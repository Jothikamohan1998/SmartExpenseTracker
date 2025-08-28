package com.sets.RegisterLoginService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sets.RegisterLoginService.dao.RegisterDao;
import com.sets.RegisterLoginService.model.LoginDTO;
import com.sets.RegisterLoginService.model.RegisterDTO;
import com.sets.RegisterLoginService.model.Role;
import com.sets.RegisterLoginService.model.UserDetails;

@Service
public class RegisterService {
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String registerUser(RegisterDTO registerDTO) {
		
		UserDetails existingUser = registerDao.findByEmail(registerDTO.getEmail());
		UserDetails user = new UserDetails();
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		user.setRole(Role.USER);
		user.setEmail(registerDTO.getEmail());
		user.setUsername(registerDTO.getUsername());
		if (existingUser == null) {
//			System.out.println("inside null block");
			registerDao.save(user);
			return "user registered successfully";
		}
		else {
			return "user already exist";
		}
		
	}

	public boolean login(LoginDTO loginDto) {

		UserDetails userDetails = registerDao.findByEmail(loginDto.getEmail());
		if(userDetails==null){
			return false;
		}
		String password = userDetails.getPassword();
		boolean ismatches =passwordEncoder.matches(loginDto.getPassword(), password);
		return ismatches;
	}

}
