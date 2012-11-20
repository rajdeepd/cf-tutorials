package com.springsource.html5expense.services;

import com.springsource.html5expense.model.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByUserName(String userName);
    User createUser(User user);
}

