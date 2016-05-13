class BanksController < ApplicationController
  def create
    render json: Rails.configuration.bank_client.create, status: :created
  end

  def show
    render json: Rails.configuration.bank_client.find(params[:id])
  end
end