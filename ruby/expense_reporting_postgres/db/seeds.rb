puts "creating default user and admin"
User.create!(username: "user", password: "123123", password_confirmation: "123123")
User.create!(username: "admin", password: "123123", password_confirmation: "123123", role: USER_ROLES[0])

puts "creating expense types"
ExpenseType.create!(name: "Travel")
ExpenseType.create!(name: "Education")
ExpenseType.create!(name: "Health")