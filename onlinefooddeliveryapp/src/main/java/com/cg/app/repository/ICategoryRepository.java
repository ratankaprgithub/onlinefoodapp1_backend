package com.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.app.entity.Category;
@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer>{
	
	public Category findByCategoryName(String catName);
	
}
