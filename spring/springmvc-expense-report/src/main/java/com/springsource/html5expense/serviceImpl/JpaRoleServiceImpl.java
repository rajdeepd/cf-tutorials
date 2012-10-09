/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springsource.html5expense.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.html5expense.model.Role;
import com.springsource.html5expense.service.RoleService;

@Service
public class JpaRoleServiceImpl implements RoleService{


	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	public Role getRole(Long id){
		return getEntityManager().find(Role.class, id);
	}

	@Transactional
	public Role getRoleByName(String name){
			Query query = getEntityManager().createQuery("select r from Role r where roleName =:roleName",Role.class);
			query.setParameter("roleName", name);
			return (query.getResultList()!=null && query.getResultList().size()>0)?(Role)query.getResultList().get(0):null;
	}
}
