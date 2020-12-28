package com.revature.pokepipeline.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;
	
	private int itemAPI;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Users.class)
	@JoinColumn(name="userId")
	private Users user;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(int itemId, int itemAPI, Users user) {
		super();
		this.itemId = itemId;
		this.itemAPI = itemAPI;
		this.user = user;
	}

	public Item(int itemAPI, Users user) {
		super();
		this.itemAPI = itemAPI;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemAPI=" + itemAPI + ", user=" + user.getUsername() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemAPI;
		result = prime * result + itemId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	
}
