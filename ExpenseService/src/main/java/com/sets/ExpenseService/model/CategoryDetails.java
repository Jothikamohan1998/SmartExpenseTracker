package com.sets.ExpenseService.model;

import java.math.BigDecimal;

public class CategoryDetails {
	private String category;
	private BigDecimal amount;
	
	public CategoryDetails() {
		
	}
	
	public CategoryDetails(String category, BigDecimal amount) {
		super();
		this.category = category;
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
