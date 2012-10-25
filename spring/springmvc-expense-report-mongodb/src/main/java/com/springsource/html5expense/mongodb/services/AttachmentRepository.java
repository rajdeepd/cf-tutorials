package com.springsource.html5expense.mongodb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.service.AttachmentService;

@Service
public class AttachmentRepository implements AttachmentService{

	@Autowired
	MongoTemplate mongoTemplate;
	
	public static String ATTACHMENT_COLLECTION = "Attachment";

	public void save(Attachment attachment){
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		attachmentList = getAllAttachment();
		long val = 1;
		val = attachmentList.size();
		attachment.setAttachmentId(val+1);
		mongoTemplate.save(attachment,ATTACHMENT_COLLECTION);
	}

	public Attachment getAttachment(Long attachmentId){
		List<Attachment> attachmentList = mongoTemplate.find(new Query(Criteria.where("attachmentId").
				is(attachmentId)), Attachment.class,ATTACHMENT_COLLECTION);
		return attachmentList!=null && attachmentList.size()>0?attachmentList.get(0):null;
	}


	public void deleteAttachment(Long id){
		Attachment attachment = getAttachment(id);
		mongoTemplate.remove(attachment,ATTACHMENT_COLLECTION);
	}
	
	public List<Attachment> getAttachmentByExpenseId(Long expenseId){
		List<Attachment> attachmentList = mongoTemplate.find(new Query(Criteria.where("expenseId").
				is(expenseId)), Attachment.class,ATTACHMENT_COLLECTION);
		return attachmentList;
	}
	
	public List<Attachment> getAllAttachment(){
		return mongoTemplate.findAll(Attachment.class, ATTACHMENT_COLLECTION);
	}
}
