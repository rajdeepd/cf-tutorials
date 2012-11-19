ExpenseReportingPostgres::Application.routes.draw do
  get "logout" => "sessions#destroy", :as => "logout"
  get "login" => "sessions#new", :as => "login"
  get "signup" => "users#new", :as => "signup"
  resources :users
  resources :sessions
  
  resources :expenses do
  member do
    put :update_status
  end
  collection do
    get :approvals
  end
end
  
  root :to => "sessions#new"
end