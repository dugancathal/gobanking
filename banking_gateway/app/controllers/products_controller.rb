class ProductsController < ApplicationController
  def index
    render json: Rails.configuration.products_client.all
  end

  def show
    render json: Rails.configuration.products_client.find(params[:id])
  end
end