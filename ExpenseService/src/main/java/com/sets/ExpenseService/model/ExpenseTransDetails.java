package com.sets.ExpenseService.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseTransDetails {
	
	private String category;
	private LocalDate expenseDate;
	private String description;
	private BigDecimal amount;
	
	public ExpenseTransDetails() {
	}
	
	public ExpenseTransDetails(String category, LocalDate expenseDate, String description, BigDecimal amount) {
		super();
		this.category = category;
		this.expenseDate = expenseDate;
		this.description = description;
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public LocalDate getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
