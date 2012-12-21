RailsExpenseReportUsingRedis::Application.routes.draw do

  resources :expenses

  root :to => "expenses#index"
end
