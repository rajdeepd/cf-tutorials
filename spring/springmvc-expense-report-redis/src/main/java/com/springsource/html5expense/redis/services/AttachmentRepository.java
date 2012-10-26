package com.springsource.html5expense.redis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.service.AttachmentService;

@Service
public class AttachmentRepository implements AttachmentService{
	
	private static final String ATTACHMENT = "ATTACHMENT";
	
	@Autowired
	RedisTemplate<String, Cacheable> redisTemplate;
	
	public void save(Attachment attachment){
		long id = 1;
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		attachmentList = getAllAttachments();
		id = attachmentList.size();
		attachment.setId(id+1);
		redisTemplate.opsForHash().put(ATTACHMENT, attachment.getId(),
				attachment);
	}
	
	public Attachment getAttachment(Long attachmentId){
		return (Attachment)redisTemplate.opsForHash().
				get(ATTACHMENT, attachmentId);
	}
	
	public List<Attachment> getAttachmentByExpenseId(Long expenseId){
		Set<Object> keys = redisTemplate.opsForHash().keys(ATTACHMENT);
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		for(Object key:keys){
			Attachment attachment = (Attachment)redisTemplate.opsForHash().get(ATTACHMENT, key);
			attachmentList.add(attachment);
		}return attachmentList;
	}
	
	public void deleteAttachment(Long id){
		redisTemplate.opsForHash().delete(ATTACHMENT, id);
	}
	
	public List<Attachment> getAllAttachments(){
		Set<Object> keys = redisTemplate.opsForHash().keys(ATTACHMENT);
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		for(Object key:keys){
				attachmentList.add((Attachment)redisTemplate.opsForHash().get(ATTACHMENT, key));
		}return attachmentList;
	}

}
