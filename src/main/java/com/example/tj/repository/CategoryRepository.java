package com.example.tj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tj.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findByCategoryName(String name);

}
