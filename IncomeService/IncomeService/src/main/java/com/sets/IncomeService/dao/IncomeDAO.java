package com.sets.IncomeService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sets.IncomeService.model.IncomeDetails;
@Repository
public interface IncomeDAO extends JpaRepository<IncomeDetails, Long>{

	List<IncomeDetails> findByEmail(String email);

}
