package com.example.tj.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tj.model.Category;
import com.example.tj.model.Expense;
import com.example.tj.model.SecurityUser;
import com.example.tj.repository.CategoryRepository;
import com.example.tj.repository.ExpenseRepository;
import com.example.tj.repository.UserRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	private ExpenseRepository expenseRepository;
	private CategoryRepository categoryRepository;
	
	public HomeController(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
		super();
		this.expenseRepository = expenseRepository;
		this.categoryRepository = categoryRepository;
	}



	@GetMapping("/")
	public String index(Model model, @AuthenticationPrincipal SecurityUser user) {
		model.addAttribute("username", user.getUsername());
		model.addAttribute("name", user.getUser().getName());
		
	
		Collection<Category> categories= this.categoryRepository.findAll();
		List<String> catOptions= new ArrayList<String>();
		for(Category c: categories) {
			catOptions.add(c.getCategoryName());
		}
		model.addAttribute("catOptions", catOptions);
		
		Collection<Expense> result = this.expenseRepository.findAllByUser(user.getUser());
		
		HashMap<String,Double> expensesByCategory = new HashMap<String, Double>();
		
		for(Expense e: result) {
			String catName = e.getCategory().getCategoryName();
			Double amount = e.getExpenseAmount();
			expensesByCategory.put(catName, expensesByCategory.getOrDefault(catName, 0D) + amount);
		}
		model.addAttribute("expenses", expensesByCategory);
		
		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().minusDays(30);
		
		HashMap<LocalDate,Double> expensesByDate = new HashMap<LocalDate, Double>();
		
		for(Expense e: result) {
			if(e.getExpenseDate().compareTo(start)<=0 && e.getExpenseDate().compareTo(end)>=0) {
				LocalDate expDate = e.getExpenseDate();
				Double amount = e.getExpenseAmount();
				expensesByDate.put(expDate, expensesByDate.getOrDefault(expDate, 0D) + amount);
			}
		}
		
		model.addAttribute("expensesByDate", expensesByDate);
		
		return "index";
	}
	
	@GetMapping("/expenses")
	public String expenses(Model model, @AuthenticationPrincipal SecurityUser user) {
		model.addAttribute("username", user.getUsername());
		model.addAttribute("name", user.getUser().getName());
		
		Collection<Expense> result = this.expenseRepository.findAllByUserOrderByExpenseDateDesc(user.getUser());
		System.out.println(result.size());
		
		HashMap<String, List<Expense>> allExpensesChron = new LinkedHashMap<String, List<Expense>>();
		LocalDate tdy = LocalDate.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		
		
		for(Expense e: result) {
			if(e.getExpenseDate().compareTo(tdy)==0) {
				if (!allExpensesChron.containsKey("Today")) {
					allExpensesChron.put("Today", new ArrayList<Expense>());
		        }
				allExpensesChron.get("Today").add(e);
			}
			
			else if(e.getExpenseDate().compareTo(tdy)==-1) {
				if (!allExpensesChron.containsKey("Yesterday")) {
					allExpensesChron.put("Yesterday", new ArrayList<Expense>());
		        }
				allExpensesChron.get("Yesterday").add(e);
			}
			
			else {
				String date = e.getExpenseDate().format(formatter);
				if (!allExpensesChron.containsKey(date)) {
					allExpensesChron.put(date, new ArrayList<Expense>());
		        }
				allExpensesChron.get(date).add(e);
			}
		}
		
		model.addAttribute("allExpensesChron", allExpensesChron);
		return "expenses";
	}
	
}
