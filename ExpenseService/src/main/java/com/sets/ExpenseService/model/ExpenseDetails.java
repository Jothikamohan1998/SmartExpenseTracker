package com.sets.ExpenseService.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense_details")
public class ExpenseDetails {
	
	public ExpenseDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public ExpenseDetails(Long id, String email, String category, LocalDate expenseDate, String description,
			LocalDateTime createdat, LocalDateTime updatedat, BigDecimal amount) {
		super();
		this.id = id;
		this.email = email;
		this.category = category;
		this.expenseDate = expenseDate;
		this.description = description;
		this.createdat = createdat;
		this.updatedat = updatedat;
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "expenseid_seq")
	@SequenceGenerator(name = "expense_seq", sequenceName = "expense_id_seq", allocationSize = 1)
	private Long id;
	private String email;
	@Column(length = 15)
	private String category;
	private LocalDate expenseDate;
	@Column(length = 30)
	private String description;
	private LocalDateTime createdat;
	private LocalDateTime updatedat;
	private BigDecimal amount;
	@PrePersist
	protected void onCreate() {
		this.createdat  = LocalDateTime.now();
		this.updatedat  = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedat = LocalDateTime.now();
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

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	public LocalDateTime getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(LocalDateTime updatedat) {
		this.updatedat = updatedat;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
