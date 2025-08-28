package com.sets.RegisterLoginService.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sets.RegisterLoginService.feignClient.ExpenseClient;
import com.sets.RegisterLoginService.feignClient.IncomeClient;
import com.sets.RegisterLoginService.model.CategoryDetails;
import com.sets.RegisterLoginService.model.ExpenseTransDetails;
import com.sets.RegisterLoginService.model.IncomeTransDetails;
import com.sets.RegisterLoginService.model.TransactionDTO;

@Service
public class DashboardService {
	
	private IncomeClient incomeClient;
	
	private ExpenseClient expenseClient;
	
	public DashboardService(IncomeClient incomeClient, ExpenseClient expenseClient) {
		this.incomeClient = incomeClient;
		this.expenseClient = expenseClient;
	}
	
	public void showLandingPageDetails(Model model,String email,LocalDate  fromdate,LocalDate todate) {
		
		ResponseEntity<BigDecimal> totincome_res =  incomeClient.getTotalIncome(email,fromdate,todate);
		BigDecimal totincome = totincome_res.getBody();
		System.out.println("total income fetch from income service : "+totincome);
		ResponseEntity<BigDecimal> totexpense_res = expenseClient.getTotalExpense(email, fromdate, todate);
		BigDecimal totexpense =  totexpense_res.getBody();
		System.out.println("total expense fetch from expense service : "+totexpense);
		BigDecimal netBalance = totincome.add(totexpense);
		System.out.println("Net balance calculated : "+netBalance);

		ResponseEntity<List<CategoryDetails>> res_map = expenseClient.getAllCategoryExpense(email, fromdate, todate);
		List<CategoryDetails> catdetails =  res_map.getBody();
		System.out.println("map size is : "+catdetails.size());
		
		List<TransactionDTO> transactionls = new ArrayList<>();
		ResponseEntity<List<ExpenseTransDetails>> expensetransdetails = expenseClient.getExpenseTransDetails(email, fromdate, todate);
		List<ExpenseTransDetails> ETransdetails =  expensetransdetails.getBody();
		System.out.println("map size is : "+ETransdetails.size());
		for(ExpenseTransDetails ex : ETransdetails) {
			TransactionDTO traansdetails = new TransactionDTO();
			traansdetails.setType("Expense");
			traansdetails.setCategory(ex.getCategory());
			traansdetails.setDescription(ex.getDescription());
			traansdetails.setDate(ex.getExpenseDate());
			traansdetails.setAmount(ex.getAmount());
			transactionls.add(traansdetails);
		}
		ResponseEntity<List<IncomeTransDetails>> incometransdetails = incomeClient.getIncomeTransDetails(email, fromdate, todate);
		List<IncomeTransDetails> ITransdetails =  incometransdetails.getBody();
		System.out.println("map size is : "+ITransdetails.size());
		for(IncomeTransDetails in : ITransdetails) {
			TransactionDTO traansdetails = new TransactionDTO();
			traansdetails.setType("Income");
			traansdetails.setCategory(in.getSource());
			traansdetails.setDescription(in.getDescription());
			traansdetails.setDate(in.getIncomeDate());
			traansdetails.setAmount(in.getAmount());
			transactionls.add(traansdetails);
		}
		
		model.addAttribute("fromDate", fromdate);
		model.addAttribute("toDate", todate);
		model.addAttribute("totalIncome", totincome);
		model.addAttribute("totalExpense", totexpense);
		model.addAttribute("netBalance", netBalance);
		model.addAttribute("catdetails", catdetails);
		model.addAttribute("recentTransactions",transactionls);
	}
	
}
