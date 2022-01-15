package com.cg.app.service;

import java.util.List;

import com.cg.app.entity.Category;
import com.cg.app.exceptions.CategoryException;


public interface ICategoryService {
	public Category addCategory(Category category); 
	public Category updateCategory(Category category) throws CategoryException;
	public Category removeCategory(String catName) throws CategoryException;
	public Category viewCategory(String catName) throws CategoryException;
	public List<Category> viewAllCategory() throws CategoryException;  

}
