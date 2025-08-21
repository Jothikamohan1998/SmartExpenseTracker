package com.sets.IncomeService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sets.IncomeService.dao.IncomeDAO;
import com.sets.IncomeService.model.IncomeDTO;
import com.sets.IncomeService.model.IncomeDetails;

@Service
public class IncomeService {

	@Autowired
	private IncomeDAO incomeDAO;
	
	
	public String addIncome(IncomeDTO incomeDTO) {
		String useremail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("datas in service : "+useremail+"  "+incomeDTO.getIncomeDate()+" "+incomeDTO.getAmount());
		IncomeDetails incomeDetails = new IncomeDetails();
		incomeDetails.setSource(incomeDTO.getSource());
		incomeDetails.setEmail(useremail);
		incomeDetails.setDescription(incomeDTO.getDescription());
		incomeDetails.setAmount(incomeDTO.getAmount());
		incomeDetails.setIncomeDate(incomeDTO.getIncomeDate());
		incomeDetails.setCreatedat(LocalDateTime.now());
		incomeDetails.setUpdatedat(LocalDateTime.now());
		incomeDAO.save(incomeDetails);
		return "income details added";
	}

	public List<IncomeDetails> fetchIncomeDetails(String email) {
		List<IncomeDetails> incomeDetails = incomeDAO.findByEmail(email);
		return incomeDetails;
	}


	public IncomeDetails getIncomeById(Long id) {
		IncomeDetails incomeDetails = incomeDAO.findById(id).orElse(null);
		System.out.println("date : "+incomeDetails.getIncomeDate());
		return incomeDetails;
	}

	public String updateIncomeDB(IncomeDTO incomeDTO) {
		System.out.println("getAmount : "+incomeDTO.getAmount()+" id "+incomeDTO.getId());
		IncomeDetails incomeDetails = incomeDAO.findById(incomeDTO.getId()).orElse(null);
		if(incomeDetails==null) {
			return "Record not found";
		}
		incomeDetails.setSource(incomeDTO.getSource());
		incomeDetails.setEmail(incomeDTO.getEmail());
		incomeDetails.setDescription(incomeDTO.getDescription());
		incomeDetails.setAmount(incomeDTO.getAmount());
		incomeDetails.setIncomeDate(incomeDTO.getIncomeDate());
		incomeDetails.setUpdatedat(LocalDateTime.now());
		incomeDAO.save(incomeDetails);
		return "User details updated successfully!!";
	}

	public String deleteIncomeById(Long id) {
		IncomeDetails incomeDetails = incomeDAO.findById(id).orElse(null);
		if(incomeDetails==null) {
			return "Record not found";
		}
		incomeDAO.deleteById(id);
		return "Record Deleted";
	}

	public List<IncomeDTO> fetchAllDatas(String email) {
		List<IncomeDetails> ls = incomeDAO.findByEmail(email);
		List<IncomeDTO> incomeDTOls =  new ArrayList<>();
		for(IncomeDetails income : ls) {
			IncomeDTO incomeDTO  = new IncomeDTO();
			incomeDTO.setSource(income.getSource());
			incomeDTO.setAmount(income.getAmount());
			incomeDTO.setDescription(income.getDescription());
			System.out.println("date : "+income.getIncomeDate());
			incomeDTO.setIncomeDate(income.getIncomeDate());
			incomeDTOls.add(incomeDTO);
			
		}
		System.out.println("list  >  "+ls.size());
		return incomeDTOls;
	}	
}
