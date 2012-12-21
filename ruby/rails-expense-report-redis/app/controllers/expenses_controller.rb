class ExpensesController < ApplicationController
  def new

  end

  def create
    @expense = Expense.create(:description => params[:expense][:description],:amount => params[:expense][:amount],
    :expense_type_id => params[:expense][:expense_type],:date => params[:expense][:date])

    redirect_to expenses_path
  end

  def index
    @expenses = Expense.all
  end

  def edit
    @expense = Expense[params[:id]]
  end

  def update
    @expense = Expense[params[:id]]
    @expense.description = params[:expense][:description]
    @expense.amount = params[:expense][:amount]
    @expense.expense_type_id = params[:expense][:expense_type]
    @expense.date = params[:expense][:date]
    if @expense.save
      redirect_to expenses_path
    else
      render "edit"
    end
  end

  def destroy
    expense = Expense[params[:id]]
    expense.delete
    redirect_to :back
  end
end
