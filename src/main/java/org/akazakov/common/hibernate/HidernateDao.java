package org.akazakov.common.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class HidernateDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session openSession() {
		return sessionFactory.getCurrentSession();
	}
}
