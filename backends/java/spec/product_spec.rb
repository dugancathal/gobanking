require 'spec_helper'

describe 'Product API' do
  it 'can be accessed' do
    response = HTTParty.get('http://localhost:8080/products')
    expect(response.code).to eq(200)
  end
end