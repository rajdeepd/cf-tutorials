class Expense < Ohm::Model
  attribute :description
  attribute :amount
  attribute :status
  attribute :date
  attribute :expense_type_id

end