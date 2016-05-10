require 'rails_helper'

RSpec.describe 'Products' do
  it 'lists them' do
    products = [{id: '123', name: 'Teddy Bear', description: 'Soft and cuddly', price: {money: 123.22, currency: 'USD'}}]
    allow(Rails.configuration.products_client).to receive(:all) { products }
    get '/products'

    expect(response).to be_ok
    expect(Rails.configuration.products_client).to have_received(:all)
    expect(parsed_body).to eq products
  end

  it 'can fetch a single product' do
    product = {id: '124', name: 'Chocolate Bar', description: 'Dark and bitter', price: {money: 0.99, currency: 'USD'}}
    allow(Rails.configuration.products_client).to receive(:find) { product }
    get '/products/124'

    expect(response).to be_ok
    expect(Rails.configuration.products_client).to have_received(:find).with('124')
    expect(parsed_body).to eq product
  end

  private

  def parsed_body
    parsed = JSON.parse(response.body)
    if parsed.is_a?(Array)
      parsed.map &:deep_symbolize_keys
    else
      parsed.deep_symbolize_keys
    end
  end
end