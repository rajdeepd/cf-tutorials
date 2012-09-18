package com.springsource.html5expense.service;

import java.util.List;

import com.springsource.html5expense.model.Attachment;

public interface AttachmentService {
	public void save(Attachment attachment);
	
	public Attachment getAttachment(Long attachmentId);
	
	public List<Attachment> getAttachmentByExpenseId(Long expenseId);
	
	public void deleteAttachment(Long id);
}
