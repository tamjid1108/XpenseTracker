package com.example.tj.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="budget")
public class Budget implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long budgetId;
	
	@Id
	private LocalDate budgetFor;
	
	private Double budgetAmount;
	
	@ManyToOne
	private User user;
	
	public Budget() {
		super();
	}

	public Budget(Long budgetId, LocalDate budgetFor, Double budgetAmount, User user) {
		super();
		this.budgetId = budgetId;
		this.budgetFor = budgetFor;
		this.budgetAmount = budgetAmount;
		this.user = user;
	}

	public Long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}

	public LocalDate getBudgetFor() {
		return budgetFor;
	}

	public void setBudgetFor(LocalDate budgetFor) {
		this.budgetFor = budgetFor;
	}

	public Double getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}


//LocalDate today = LocalDate.now();
//System.out.println("First day: " + today.withDayOfMonth(1));
//System.out.println("Last day: " + today.withDayOfMonth(today.lengthOfMonth()));