package com.sets.ExpenseService.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sets.ExpenseService.dao.ExpenseDAO;
import com.sets.ExpenseService.model.CategoryDetails;
import com.sets.ExpenseService.model.ExpenseTransDetails;

@Service
public class ExpenseCalService {

	@Autowired
	private ExpenseDAO expenseDAO;
	
	public BigDecimal TotalExpense(String email,LocalDate fromDate,LocalDate toDate) {
		BigDecimal sum = expenseDAO.findAllByFilter(email,fromDate,toDate);
		System.out.println("total amount : "+sum);
		return sum;
	}

	public List<CategoryDetails> getAllCategoryExpense(String email, LocalDate fromDate, LocalDate toDate) {
		List<CategoryDetails> categorydetails = expenseDAO.findAllCategoryExpense(email,fromDate,toDate);
		return categorydetails;
	}
	
	public List<ExpenseTransDetails> getExpenseTransDetails(String email, LocalDate fromDate, LocalDate toDate) {
		List<ExpenseTransDetails> expensetransdetails = expenseDAO.findExpenseTransDetails(email,fromDate,toDate);
		return expensetransdetails;
	}
}
