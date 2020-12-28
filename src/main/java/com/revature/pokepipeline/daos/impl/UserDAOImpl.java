package com.revature.pokepipeline.daos.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.pokepipeline.daos.UserDAO;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.utility.HibernateUtility;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean insertUser(Users user) {
		boolean isInserted = false;
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			isInserted = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return isInserted;
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

	@Override
	public Users getUserByUsername(String username) {
		Users user = new Users();
		Session session = HibernateUtility.getSession();	
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			String HQL = "from Users where username=?1";
			Query<Users> query = session.createQuery(HQL, Users.class);
			query.setParameter(1, username);
			user = query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			session.close();
		}
		if (user.getUserId() == 0) return null;
		else return user;
	}

}
