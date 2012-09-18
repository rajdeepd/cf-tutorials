package com.springsource.html5expense.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.RoleService;
import com.springsource.html5expense.service.UserService;

@Service
public class JpaUserServiceImpl implements UserService{
	

	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public User getUserById(Long userId){
		return getEntityManager().find(User.class, userId);
	}
	
	@Override
	@Transactional
	public User getUserByUserName(String userName){
		Query query = getEntityManager().createQuery("from User u where u.userName =:username");
		query.setParameter("username",userName);
		return  (query.getResultList()!=null && query.getResultList().size()>0)?(User)(query.getResultList().get(0)):null;
		//return getEntityManager().find(User.class,userName);
	}
	
	@Override
	@Transactional
	public User createUser(String userName,String password,String mailId,Role role){
		User user = new User(userName,password,mailId);
		user.setEnabled(new Boolean(true));
		user.setRole(role);
		getEntityManager().persist(user);
		return user;
		
	}

}
