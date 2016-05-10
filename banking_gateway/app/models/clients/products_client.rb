require 'httparty'

module Clients
  class ProductsClient
    attr_reader :http

    def initialize(http=HTTPBackend)
      @http = http
    end

    def all
      http.get('/products').body
    end

    def find (id)
      http.get("/products/#{id}").body
    end

    class HTTPBackend
      include HTTParty
      default_timeout 5
      base_uri 'http://products.dev/'
    end
  end
end