package com.sets.IncomeService.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sets.IncomeService.model.IncomeDetails;
import com.sets.IncomeService.model.IncomeTransDetails;
@Repository
public interface IncomeDAO extends JpaRepository<IncomeDetails, Long>{

	List<IncomeDetails> findByEmail(String email);

	@Query("SELECT SUM(i.amount) FROM IncomeDetails i where i.email = :email AND i.incomeDate BETWEEN :fromDate AND :toDate")
	BigDecimal findAllByFilter(@Param("email") String email, 
						@Param("fromDate") LocalDate fromDate,
						@Param("toDate") LocalDate toDate);
	
	@Query("SELECT new com.sets.IncomeService.model.IncomeTransDetails(i.source,i.incomeDate,i.description,SUM(i.amount)) FROM IncomeDetails i where i.email = :email AND i.incomeDate BETWEEN :fromDate AND :toDate GROUP BY i.source, i.incomeDate, i.description")
	List<IncomeTransDetails> findIncomeTransDetails(@Param("email") String email, 
						@Param("fromDate") LocalDate fromDate,
						@Param("toDate") LocalDate toDate);

}
