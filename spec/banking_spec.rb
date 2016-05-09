require 'spec_helper'
require 'httparty'
require 'json'

describe 'Purchasing a product with funds from the bank' do
  it 'Allows a user to purchase if there are sufficient funds in the bank' do
    bank_id = create_piggy_bank
    deposit(bank_id, 20.00)
    add_product_to_cart("TeddyBear")
    purchase = checkout(bank_id)
    receipt = receipt(purchase['id'])

    expect(receipt['subtotal']['money']).to eq 9.99
    expect(receipt['tax']['money']).to eq 0.80
    expect(receipt['total']['money']).to eq 10.79

    expect(funds(bank_id)['money']).to eq 9.21
  end

  private

  def create_piggy_bank
    bank_response = make_call 'POST', "http://bank.dev/banks", {}
    bank_response['id']
  end

  def add_product_to_cart(product)
    product = make_call 'GET', "http://products.dev/products/#{URI.encode_www_form_component(product)}"
    make_call 'POST', "http://cart.dev/cart/items", {product_id: product['id']}.to_json
  end

  def checkout(bank_id)
    make_call 'POST', "http://purchase.dev/checkout", {account_id: bank_id}.to_json
  end

  def receipt(purchase_id)
    make_call 'GET', "http://purchase.dev/receipts/#{purchase_id}"
  end

  def deposit(bank_id, pennies)
    make_call 'POST', "http://bank.dev/banks/#{bank_id}/deposit", {money: pennies, currency: 'USD'}.to_json
  end

  def funds(bank_id)
    make_call 'GET', "http://bank.dev/banks/#{bank_id}/funds"
  end

  def make_call(method, url, body={})
    response = HTTParty.public_send(method.downcase, url, body: body)
    if response.code == 503
      raise 'Good try'
    end
    JSON.parse(response.body.empty? ? '{}' : response.body)
  end
end