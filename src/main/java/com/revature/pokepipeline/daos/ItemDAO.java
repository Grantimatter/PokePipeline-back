package com.revature.pokepipeline.daos;

import java.util.List;

import com.revature.pokepipeline.models.Item;

public interface ItemDAO {
	
	public void insertItem(Item item);
	public void updateItem(Item item);
	public Item getItemById(int itemId);
	public List<Item> getAllItems();

}
