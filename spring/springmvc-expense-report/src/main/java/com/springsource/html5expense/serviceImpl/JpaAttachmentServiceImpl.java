package com.springsource.html5expense.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.service.AttachmentService;

@Service
public class JpaAttachmentServiceImpl implements AttachmentService{

	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	public Attachment getAttachment(Long attachmentId){
		Attachment attachment = new Attachment();
		attachment.setId(attachmentId);
		attachment = entityManager.find(Attachment.class,attachmentId);
		return attachment;
	}
	
	@Transactional
	public void save(Attachment attachment){
		entityManager.persist(attachment);
		
	}
	
	@Transactional
	public List<Attachment> getAttachmentByExpenseId(Long expenseId){
		Query query = entityManager.createQuery("select a from Attachment a where a.expense.id =:expenseId");
		query.setParameter("expenseId", expenseId);
		
		List<Attachment> attachmentList = query.getResultList();
		return attachmentList;
	}
	
	@Transactional
	public void deleteAttachment(Long id){
		Attachment attchment = getAttachment(new Long(id));
		getEntityManager().remove(attchment);
	}
	

}
