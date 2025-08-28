package com.sets.RegisterLoginService.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeTransDetails {

	private String source;
	private LocalDate incomeDate;
	private String description;
	private BigDecimal amount;
	
	public IncomeTransDetails() {
	}
	
	public IncomeTransDetails(String source, LocalDate incomeDate, String description, BigDecimal amount) {
		super();
		this.source = source;
		this.incomeDate = incomeDate;
		this.description = description;
		this.amount = amount;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public LocalDate getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(LocalDate incomeDate) {
		this.incomeDate = incomeDate;
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
