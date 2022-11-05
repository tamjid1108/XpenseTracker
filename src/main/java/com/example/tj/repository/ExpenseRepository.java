package com.example.tj.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tj.model.Expense;
import com.example.tj.model.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	Collection<Expense> findAllByUser(User user);
	Collection<Expense> findAllByExpenseDateBetween(LocalDate start, LocalDate end);
//	Collection<> countTotalExpenseAmountByCategory();
	Collection<Expense> findAllByUserOrderByExpenseDateDesc(User user);
	
}
