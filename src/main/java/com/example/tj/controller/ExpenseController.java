package com.example.tj.controller;

import java.time.LocalDate;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tj.model.Category;
import com.example.tj.model.Expense;
import com.example.tj.model.SecurityUser;
import com.example.tj.payload.ExpenseDTO;
import com.example.tj.repository.CategoryRepository;
import com.example.tj.repository.ExpenseRepository;
import com.example.tj.repository.UserRepository;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
	
	private ExpenseRepository expenseRepository;
	private CategoryRepository categoryRepository;
	
	

	public ExpenseController(ExpenseRepository expenseRepository, CategoryRepository categoryRepository,
			UserRepository userRepository) {
		super();
		this.expenseRepository = expenseRepository;
		this.categoryRepository = categoryRepository;
	}



	@PostMapping("/add")
	public String addExpense(ExpenseDTO expenseDTO, @AuthenticationPrincipal SecurityUser securityUser) {
		
		Expense expense = new Expense();
		expense.setDescription(expenseDTO.getDescription());
		
		Category category = this.categoryRepository.findByCategoryName(expenseDTO.getCategory());
		expense.setCategory(category);
		
		expense.setExpenseDate(LocalDate.parse(expenseDTO.getExpenseDate()));
		
		expense.setExpenseAmount(expenseDTO.getExpenseAmount());
		
		expense.setExpenseType(expenseDTO.getExpenseType());
		
		expense.setUser(securityUser.getUser());
		
		this.expenseRepository.save(expense);
		return "redirect:/";
	}
	
	@PostMapping("/delete/{expenseId}")
	public String deleteExpense(@PathVariable Long expenseId) {
		this.expenseRepository.deleteById(expenseId);
		return "redirect:/expenses";
	}
	
	
}
