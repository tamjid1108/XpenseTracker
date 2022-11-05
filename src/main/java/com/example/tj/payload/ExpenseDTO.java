package com.example.tj.payload;

import lombok.Data;


@Data
public class ExpenseDTO {
	
	private String description;
	
	private String expenseDate;
	
	private String expenseType;
	
	private Double expenseAmount;
	
	private String category;
	
	

	public ExpenseDTO() {
		super();
	}



	public ExpenseDTO(String description, String expenseDate, String expenseType, Double expenseAmount,
			String category) {
		super();
		this.description = description;
		this.expenseDate = expenseDate;
		this.expenseType = expenseType;
		this.expenseAmount = expenseAmount;
		this.category = category;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getExpenseDate() {
		return expenseDate;
	}



	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}



	public String getExpenseType() {
		return expenseType;
	}



	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}



	public Double getExpenseAmount() {
		return expenseAmount;
	}



	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
}
