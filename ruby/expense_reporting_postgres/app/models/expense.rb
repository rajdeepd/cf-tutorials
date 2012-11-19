class Expense < ActiveRecord::Base
  attr_accessible :description, :date, :amount, :status,
                  :attachment, :expense_type_id, :user_id
  
  mount_uploader :attachment, AttachmentUploader # for using the attachment_uploader.rb file for uploading the attachments
  
  belongs_to :user
  belongs_to :expense_type

  validates :description, :expense_type_id, :status, :user_id, :date, :presence => true
  validates :amount, :numericality => {:message => "is not valid"}

  scope :pending, where(:status => EXPENSE_STATUS[0])
end
