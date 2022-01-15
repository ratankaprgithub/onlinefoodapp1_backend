package com.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.app.entity.Item;

@Repository
public interface IItemRepository extends JpaRepository<Item,Integer>{
	

}
