package com.sets.RegisterLoginService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sets.RegisterLoginService.model.UserDetails;

@Repository
public interface RegisterDao extends JpaRepository<UserDetails, Long> {

	UserDetails findByEmail(String email);

}
