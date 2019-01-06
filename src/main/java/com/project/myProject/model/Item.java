package com.project.myProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String name;
	Integer amount;
	Double inventorycode;

	public Item() {}
	
	public Item(Integer id,String name,Integer amount,Double inventorycode) {
		this.id=id;
		this.name=name;
		this.amount=amount;
		this.inventorycode=inventorycode;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getInventorycode() {
		return inventorycode;
	}

	public void setInventorycode(Double inventorycode) {
		this.inventorycode = inventorycode;
	}

}



