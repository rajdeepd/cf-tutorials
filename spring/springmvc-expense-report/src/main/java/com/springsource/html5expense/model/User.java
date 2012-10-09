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
package com.springsource.html5expense.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User implements Serializable{
	
	@Id
	@GeneratedValue
	private Long userId;

	private String userName;
	
	private String password;
	
	private String emailId;
	
	private boolean enabled;
	
	/*@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="USER_ROLE",joinColumns={@JoinColumn(name="userId")},inverseJoinColumns={@JoinColumn(name="roleId")})
	private List<Role> userRoleList;*/
	
	@OneToOne
	private Role role;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/*public List<Role> getUserRole() {
		return userRoleList;
	}

	public void setUserRole(List<Role> userRoleList) {
		this.userRoleList = userRoleList;
	}*/
	
	public User(){
		
	}
	public User(String userName,String password,String mailId){
		this.userName = userName;
		this.password = password;
		this.emailId = mailId;
	}
}
