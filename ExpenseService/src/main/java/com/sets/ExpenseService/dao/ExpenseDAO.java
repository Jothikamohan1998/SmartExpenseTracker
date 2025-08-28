package com.sets.ExpenseService.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sets.ExpenseService.model.CategoryDetails;
import com.sets.ExpenseService.model.ExpenseDetails;
import com.sets.ExpenseService.model.ExpenseTransDetails;

@Repository
public interface ExpenseDAO extends JpaRepository<ExpenseDetails, Long>{

	List<ExpenseDetails> findByEmail(String currentuser);

	@Query("SELECT SUM(e.amount) FROM ExpenseDetails e where e.email = :email  AND e.expenseDate BETWEEN :fromDate AND :toDate")
	BigDecimal findAllByFilter(@Param("email") String email, 
						       @Param("fromDate") LocalDate fromDate,
						       @Param("toDate") LocalDate toDate);

	
	@Query("SELECT new com.sets.ExpenseService.model.CategoryDetails(e.category, SUM(e.amount))  FROM ExpenseDetails e WHERE e.email = :email AND e.expenseDate BETWEEN :fromDate AND :toDate  GROUP BY e.category")
	List<CategoryDetails> findAllCategoryExpense(@Param("email") String email, 
										  @Param("fromDate") LocalDate fromDate,
										  @Param("toDate") LocalDate toDate);

	@Query("SELECT new com.sets.ExpenseService.model.ExpenseTransDetails(e.category,e.expenseDate,e.description,SUM(e.amount))  FROM ExpenseDetails e WHERE e.email = :email AND e.expenseDate BETWEEN :fromDate AND :toDate  GROUP BY e.category, e.expenseDate, e.description")
	List<ExpenseTransDetails> findExpenseTransDetails(@Param("email") String email, 
			  										  @Param("fromDate") LocalDate fromDate,
			  										  @Param("toDate") LocalDate toDate);
}
