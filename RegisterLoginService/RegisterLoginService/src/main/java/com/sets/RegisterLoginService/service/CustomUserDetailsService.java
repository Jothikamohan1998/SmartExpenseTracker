package com.sets.RegisterLoginService.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sets.RegisterLoginService.dao.RegisterDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	RegisterDao registerDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.sets.RegisterLoginService.model.UserDetails  userdetails =registerDao.findByEmail(username);
		if (userdetails == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
        		userdetails.getEmail(),
        		userdetails.getPassword(),
                Collections.emptyList()
        );
	}

}
