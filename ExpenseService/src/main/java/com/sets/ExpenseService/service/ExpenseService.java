package com.sets.ExpenseService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sets.ExpenseService.dao.ExpenseDAO;
import com.sets.ExpenseService.model.ExpenseDTO;
import com.sets.ExpenseService.model.ExpenseDetails;
import com.sets.ExpenseService.model.Globals;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseDAO expenseDao;
	
	@Autowired
	private Globals globals;
	
	public List<ExpenseDTO> fetchAllExpense(String currentuser) {
		List<ExpenseDetails> ls = expenseDao.findByEmail(currentuser);
		List<ExpenseDTO> exls = new ArrayList<>();
		for(ExpenseDetails ex : ls) {
			ExpenseDTO expenseDTO = new ExpenseDTO();
			expenseDTO.setId(ex.getId());
			expenseDTO.setCategory(ex.getCategory());
			expenseDTO.setAmount(ex.getAmount());
			expenseDTO.setDescription(ex.getDescription());
			expenseDTO.setExpenseDate(ex.getExpenseDate());
			exls.add(expenseDTO);
		}
		return exls;
	}
	
	public String addExpense(ExpenseDTO expenseDTO) {
		ExpenseDetails expenseDetails = new ExpenseDetails();
		expenseDetails.setEmail(globals.getCurrentuser());
		expenseDetails.setCategory(expenseDTO.getCategory());
		expenseDetails.setAmount(expenseDTO.getAmount());
		expenseDetails.setExpenseDate(expenseDTO.getExpenseDate());
		expenseDetails.setDescription(expenseDTO.getDescription());
		expenseDao.save(expenseDetails);
		return  "expense details added";
	}

	public String updateExpenseDB(ExpenseDTO expenseDTO) {
		ExpenseDetails expenseDetails = expenseDao.findById(expenseDTO.getId()).orElse(null);
		expenseDetails.setCategory(expenseDTO.getCategory());
		expenseDetails.setEmail(globals.getCurrentuser());
		expenseDetails.setAmount(expenseDTO.getAmount());
		expenseDetails.setExpenseDate(expenseDTO.getExpenseDate());
		expenseDetails.setDescription(expenseDTO.getDescription());
		expenseDao.save(expenseDetails);
		return null;
	}

	public String deleteExpenseById(Long id) {
		ExpenseDetails expenseDetails = expenseDao.findById(id).orElse(null);
		if(expenseDetails==null) {
			return "Record not found";
		}
		expenseDao.deleteById(id);
		return "Record Deleted";
	}

}
