class DepositsController < ApplicationController
  def create
    render json: Rails.configuration.bank_client.deposit(params[:bank_id], params[:amount]), status: :created
  end
end