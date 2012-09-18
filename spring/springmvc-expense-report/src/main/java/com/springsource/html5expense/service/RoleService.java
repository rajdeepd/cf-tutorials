package com.springsource.html5expense.service;

import com.springsource.html5expense.model.Role;

public interface RoleService {

	public Role getRole(Long id);
	
	public Role getRoleByName(String name);
}
