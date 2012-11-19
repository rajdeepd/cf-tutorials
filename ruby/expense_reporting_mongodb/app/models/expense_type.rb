class ExpenseType  
  include Mongoid::Document
  include Mongoid::Timestamps
  
  field :name, type: String

  has_many :expenses
  validates :name, :presence => true, :uniqueness => true
end