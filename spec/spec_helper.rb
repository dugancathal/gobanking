require 'httparty'
require 'rspec'

RSpec.configure do |config|
  config.before(:suite) do
    success = 10.times.map.any? do
      sleep 0.5
      all_servers_up?
    end
    raise 'Unable to start all servers.' unless success
  end
end

def all_servers_up?
  %w(http://products.dev http://bank.dev http://cart.dev/ http://purchase.dev/ http://gateway.dev/).all? do |url|
    begin
      HTTParty.get(url, timeout: 0.2)
    rescue Net::OpenTimeout, Errno::ECONNREFUSED
      return false
    end
  end
end
