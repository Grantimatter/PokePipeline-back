package com.revature.pokepipeline.daos.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.pokepipeline.daos.UserDAO;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.utility.HibernateUtility;

public class UserDAOImpl implements UserDAO {

	@Override
	public void insertUser(Users user) {
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean updateUser(Users user) {
		boolean isUpdated = false;
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			session.close();
		}
		return isUpdated;
	}

	@Override
	public Users getUserById(int userId) {
		Session session = HibernateUtility.getSession();	
		return session.get(Users.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getAllUsers() {
		Session session = HibernateUtility.getSession();
		List<Users> userList = session.createQuery("from Users").list();
		return userList;
	}

}
