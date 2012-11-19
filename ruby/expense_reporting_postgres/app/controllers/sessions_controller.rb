class SessionsController < ApplicationController
  def new
    if current_user
      redirect_to expenses_path()
    end
  end

  def create
    user = login(params[:username], params[:password], params[:remember_me])
    if user
      redirect_back_or_to expenses_path(), :notice => "Logged in!"
    else
      @error_message = "Invalid Username or Password."
      render 'new'
    end
  end

  def destroy
	  logout
	  redirect_to root_url, :notice => "Logged out!"
	end
end