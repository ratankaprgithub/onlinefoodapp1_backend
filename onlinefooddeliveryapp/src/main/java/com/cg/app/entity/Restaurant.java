package com.cg.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;
	
	private String restaurantName;
	
	private String managerName;
	
	private String contactNumber;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Item> itemList=new ArrayList<Item>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

}
