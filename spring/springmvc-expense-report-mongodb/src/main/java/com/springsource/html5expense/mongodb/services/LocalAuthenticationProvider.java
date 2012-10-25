package com.springsource.html5expense.mongodb.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.springsource.html5expense.service.UserService;

/*
 Extend AbstractUserDetailsAuthenticationProvider when you want to
 prehandle authentication, as in throwing custom exception messages,
 checking status, etc. 
 */
@Component
public class LocalAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    UserService userService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	@Override
	public UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
        String password = (String) authentication.getCredentials();
        if (!StringUtils.hasText(password)) {
        	logger.warn("Username {}: no password provided", username);
            throw new BadCredentialsException("Please enter password");
        }

        //UserAccount user = userService.getByUsernameAndPassword(username, encoder.encodePassword(password, null));
        com.springsource.html5expense.model.User user = userService.getUserByUserName(username);
        if (user == null) {
        	logger.warn("Username {}, password {}: username and password not found", username, password);
            throw new BadCredentialsException("Invalid Username/Password");
        }
       
        final List<GrantedAuthority> auths;
        if (null!= user.getRole()) {
	    	auths = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getRoleName());
        } else {
        	auths = AuthorityUtils.NO_AUTHORITIES;
        }

        return new User(username, password, user.isEnabled(), // enabled
                true, // account not expired
                true, // credentials not expired
                true, // account not locked
                auths);
	}

}
