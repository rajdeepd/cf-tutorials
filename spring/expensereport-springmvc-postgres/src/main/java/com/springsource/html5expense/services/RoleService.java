package com.springsource.html5expense.services;

import com.springsource.html5expense.model.Role;
public interface RoleService {
    Role getRole(Long id);

    Role getRoleByName(String name);
}
