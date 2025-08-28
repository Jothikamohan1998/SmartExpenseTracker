package com.sets.ExpenseService.model;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Globals {

	
	public String getCurrentuser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
