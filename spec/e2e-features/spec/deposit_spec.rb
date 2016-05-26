require 'spec_helper'

describe 'Deposit' do
  it 'renders the products available' do
    visit '/'
    click_on 'Bank'

    click_on 'Create a new account'

    fill_in 'Deposit Amount', with: '500.93'
    click_on 'Deposit'

    expect(page).to have_content 'Account balance: $500.93'
  end
end