class AddRoleToUsers < ActiveRecord::Migration
  def change
    add_column :users, :role, :string, :default => USER_ROLES[1]
  end
end
