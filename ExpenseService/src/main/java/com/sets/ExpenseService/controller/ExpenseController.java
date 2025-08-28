package com.sets.ExpenseService.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sets.ExpenseService.model.CategoryDetails;
import com.sets.ExpenseService.model.ExpenseDTO;
import com.sets.ExpenseService.model.ExpenseTransDetails;
import com.sets.ExpenseService.model.Globals;
import com.sets.ExpenseService.service.ExpenseCalService;
import com.sets.ExpenseService.service.ExpenseService;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseservice;
	
	@Autowired
	private ExpenseCalService expenseCalService;
	
	@Autowired
	private Globals globals;
	
	@Value("${custom.gateway-url}")
    private String gatewayUrl;
	
	@GetMapping
	public String showLandingPage(Model model) {
		
		List<ExpenseDTO> ls = expenseservice.fetchAllExpense(globals.getCurrentuser());
		model.addAttribute("expenses", ls);
		return "expense-dashboard";
	}
	
	@PostMapping("add-expense")
	public String addExpenses(@ModelAttribute ExpenseDTO expenseDTO,Model model) {
		
		String msg = expenseservice.addExpense(expenseDTO);
		System.out.println(msg);
		model.addAttribute("expenses", new ExpenseDTO());
		return "redirect:" + gatewayUrl + "/expense";
	}
	
	@PostMapping("/update")
	public String updateIncomeDB(@ModelAttribute ExpenseDTO expenseDTO) {
		String msg = expenseservice.updateExpenseDB(expenseDTO);
		System.out.println("msg  : "+msg);
		return  "redirect:" + gatewayUrl + "/expense";
	}
	
	@PostMapping("/delete")
	public String deleteIncome(@RequestParam("id") Long id) {
		System.out.println("inside edit income : "+id);
		String msg = expenseservice.deleteExpenseById(id);
		System.out.println("msg  : "+msg);
		return  "redirect:" + gatewayUrl + "/expense";
	}
	
	@GetMapping("/gettotalexpense")
	public ResponseEntity<BigDecimal> getTotalExpense(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate) {
		BigDecimal total = expenseCalService.TotalExpense(email, fromdate, todate);
		return ResponseEntity.ok(total);
	}
	@GetMapping("/getallcategoryexpense")
	public ResponseEntity<List<CategoryDetails>> getAllCategoryExpense(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate) {
		System.out.println("inside getallcategoryexpense");
		List<CategoryDetails> map = expenseCalService.getAllCategoryExpense(email, fromdate, todate);
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/getexpensetransdetails")
	public ResponseEntity<List<ExpenseTransDetails>> getExpenseTransDetails(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate) {
		System.out.println("inside getExpenseTransDetails");
		List<ExpenseTransDetails> map = expenseCalService.getExpenseTransDetails(email, fromdate, todate);
		return ResponseEntity.ok(map);
		}
	
}
