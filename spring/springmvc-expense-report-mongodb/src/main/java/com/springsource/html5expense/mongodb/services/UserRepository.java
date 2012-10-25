package com.springsource.html5expense.mongodb.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.UserService;

@Service
public class UserRepository implements UserService {
	

	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String USER_COLLECTION = "User";

	public User getUserById(Long id){
		return mongoTemplate.findById(id, User.class);
	}

	public User getUserByUserName(String userName){
		List<User> roleList = mongoTemplate.find(new Query(Criteria.where("userName").
				is(userName)), User.class,USER_COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}

	public User createUser(String userName,String password,String mailId,Role role){
		User user = new User(userName,password,mailId);
		user.setRole(role);
		user.setEnabled(true);
		List<User> usersList = new ArrayList<User>();
		long val = 1;
		usersList = getAllUsers();
		val = usersList.size();
		logger.warn("size of values "+val);
		
		user.setUserId(val+1);
		mongoTemplate.save(user,USER_COLLECTION);
		return user;
	}
	
	public List<User> getAllUsers(){
		return mongoTemplate.findAll(User.class, USER_COLLECTION);
	}
}
