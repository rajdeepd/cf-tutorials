package com.springsource.html5expense.service;

import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.model.User;

public interface UserService {
	
	public User getUserById(Long id);
	
	public User getUserByUserName(String userName);
	
	public User createUser(String userName,String password,String mailId,Role role);

}
