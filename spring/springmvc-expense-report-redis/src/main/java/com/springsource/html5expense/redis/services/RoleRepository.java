package com.springsource.html5expense.redis.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.service.RoleService;

@Service
public class RoleRepository implements RoleService {
	
	private static final String ROLE = "ROLE" ;
	
	@Autowired
	RedisTemplate<String, Cacheable> redisTemplate;
	
	public Role getRole(Long id){
		return (Role)redisTemplate.opsForHash().get(ROLE, id);
	}
	
	public Role getRoleByName(String name){
		return (Role)redisTemplate.opsForHash().get(ROLE, name);
	}
	
	public void save(Role role){
		List<Role> roleList = new ArrayList<Role>();
		long id = 1;
		roleList = getAllRoles();
		id = roleList.size()+1;
		role.setRoleId(id);
		redisTemplate.opsForHash().put(ROLE, role.getRoleId(), role);
	}
	
	public List<Role> getAllRoles(){
		List<Role> roleList = new ArrayList<Role>();
		for(Object key:redisTemplate.opsForHash().keys(ROLE)){
			roleList.add((Role)redisTemplate.opsForHash().get(ROLE, key));
		}
		return roleList;
	}
	
}
