package com.cg.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.app.entity.Category;
import com.cg.app.service.ICategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	
	@PostMapping("/add")
	public Category addCategory(@RequestBody Category category){
		return categoryService.addCategory(category);
		
	}
	
	
	@PutMapping("/update")
	public Category updateCategory(@RequestBody Category cat) {
		
		return categoryService.updateCategory(cat);
		
	}
	
	
	@DeleteMapping("/remove/{catName}")
	public Category removeCategory(@PathVariable ("catName") String catName) {
		
		return categoryService.removeCategory(catName);
		
	}
	
	
	@GetMapping("/view/{catName}")
	public Category viewCategory(@PathVariable("catName") String catName){
		
		return categoryService.viewCategory(catName);
		
	}
	
	@GetMapping("/viewall")
	public List<Category> viewAllCategory()
	{
		return  categoryService.viewAllCategory();
			
	}
	
	

}
