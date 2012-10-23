package com.springsource.html5expense.serviceImpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.ExpenseTypeService;
import com.springsource.html5expense.service.RoleService;
import com.springsource.html5expense.service.UserService;

@Service
@Transactional
public class InitService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ExpenseTypeService expenseTypeService;
	
	
	@PostConstruct
	public void init(){
		Role roleUser = new Role();
		roleUser.setRoleName("ROLE_USER");
		roleService.save(roleUser);
		
		Role roleManager = new Role();
		roleManager.setRoleName("ROLE_MANAGER");
		roleService.save(roleManager);
		
		User user = new User();
		user.setEmailId("admin@expense.com");
		user.setEnabled(new Boolean(true));
		user.setPassword("admin");
		user.setUserName("admin");
		user.setRole(roleManager);
		userService.save(user);
		
		ExpenseType expenseType = new ExpenseType();
		expenseType.setName("TRAVEL");
		expenseTypeService.save(expenseType);
		
		ExpenseType expenseType1 = new ExpenseType();
		expenseType1.setName("MEDICAL");
		expenseTypeService.save(expenseType1);
		
		
	}
	
	

}
