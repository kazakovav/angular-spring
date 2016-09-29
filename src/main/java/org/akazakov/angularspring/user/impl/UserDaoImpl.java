package org.akazakov.angularspring.user.impl;

import org.akazakov.angularspring.user.api.UserDao;
import org.akazakov.angularspring.user.api.dto.User;
import org.akazakov.common.hibernate.HidernateDao;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl extends HidernateDao implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUserName(String userName) {
		List<User> userList;
		Query query = openSession()
				.createQuery("from org.akazakov.angularspring.user.api.dto.User u where u.userName = :userName");
		query.setParameter("userName", userName);
		userList = query.list();
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public void addUser(User user) {
		if (user == null) {
			throw new RuntimeException("User is empty");
		}
		Session session = openSession();
		session.save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList() {
		List<User> userList = (List<User>) openSession().createCriteria(User.class).list();
		return userList;
	}

}
