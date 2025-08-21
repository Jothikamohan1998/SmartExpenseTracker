package com.sets.IncomeService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sets.IncomeService.dao.IncomeDAO;
import com.sets.IncomeService.model.Globals;
import com.sets.IncomeService.model.IncomeDTO;
import com.sets.IncomeService.model.IncomeDetails;

@Service
public class IncomeService {

	@Autowired
	private IncomeDAO incomeDAO;
	
	@Autowired
	private Globals globals;
	
	public String addIncome(IncomeDTO incomeDTO) {
		System.out.println("datas in service : "+globals.getCurrentuser()+"  "+incomeDTO.getIncomeDate()+" "+incomeDTO.getAmount());
		IncomeDetails incomeDetails = new IncomeDetails();
		incomeDetails.setSource(incomeDTO.getSource());
		incomeDetails.setEmail(globals.getCurrentuser());
		incomeDetails.setDescription(incomeDTO.getDescription());
		incomeDetails.setAmount(incomeDTO.getAmount());
		incomeDetails.setIncomeDate(incomeDTO.getIncomeDate());
		incomeDetails.setCreatedat(LocalDateTime.now());
		incomeDetails.setUpdatedat(LocalDateTime.now());
		incomeDAO.save(incomeDetails);
		return "income details added";
	}

	public String updateIncomeDB(IncomeDTO incomeDTO) {
		System.out.println("getAmount : "+incomeDTO.getAmount()+" id "+incomeDTO.getId());
		IncomeDetails incomeDetails = incomeDAO.findById(incomeDTO.getId()).orElse(null);
		incomeDetails.setSource(incomeDTO.getSource());
		incomeDetails.setEmail(globals.getCurrentuser());
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
			incomeDTO.setId(income.getId());
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
