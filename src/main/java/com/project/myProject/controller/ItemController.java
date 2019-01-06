package com.project.myProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.myProject.model.Item;
import com.project.myProject.repository.ItemRepository;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("api/v1/")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	//test
	@RequestMapping(value="Hello",method=RequestMethod.GET)
	public String helloWorld(){
		return "test";
	}	
	
	
	//GET LIST
	@ApiOperation(value="Returns the inventory list")
	@RequestMapping(value="Items",method=RequestMethod.GET)
	public List<Item> list(){
		return itemRepository.findAll();
	}
	
	
	//CREATE ITEAM
	@ApiOperation(value="Add a new item to the inventory list")
	@RequestMapping(value="Items",method=RequestMethod.POST)
	public Item create(@RequestBody Item item){
		return itemRepository.saveAndFlush(item);
	}
	
	//GET ITEAM
	@ApiOperation(value="Returns item details by item-ID")
	@RequestMapping(value="Items/{id}",method=RequestMethod.GET)
	public Item get(@PathVariable Integer id){
		if (!isValidArgs(id)) {
			return null;}
		return itemRepository.getOne(id);
	}
	
	
	//DELETE ITEAM
	@ApiOperation(value="Delete item by item-ID")
	@RequestMapping(value="Items/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id){
		if (!isValidArgs(id)) {
			return "Operation failed.\nThe given item-id doesn't exist";}
		
		itemRepository.delete(itemRepository.getOne(id));
		return "Successfully deleted item.";
	}
	
	
	//DEPOSIT
	@ApiOperation(value="Deposit quantity of a specific item to stock")
	@RequestMapping(value="Items/deposit/{id}/{amount}",method=RequestMethod.PUT)
	public String deposit(@PathVariable Integer id,@PathVariable Integer amount){
		if (!isValidArgs(id,amount)) {
			return "Operation failed.\nInvalid args.please try again.";}
		
		Item item=itemRepository.getOne(id);
		item.setAmount(item.getAmount()+amount);
		itemRepository.saveAndFlush(item);
		return "Your deposit was successful.";		
	}
	
	
	//WITHDRAWAL
	@ApiOperation(value="Withdrawal quantity of a specific item from stock")
	@RequestMapping(value="Items/withdrawal/{id}/{amount}",method=RequestMethod.PUT)
	public String withdrawal(@PathVariable Integer id, @PathVariable Integer amount){
		if (!isValidArgs(id,amount)) {
			return "Operation failed.\nInvalid args.please try again.";}
		
		Item item=itemRepository.getOne(id);
		int newAmount=item.getAmount()-amount;
		
		if (newAmount<0) {
			return "Operation failed.\nThe requested quantity is not available";}
		
		else if (newAmount==0) {
			itemRepository.delete(item);
			return "Your withdrawal was successful.\n Note: Now this item is out of stock.";}
		
		item.setAmount(newAmount);
		itemRepository.saveAndFlush(item);
		return "Your withdrawal was successful.";		
	}
	
	
	//HELP METHODS
	public boolean isValidArgs(Integer id) {
		return (itemRepository.findById(id).isPresent());
	}
	public boolean isValidArgs(Integer id,Integer amount) {
		return (amount>0 && itemRepository.findById(id).isPresent());
	}
	

}
