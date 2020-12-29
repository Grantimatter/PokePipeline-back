package com.revature.pokepipeline.daos.impl;

import java.util.List;

import org.hibernate.Session;

import com.revature.pokepipeline.daos.ItemDAO;
import com.revature.pokepipeline.models.Item;
import com.revature.pokepipeline.utility.HibernateUtility;

public class ItemDAOImpl implements ItemDAO {

	@Override
	public void insertItem(Item item) {
		Session session = HibernateUtility.getSession();
		session.save(item);
	}

	@Override
	public void updateItem(Item item) {
		Session session = HibernateUtility.getSession();
		session.merge(item);
	}

	@Override
	public Item getItemById(int itemId) {
		Session session = HibernateUtility.getSession();	
		return session.get(Item.class, itemId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAllItems() {
		Session session = HibernateUtility.getSession();
		List<Item> itemsList = session.createQuery("from Item").list();
		return itemsList;
	}

}
