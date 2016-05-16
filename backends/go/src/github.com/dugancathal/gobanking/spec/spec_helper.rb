RSpec.configure do |config|
  config.expect_with :rspec do |expectations|
    expectations.include_chain_clauses_in_custom_matcher_descriptions = true
  end

  config.mock_with :rspec do |mocks|
    mocks.verify_partial_doubles = true
  end

  config.before(:suite) do
    success = 10.times.map.any? do
      sleep 0.5
      all_servers_up?
    end
    raise 'Unable to start all servers.' unless success
  end
end

def all_servers_up?
  %w(http://products.dev http://bank.dev http://cart.dev/ http://purchase.dev/).all? do |url|
    begin
      HTTParty.get(url, timeout: 1)
    rescue Net::OpenTimeout, Errno::ECONNREFUSED
      return false
    end
  end
end
