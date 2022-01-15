package com.cg.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.entity.Category;
import com.cg.app.exceptions.CategoryException;
import com.cg.app.repository.ICategoryRepository;

@Service
public class ICategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryRepository catRepo;
	
	
	@Override
	public Category addCategory(Category category) {
		
		return catRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
	
		if(catRepo.existsById(category.getCatId())) {
			return catRepo.save(category);
		}
		throw new CategoryException("Category does not exist");
		
		
	}

	@Override
	public Category removeCategory(String catName) throws CategoryException {
		
		Category category = catRepo.findByCategoryName(catName);
	
		if(category == null)
			throw new CategoryException("No Category found with name :"+catName);
		else {
			
			catRepo.delete(category);
			return category;
		}
	}

	@Override
	public Category viewCategory(String catName) throws CategoryException {
	
		Category category = catRepo.findByCategoryName(catName);
		
		if(category == null)
			throw new CategoryException("No Category found with name :"+catName);
		else
			return category;
	}

	@Override
	public List<Category> viewAllCategory() throws CategoryException {
		List<Category> catList = catRepo.findAll();
		if(catList.size()>0)
			return catList;
		throw new CategoryException("Category not found");
	}

	
	
	
}
