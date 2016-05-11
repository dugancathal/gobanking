require 'spec_helper'

describe 'Products' do
  it 'can list products' do
    products = HTTParty.get 'http://gateway.dev/products'
    expect(products).to be_ok
    expect(products.length).to eq 1
    expect(products[0]['name']).to eq 'TeddyBear'
  end
end