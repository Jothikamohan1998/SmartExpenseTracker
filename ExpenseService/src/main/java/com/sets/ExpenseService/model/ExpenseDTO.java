package com.sets.ExpenseService.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

public class ExpenseDTO {
	
	public ExpenseDTO(){
	}
	
	public ExpenseDTO(Long id, String email, String category, LocalDate expenseDate, String formattedIncomeDate,
			String description, BigDecimal amount) {
		super();
		this.id = id;
		this.email = email;
		this.category = category;
		this.expenseDate = expenseDate;
		this.formattedIncomeDate = formattedIncomeDate;
		this.description = description;
		this.amount = amount;
	}
	private Long id;
	private String email;
	private String category;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expenseDate;
	private String formattedIncomeDate; 
	private String description;
	private BigDecimal amount;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		if(expenseDate!=null)
		{
			DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			this.formattedIncomeDate =  expenseDate.format(formater);
		}
	}

	public String getFormattedIncomeDate() {
		return formattedIncomeDate;
	}

	public void setFormattedIncomeDate(String formattedIncomeDate) {
		this.formattedIncomeDate = formattedIncomeDate;
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
