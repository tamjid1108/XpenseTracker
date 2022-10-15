package com.example.tj.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tj.model.Category;
import com.example.tj.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
	private CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping("/all")
	Collection<Category> categories(){
		return this.categoryRepository.findAll();
	}
	
	@GetMapping("/{categoryId}")
	ResponseEntity<?> getCategoryById(@PathVariable Long categoryId) {
		Optional<Category> category = this.categoryRepository.findById(categoryId);
		return category.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/add")
	ResponseEntity<Category> addCategory(@Validated @RequestBody Category category) throws URISyntaxException{
		Category result = categoryRepository.save(category);
		return ResponseEntity.created(new URI("/category/" + result.getCategoryId())).body(result);
	}
	
	@PutMapping("/update")
	ResponseEntity<Category> updateCategory(@Validated @RequestBody Category category) throws URISyntaxException{
		Category result = categoryRepository.save(category);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
		this.categoryRepository.deleteById(categoryId);
		return ResponseEntity.ok().build();
	}
	
}
