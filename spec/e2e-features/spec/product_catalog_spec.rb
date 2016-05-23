require 'spec_helper'

describe 'Product Catalog' do
  it 'renders the products available' do
    visit '/products'

    expect(page).to have_css 'th', text: 'Name'

    pending 'Fetch polyfill'
    expect(page).to have_css 'tr', text: 'TeddyBear'
  end
end