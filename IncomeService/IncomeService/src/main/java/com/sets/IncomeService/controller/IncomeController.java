package com.sets.IncomeService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sets.IncomeService.model.IncomeDTO;
import com.sets.IncomeService.model.IncomeDetails;
import com.sets.IncomeService.service.IncomeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/income")
public class IncomeController {
	
	@Autowired
	private IncomeService incomeService;
	
	  @GetMapping
	    public String showLandingPage(HttpServletRequest request) {
		  System.out.println("[Controller] showLandingPage called!");
	        return "income-dashboard";
	    }
	  
	//add income
	@GetMapping("/add-income")
	public String returnAddIncomePage(Model model)
	{
		model.addAttribute("income", new IncomeDTO());
		return "add-income";
	}
	
	@PostMapping("/add-income")
	public String addIncome(@ModelAttribute IncomeDTO incomeDTO,Model model) {
		
//		TODO remove the hard coded   value 
//		incomeDTO.setEmail("jothikamohan98@gmail.com");
		String msg =  incomeService.addIncome(incomeDTO);
		System.out.println("msg : "+msg);
		model.addAttribute("income", new IncomeDTO());
		return "add-income";
	}

//	update income
	@GetMapping("/update-income")
	public String returnUpdateIncomePage(Model model) {
		model.addAttribute("search", new IncomeDTO());
		return "update-income";
	}
	
	@PostMapping("/search")
	public String updateIncome(@RequestParam("email") String email, Model model) {
		List<IncomeDetails> incomeDTOs = incomeService.fetchIncomeDetails(email);
		model.addAttribute("incomeList", incomeDTOs);
		 return "update-income";
	}
	
	@GetMapping("/edit/{id}")
	public String editIncome(@PathVariable Long id, Model model) {
		System.out.println("inside edit income : "+id);
		 IncomeDetails income = incomeService.getIncomeById(id);//TODO msg box for not found
		 model.addAttribute("income", income);
	    return "edit-income";
	}
	
	@PostMapping("/update")
	public String updateIncomeDB(@ModelAttribute IncomeDTO incomeDTO,RedirectAttributes redirectAttributes) {
//		TODO remove the hard coded   value 
		incomeDTO.setEmail("jothikamohan98@gmail.com");
		String msg = incomeService.updateIncomeDB(incomeDTO);
		 redirectAttributes.addFlashAttribute("showSuccessModal", true);
		 redirectAttributes.addFlashAttribute("successMessage", msg);
		return "redirect:/income/edit/" + incomeDTO.getId();
	}
	
	//delete 
	@GetMapping("/delete-income")
	public String showDeletePage(Model model) {
		model.addAttribute("search", new IncomeDTO());
		return "delete-income";
	}
	@PostMapping("/search-delete")
	public String showIncomeToDelete(@RequestParam("email") String email, Model model) {
		List<IncomeDetails> incomeDTOs = incomeService.fetchIncomeDetails(email);
		model.addAttribute("incomes", incomeDTOs);
		 return "delete-income";
	}
	@PostMapping("/delete-byId")
	public String deleteIncome(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		System.out.println("inside edit income : "+id);
		String msg = incomeService.deleteIncomeById(id);
		redirectAttributes.addFlashAttribute("successMessage", msg);
//		 model.addAttribute("income", income);
		return "redirect:/income/delete";
	}
	
	//view details
		@GetMapping("/view-income")
		public String showViewPage(Model model) {
			List<IncomeDTO> incomels = incomeService.fetchAllDatas("jothikamohan98@gmail.com");
			model.addAttribute("incomes", incomels);
			return "view-income";
		}
}
