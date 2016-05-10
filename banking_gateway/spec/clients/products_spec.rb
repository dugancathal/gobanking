require 'spec_helper'
require 'models/clients/products_client'

RSpec.describe Clients::ProductsClient do
  let(:http) { double(HTTParty, get: double(body: '[]')) }
  describe '.all' do
    it 'returns things' do
      client = Clients::ProductsClient.new(http)

      client.all

      expect(http).to have_received(:get).with('/products')
    end
  end
end