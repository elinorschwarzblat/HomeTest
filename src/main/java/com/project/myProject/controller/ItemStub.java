package com.project.myProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.myProject.model.Item;


public class ItemStub {
	private static Map<Integer, Item> items = new HashMap<Integer, Item>();
	private static Integer idIndex = 3;
	
	//populate initial wrecks
	static {
		Item a = new Item(1, "dress", 5, (double)119901);
		items.put(1, a);
		Item b = new Item(2, "pants", 40, (double)119902);
		items.put(2, b);
		Item c = new Item(3, "shoes", 18, (double)119903);
		items.put(3, c);
	}
	
	//List of inventory items list 
	public static List<Item> list() {
		return new ArrayList<Item>(items.values());
	}
	
	//Add item to stock
	public static Item create(Item item) {
		idIndex += idIndex;
		item.setId(idIndex);
		items.put(idIndex, item);
		return item;
	}
	
	//Read item details
	public static Item get(Integer id) {
		return items.get(id);
	}

	public static Item update(Integer id, Item item) {
		items.put(id, item);
		return item;
	}
	
	//Delete an item from stock
	public static Item delete(Integer id) {
		return items.remove(id);
	}
	
	//Withdrawal quantity of a specific item from stock
	public static boolean withdrawal(Integer id, Integer amount) {
		Item item=items.get(id);
		if (item!=null && item.getAmount()>=amount) {
			item.setAmount(item.getAmount()-amount);
			return true;
		}
		return false;	
	}
	
	//Deposit quantity of a specific item to stock
	public static boolean deposit(Integer id, Integer amount) {
		Item item=items.get(id);
		if (item!=null) {
			item.setAmount(item.getAmount()+amount);
			return true;
		}
		return false;	
	}
	
		
	
}
