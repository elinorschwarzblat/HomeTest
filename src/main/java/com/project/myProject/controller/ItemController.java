package com.project.myProject.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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

	@ApiOperation(value="Returns the inventory list")
	@RequestMapping(value="Items",method=RequestMethod.GET)
	public List<Item> list(){
		return itemRepository.findAll();
	}
	
	@ApiOperation(value="Add a new item to the inventory list")
	@RequestMapping(value="Items",method=RequestMethod.POST)
	public Item create(@RequestBody Item item){
		return itemRepository.saveAndFlush(item);
		//return ItemStub.create(item);
	}
	
	@ApiOperation(value="Returns item details by item-ID")
	@RequestMapping(value="Items/{id}",method=RequestMethod.GET)
	public Item get(@PathVariable Integer id){
		return itemRepository.getOne(id);
		//return ItemStub.get(id);
	}
	
	@ApiOperation(value="Delete item by item-ID")
	@RequestMapping(value="Items/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		Item item = itemRepository.getOne(id);
		itemRepository.delete(item);
		return;
		//return ItemStub.delete(id);
	}
	
	@ApiOperation(value="Deposit quantity of a specific item to stock")
	@RequestMapping(value="Items/deposit/{id}/{amount}",method=RequestMethod.PUT)
	public Item deposit(@PathVariable Integer id,@PathVariable Integer amount){
		Item item = itemRepository.getOne(id);
		item.setAmount(item.getAmount()+amount);
		return itemRepository.saveAndFlush(item);
		
		//return ItemStub.deposit(id, amount);
	}
	
	@ApiOperation(value="Withdrawal quantity of a specific item from stock")
	@RequestMapping(value="Items/withdrawal/{id}/{amount}",method=RequestMethod.PUT)
	public Item withdrawal(@PathVariable Integer id, @PathVariable Integer amount){
		Item item = itemRepository.getOne(id);
		if (item.getAmount()>=amount) {
		item.setAmount(item.getAmount()-amount);
		return itemRepository.saveAndFlush(item);
		}
		//dont do anything
		return item;
	}

	

}
