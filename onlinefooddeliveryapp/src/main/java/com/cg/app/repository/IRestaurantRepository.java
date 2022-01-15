package com.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.app.entity.Restaurant;

@Repository
public interface IRestaurantRepository  extends JpaRepository<Restaurant,Integer>{

}
