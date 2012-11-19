class CreateExpenses < ActiveRecord::Migration
  def change
    create_table :expenses do |t|
      t.string :description
      t.date :date
      t.float :amount
      t.string :status, :default => EXPENSE_STATUS[0]
      t.string :attachment

      t.references :expense_type
      t.references :user
      t.timestamps
    end
  end
end