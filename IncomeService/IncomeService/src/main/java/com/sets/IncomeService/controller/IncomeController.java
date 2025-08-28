package com.sets.IncomeService.controller;

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

import com.sets.IncomeService.model.Globals;
import com.sets.IncomeService.model.IncomeDTO;
import com.sets.IncomeService.model.IncomeTransDetails;
import com.sets.IncomeService.service.IncomeCalService;
import com.sets.IncomeService.service.IncomeService;

@Controller
@RequestMapping("/income")
public class IncomeController {
	
	@Autowired
	private IncomeService incomeService;
	
	@Autowired
	private IncomeCalService incomeCalService;
	
	@Autowired
	private Globals globals;
	
	@Value("${custom.gateway-url}")
    private String gatewayUrl;
	
	@GetMapping
	    public String showLandingPage(Model model) {
		
		  List<IncomeDTO> ls = incomeService.fetchAllDatas(globals.getCurrentuser());
		  model.addAttribute("incomes",ls);
	      return "income-dashboard";
	    }
	  
	@PostMapping("/add-income")
	public String addIncome(@ModelAttribute IncomeDTO incomeDTO,Model model) {
		
		String msg =  incomeService.addIncome(incomeDTO);
		System.out.println("msg : "+msg);
		model.addAttribute("income", new IncomeDTO());
		return "redirect:" + gatewayUrl + "/income";
	}
	
	@PostMapping("/update")
	public String updateIncomeDB(@ModelAttribute IncomeDTO incomeDTO) {
		String msg = incomeService.updateIncomeDB(incomeDTO);
		System.out.println("msg  : "+msg);
		return  "redirect:" + gatewayUrl + "/income";
	}
	
	@PostMapping("/delete")
	public String deleteIncome(@RequestParam("id") Long id) {
		System.out.println("inside edit income : "+id);
		String msg = incomeService.deleteIncomeById(id);
		System.out.println("msg  : "+msg);
		return  "redirect:" + gatewayUrl + "/income";
	}
	
	@GetMapping("/gettotalincome")
	public ResponseEntity<BigDecimal> getTotalIncome(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate) {
		BigDecimal total = incomeCalService.TotalIncome(email, fromdate, todate);
		return ResponseEntity.ok(total);
	}
	
	@GetMapping("/getincometransdetails")
	public ResponseEntity<List<IncomeTransDetails>> getIncomeTransDetails(@RequestParam("email") String email,
									  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate) {
		System.out.println("inside getIncomeTransDetails");
		List<IncomeTransDetails> map = incomeCalService.getIncomeTransDetails(email, fromdate, todate);
		return ResponseEntity.ok(map);
		}
	
}
