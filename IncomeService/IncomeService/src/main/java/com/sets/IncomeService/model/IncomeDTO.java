package com.sets.IncomeService.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

public class IncomeDTO {
	private Long id;
	private String email;
	private String source;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate incomeDate;
	private String formattedIncomeDate; 
	private String description;
	private BigDecimal amount;
	
	public IncomeDTO() {}
	
	public IncomeDTO(Long id,String email, String source, LocalDate incomeDate, String description,BigDecimal amount) {
		super();
		this.id=id;
		this.email = email;
		this.source = source;
		this.incomeDate = incomeDate;
		this.description = description;
		this.amount = amount;
	}
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public LocalDate getIncomeDate() {
		return incomeDate;
	}
	 public String getFormattedIncomeDate() {
	        return formattedIncomeDate;
	    }
	
	public void setIncomeDate(LocalDate incomeDate) {
		this.incomeDate = incomeDate;
		if(incomeDate!=null)
		{
			DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			this.formattedIncomeDate =  incomeDate.format(formater);
		}
		
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
