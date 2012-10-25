package com.springsource.html5expense.mongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.service.RoleService;

@Service
public class RoleRepository implements RoleService {

	@Autowired
	MongoTemplate mongoTemplate;

	public static final String ROLE_COLLECTION = "Role";

	public Role getRole(Long id){
		return mongoTemplate.findById(id, Role.class,ROLE_COLLECTION);
	}

	public Role getRoleByName(String name){
		List<Role> roleList = mongoTemplate.find(new Query(Criteria.where("roleName").
				is(name)), Role.class,ROLE_COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}

}
