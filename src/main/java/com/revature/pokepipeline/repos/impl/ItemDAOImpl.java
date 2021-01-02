package com.revature.pokepipeline.repos.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

import com.revature.pokepipeline.repos.ItemDAO;
import com.revature.pokepipeline.models.Item;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public class ItemDAOImpl implements ItemDAO {

	public SessionFactory sessionFactory;

	@Autowired
	public ItemDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertItem(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(item);
	}

	@Override
	public Item getItemById(int itemId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Item.class, itemId);
	}

	@Override
	public List<Item> getAllItems() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaQuery<Item> criteriaQuery = session.getCriteriaBuilder().createQuery(Item.class);
		criteriaQuery.from(Item.class);
		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void updateItem(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.update(item);
	}

	@Override
	public void deleteItem(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(item);
	}
}
