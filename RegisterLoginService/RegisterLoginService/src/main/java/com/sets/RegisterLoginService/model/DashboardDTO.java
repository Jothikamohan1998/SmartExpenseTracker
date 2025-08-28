package com.sets.RegisterLoginService.model;

import java.math.BigDecimal;
import java.util.List;

public class DashboardDTO {

	public DashboardDTO() {
	}
	
	public DashboardDTO(BigDecimal totalBalance, BigDecimal totalIncome, BigDecimal totalExpense,
			List<String> categories, List<BigDecimal> categoryamount, List<TransactionDTO> transactions) {
		super();
		this.totalBalance = totalBalance;
		this.totalIncome = totalIncome;
		this.totalExpense = totalExpense;
		this.categories = categories;
		this.categoryamount = categoryamount;
		this.transactions = transactions;
	}

	private BigDecimal totalBalance;
	private BigDecimal totalIncome;
	private BigDecimal totalExpense;
	private List<String> categories; 
	private List<BigDecimal> categoryamount; 
	private List<TransactionDTO> transactions;
	
	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<BigDecimal> getCategoryamount() {
		return categoryamount;
	}

	public void setCategoryamount(List<BigDecimal> categoryamount) {
		this.categoryamount = categoryamount;
	}

	public List<TransactionDTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}
}
