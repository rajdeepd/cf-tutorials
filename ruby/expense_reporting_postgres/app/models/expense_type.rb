class ExpenseType < ActiveRecord::Base
  attr_accessible :name
  has_many :expenses
  validates :name, :presence => true, :uniqueness => true
end