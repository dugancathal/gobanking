Rails.application.routes.draw do

  resources :products, only: [:index, :show]
  resources :banks, only: [:create, :show] do
    resources :deposits, only: [:create]
  end
end
