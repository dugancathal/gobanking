require 'spec_helper'
require 'models/clients/bank_client'

describe Clients::BankClient do

  describe '#deposit' do
    let(:http) { double(HTTParty, post: double(body: '{}')) }

    it 'adds funds to the account' do
      client = Clients::BankClient.new(http)
      client.deposit(1, 50)

      expect(http).to have_received(:post).with("/banks/1/deposit", body: {money: 50, currency: "USD"}.to_json)
    end
  end

  describe '#find' do
    let(:http) { double(HTTParty, get: double(body: '{"money": 92, "currency": "USD"}')) }

    it 'returns the account with the id' do
      client = Clients::BankClient.new(http)
      bank = client.find(2)

      expect(bank['id']).to eq 2
    end

    it 'returns the amount in the account' do
      client = Clients::BankClient.new(http)
      bank = client.find(2)

      expect(bank['amount']).to eq 92.00
    end
  end
end