package com.sets.RegisterLoginService.feignClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sets.RegisterLoginService.configuration.FeignConfig;
import com.sets.RegisterLoginService.model.CategoryDetails;
import com.sets.RegisterLoginService.model.ExpenseTransDetails;

@FeignClient(name="ExpenseService",configuration = FeignConfig.class)
public interface ExpenseClient {
	
	@GetMapping("/expense/gettotalexpense")
	ResponseEntity<BigDecimal> getTotalExpense(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate);
	
	@GetMapping("/expense/getallcategoryexpense")
	public ResponseEntity<List<CategoryDetails>> getAllCategoryExpense(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate);
		
	@GetMapping("/expense/getexpensetransdetails")
	public ResponseEntity<List<ExpenseTransDetails>> getExpenseTransDetails(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate);
}
