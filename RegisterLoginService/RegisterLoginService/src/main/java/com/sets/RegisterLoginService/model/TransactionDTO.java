package com.sets.RegisterLoginService.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDTO {

	public TransactionDTO() {
		
	}
	
	public TransactionDTO(String type, LocalDate date, String category, String description,BigDecimal amount) {
		super();
		this.type = type;
		this.date = date;
		this.category = category;
		this.description = description;
		this.amount = amount;
	}
	
	private String type;
	private LocalDate date;
	private String category;
	private String description;
	private BigDecimal amount;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
