package com.cg.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.app.entity.OrderDetails;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails,Integer>{
	
	@Query("select o from OrderDetais o where o.cart.cartId = (select c.cartId from FoodCart c where c.customer.customerId = :custId)")
	public List<OrderDetails> getOrders(@Param("custId") Integer custId);

}
