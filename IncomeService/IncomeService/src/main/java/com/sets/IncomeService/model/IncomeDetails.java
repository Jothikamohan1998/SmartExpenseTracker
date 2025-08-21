package com.sets.IncomeService.model;

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
@Table(name = "income_details")
public class IncomeDetails {
	
	public IncomeDetails() {}
	
	public IncomeDetails(Long id, String email, String source, LocalDate incomeDate, String description,
			LocalDateTime createdat, LocalDateTime updatedat,BigDecimal amount) {
		super();
		this.id = id;
		this.email = email;
		this.source = source;
		this.incomeDate = incomeDate;
		this.description = description;
		this.createdat = createdat;
		this.updatedat = updatedat;
		this.amount = amount;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "incomeid_seq")
	@SequenceGenerator(name = "incomeid_seq", sequenceName = "income_id_seq", allocationSize = 1)
	private Long id;
	private String email;
	@Column(length = 15)
	private String source;
	private LocalDate incomeDate;
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
