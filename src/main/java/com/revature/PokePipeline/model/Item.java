package com.revature.PokePipeline.model;

public class Item {
	// constructors: with id, without id
	private int itemId; // primary key
	private int itemAPI;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(int itemId, int itemAPI) {
		super();
		this.itemId = itemId;
		this.itemAPI = itemAPI;
	}
	public Item(int itemAPI) {
		super();
		this.itemAPI = itemAPI;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemAPI=" + itemAPI + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemAPI;
		result = prime * result + itemId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemAPI != other.itemAPI)
			return false;
		if (itemId != other.itemId)
			return false;
		return true;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemAPI() {
		return itemAPI;
	}
	public void setItemAPI(int itemAPI) {
		this.itemAPI = itemAPI;
	}
	
	
}
