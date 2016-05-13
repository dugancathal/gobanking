$LOAD_PATH << File.expand_path('../app', __dir__)
RSpec.configure do |config|
  config.expect_with :rspec do |expectations|
    expectations.include_chain_clauses_in_custom_matcher_descriptions = true
  end

  config.mock_with :rspec do |mocks|
    mocks.verify_partial_doubles = true
  end
end

def parsed_body
  parsed = JSON.parse(response.body)
  if parsed.is_a?(Array)
    parsed.map &:deep_symbolize_keys
  else
    parsed.deep_symbolize_keys
  end
end