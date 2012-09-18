package com.springsource.html5expense.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseReport;
import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.model.State;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.ExpenseService;
import com.springsource.html5expense.service.UserService;



@Service
public class JpaExpenseServiceImpl implements ExpenseService {
	
	
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	private UserService userService;
	
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Transactional
	public Long createExpense(String description,ExpenseType expenseType,Date expenseDate,
			Double amount,User user,Attachment attachment) {
		// TODO Auto-generated method stub
		Expense expense = new Expense(description,expenseType,expenseDate,amount,user,attachment);
		//ExpenseReport report = new ExpenseReport(expense);
		entityManager.persist(expense);
		//entityManager.persist(report);
		return expense.getId();
	}
	
	
	@Transactional
	public Expense getExpense(Long expenseId){
		return entityManager.find(Expense.class, expenseId);
	}
	
	
	@Transactional
    public void remove(Integer id) {
         
        
    }

/*	
	public EntityManager entityManager {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}*/
	
	@Override
	@Transactional
	public List getAllExpenses(){
		Query query = getEntityManager().createQuery("select e from Expense e");
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public List<Expense> getExpensesByUser(User user){
		Query query = getEntityManager().createQuery("select e from Expense e where e.user.userId =:userId ORDER BY e.expenseDate DESC",Expense.class);
		query.setParameter("userId", user.getUserId());
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public List<Expense> getPendingExpensesList(){
		List<Enum> state = new ArrayList<Enum>();
		state.add(State.NEW);
		state.add(State.OPEN);
		state.add(State.IN_REVIEW);
		Query query = entityManager.createQuery("select e from Expense e where e.state IN (:state) ORDER BY e.expenseDate DESC",Expense.class);
		query.setParameter("state", state);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public List<Expense> getApprovedAndRejectedExpensesList(){
		List<Enum> state = new ArrayList<Enum>();
		state.add(State.REJECTED);
		state.add(State.APPROVED);
		Query query = entityManager.createQuery("select e from Expense e where e.state IN (:state) e.expenseDate DESC",Expense.class);
		query.setParameter("state", state);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public Expense changeExpenseStatus(Long expenseId,String state){
		Expense expense = getExpense(expenseId);
		expense.setState(State.valueOf(state));
		getEntityManager().merge(expense);
		return expense;
		
		
	}
	
	@Override
	@Transactional
	public void deleteExpense(Long expenseId){
		Expense expense = getExpense(expenseId);
		expense.setAttachment(null);
		getEntityManager().remove(expense);
		
	}
	
	@Override
	@Transactional
	public void updateExpense(Long expenseId,String description,Double amount,ExpenseType expenseType){
		Expense expense = getExpense(expenseId);
		expense.setDescription(description);
		expense.setAmount(amount);
		expense.setExpenseType(expenseType);
		getEntityManager().merge(expense);
		
		
	}


}
