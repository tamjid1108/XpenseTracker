package com.example.tj.model;

import java.time.Instant;

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
@Table(name="expense")
public class Expense {
	@Id
	private Long expenseId;
	
	private String description;
	
	private Instant expenseDate;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
}
