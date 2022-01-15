package com.cg.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;
	
	private String itemName;
	
	private Integer quantity;
	
	private Double cost;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "itemList")
	private List<Restaurant> restaurants=new ArrayList<Restaurant>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private FoodCart foodCart;

}
