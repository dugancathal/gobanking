require 'rails_helper'

describe 'Banking' do
  before do
    @bank = {id: "1", amount: {money: 0.0, currency: 'USD'}}
    allow(Rails.configuration.bank_client).to receive(:create) { @bank }
    allow(Rails.configuration.bank_client).to receive(:find) { @bank }
  end

  it 'can create a bank account' do
    post '/banks'

    expect(response).to be_created
    expect(Rails.configuration.bank_client).to have_received(:create)
    expect(parsed_body).to eq @bank
  end

  it 'can deposit funds into an account' do
    post '/banks'

    bank_id = parsed_body[:id]

    post "/banks/#{bank_id}/deposits", {amount: 50}.to_json, 'CONTENT_TYPE' => 'application/json'
    expect(response).to be_created

    get "/banks/#{bank_id}"

    expect(response).to be_ok
    expect(Rails.configuration.bank_client).to have_received(:find).with('1')
  end
end