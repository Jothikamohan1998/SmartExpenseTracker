package com.sets.IncomeService.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sets.IncomeService.dao.IncomeDAO;
import com.sets.IncomeService.model.IncomeTransDetails;

@Service
public class IncomeCalService {

	@Autowired
	private IncomeDAO incomeDAO;
	
	public BigDecimal TotalIncome(String email,LocalDate fromDate,LocalDate toDate) {
		BigDecimal sum = incomeDAO.findAllByFilter(email,fromDate,toDate);
		System.out.println("total amount : "+sum);
		return sum;
	}
	
	public List<IncomeTransDetails> getIncomeTransDetails(String email, LocalDate fromDate, LocalDate toDate) {
		List<IncomeTransDetails> incometransdetails = incomeDAO.findIncomeTransDetails(email,fromDate,toDate);
		return incometransdetails;
	}
}
