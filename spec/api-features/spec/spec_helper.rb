require 'httparty'
require 'rspec'
require 'capybara'

RSpec.configure do |config|
  config.before(:suite) do
    error = nil
    success = 60.times.map.any? do
      sleep 0.5
      success, error = all_servers_up?
      success
    end
    raise "Unable to start all servers.\n #{error}" unless success
  end
  Capybara.default_host = 'http://localhost:8080'
end

def all_servers_up?
  %w(http://products.dev http://bank.dev http://cart.dev/ http://purchase.dev/ http://gateway.dev/).all? do |url|
    begin
      HTTParty.get(url, timeout: 0.2)
    rescue Net::OpenTimeout, Net::ReadTimeout, Errno::ECONNREFUSED => e
      return false, RuntimeError.new("#{e}: #{url}")
    end
  end
  [true, nil]
end
