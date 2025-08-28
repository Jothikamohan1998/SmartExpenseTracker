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
import com.sets.RegisterLoginService.model.IncomeTransDetails;

@FeignClient(name="IncomeService",configuration = FeignConfig.class)
public interface IncomeClient {

	@GetMapping("/income/gettotalincome")
	ResponseEntity<BigDecimal> getTotalIncome(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate);
	
	@GetMapping("/income/getincometransdetails")
	public ResponseEntity<List<IncomeTransDetails>> getIncomeTransDetails(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate);
}
