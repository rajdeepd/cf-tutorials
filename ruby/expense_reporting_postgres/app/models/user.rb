class User < ActiveRecord::Base
  authenticates_with_sorcery!

  attr_accessible :username, :password, :password_confirmation, :role

  validates_confirmation_of :password
  validates_presence_of :password, :on => :create
  validates_presence_of :username
  validates_uniqueness_of :username

  has_many :expenses

  def is_admin?
	  role.eql? USER_ROLES[0]
	end
end