require 'spec_helper'

describe 'Banking' do
  RSpec::Matchers.define :have_response_field do |field, opts={}|
    match do |actual|
      if opts.has_key?(:with) && JSON.parse(actual.body).has_key?(field.to_s)
        actual[field.to_s] == opts[:with]
      else
        actual.has_key?(field.to_s)
      end
    end
  end

  it 'can deposit funds into an account' do
    client = GatewayClient.new
    create_response = client.create_bank
    bank_id = create_response.fetch('id')

    deposit_response = client.deposit(bank_id, amount: 50)
    expect(deposit_response.code).to eq 200

    bank_response = client.get_bank(bank_id)

    expect(bank_response.code).to eq 200
    expect(bank_response).to have_response_field 'id', with: bank_id
    expect(bank_response.fetch('amount')).to eq 50.00
  end

  class GatewayClient
    include HTTParty
    base_uri 'http://gateway.dev'

    def create_bank
      make_call 'POST', '/banks'
    end

    def get_bank(id)
      self.make_call 'GET', "/banks/#{id}"
    end

    def deposit(bank_id, body)
      make_call 'POST', "/banks/#{bank_id}/deposit", body
    end

    def make_call(method, url, body={})
      response = self.class.public_send(method.downcase, url, body: body.to_json, headers: {'CONTENT-TYPE' => 'application/json'})
      case response.code
        when 200..399 then response
        when 400..499 then raise "#{method} #{url} - You did something wrong (#{response.code})"
        when 500..599 then raise "#{method} #{url} - The server did something wrong (#{response.code})"
        else raise "#{method} #{url} - I did something wrong (#{response.code})"
      end
    end
  end
end