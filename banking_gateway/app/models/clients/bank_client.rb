require 'httparty'

module Clients
  class BankClient
    attr_reader :http

    def initialize(http=HTTPBackend)
      @http = http
    end

    def create
      http.post('/banks').body
    end

    def find(id)
      funds = JSON.parse(http.get("/banks/#{id}/funds").body)
      {'id' => id, 'amount' => funds['money']}
    end

    def deposit(bank_id, amount)
      http.post("/banks/#{bank_id}/deposit", body: {money: amount, currency: "USD"}.to_json)
    end

    class HTTPBackend
      include HTTParty
      default_timeout 5
      base_uri 'http://bank.dev/'
    end
  end
end