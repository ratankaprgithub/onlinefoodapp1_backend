package com.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.app.entity.FoodCart;

@Repository
public interface ICartRepository extends JpaRepository<FoodCart,Integer>{
	

}
