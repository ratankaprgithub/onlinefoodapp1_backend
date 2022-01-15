package com.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.app.entity.Bill;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Integer>{
	

}
