package com.revature.pokepipeline.repos;

import java.util.List;

import com.revature.pokepipeline.models.Item;

public interface ItemDAO {
	
	void insertItem(Item item); // untested
	void updateItem(Item item); // untested
	void deleteItem(Item item); // untested
	Item getItemById(int itemId); // untested
	List<Item> getAllItems(); // untested

}
