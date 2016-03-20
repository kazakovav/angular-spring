package org.akazakov.angularspring.user.impl;

import org.akazakov.angularspring.security.api.dto.UserDetails;
import org.akazakov.angularspring.user.api.UserDao;
import org.akazakov.angularspring.user.api.UserService;
import org.akazakov.angularspring.user.api.dto.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Override
	public User getCurrentUser() {
		return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
	}

	@Override
	public List<User> getUsers() {
		return userDao.getUserList();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
