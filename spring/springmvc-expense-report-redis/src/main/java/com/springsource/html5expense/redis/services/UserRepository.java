package com.springsource.html5expense.redis.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.UserService;

@Service
public class UserRepository implements UserService
{
	private static final String USER = "USER";
	
	@Autowired
	RedisTemplate<String, Cacheable> redisTemplate;
	
	public User getUserById(Long id)
	{
		return (User)redisTemplate.opsForHash().get(USER, id);
	}
	
	public User getUserByUserName(String userName){
		User user = null;
		for(Object key:redisTemplate.opsForHash().keys(USER)){
			user  = (User)redisTemplate.opsForHash().get(USER, key);
			if(user!=null && userName.equalsIgnoreCase(userName)){
				break; 
			}
		}
		return user;
	}
	
	public User createUser(String userName,String password,String mailId,Role role){
		User user = new User(userName, password, mailId);
		user.setEnabled(true);
		List<User> userList = new ArrayList<User>();
		long id = 1;
		id = userList.size()+1;
		user.setUserId(id);
		redisTemplate.opsForHash().put(USER, user.getUserId(), user);
		return user;
	}
	
	public void save(User user){
		redisTemplate.opsForHash().put(USER, user.getUserId(), user);
	}
	
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<User>();
		for(Object key:redisTemplate.opsForHash().keys(USER)){
			userList.add( (User)redisTemplate.opsForHash().get(USER,
					key));
			
		}
		return userList;
	}

}
