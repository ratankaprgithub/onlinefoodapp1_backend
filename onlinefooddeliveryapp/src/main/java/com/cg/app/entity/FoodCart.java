package com.cg.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="foodCart")
	@JsonIgnore
	private List<Item> itemList=new ArrayList<Item>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	@ElementCollection
	@CollectionTable(name="Item_quantity")
	@MapKeyJoinColumn(name = "itemId")
	@Column(name="Quantity")
	Map<Item,Integer> item_cartQty = new HashMap<Item,Integer>();
	


}
