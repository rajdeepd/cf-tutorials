package com.springsource.html5expense.redis.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.ExpenseTypeService;
import com.springsource.html5expense.service.RoleService;
import com.springsource.html5expense.service.UserService;


@Service
public class InitService {

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ExpenseTypeService expenseTypeService;
	
	
	private final org.apache.log4j.Logger logger = org.apache.log4j.
			Logger.getLogger(InitService.class);
	
	
	@PostConstruct
	public void init(){
		
		Role role = new Role();
		role.setRoleName("ROLE_USER");
		role.setRoleId(new Long(1));
		roleService.save(role);
		
		logger.info("init role save "+role.getRoleId());
		
		Role adminRole = new Role();
		adminRole.setRoleName("ROLE_MANAGER");
		adminRole.setRoleId(new Long(2));
		roleService.save(adminRole);
		logger.info("init role save "+adminRole.getRoleId());
		
		User user = new User();
		user.setUserId(new Long(1));
		user.setEmailId("admin@expense.com");
		user.setEnabled(true);
		user.setRole(adminRole);
		user.setUserName("admin");
		user.setPassword("admin");
		userService.save(user);
		logger.info("init user save "+user.getUserId());
		
		ExpenseType expenseType = new ExpenseType();
		expenseType.setId(new Long(1));
		expenseType.setName("GYM");
		expenseTypeService.save(expenseType);
		
		
		
	}
}
