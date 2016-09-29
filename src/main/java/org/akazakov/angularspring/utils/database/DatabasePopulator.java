package org.akazakov.angularspring.utils.database;

import java.util.HashSet;

import org.akazakov.angularspring.security.api.UserRolesConst;
import org.akazakov.angularspring.user.api.UserDao;
import org.akazakov.angularspring.user.api.dto.User;
import org.akazakov.angularspring.user.api.dto.UserRoles;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DatabasePopulator implements ApplicationListener<ContextRefreshedEvent> {

	private UserDao userDao;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		populateDatabase();
	}

	private void populateDatabase() {
		if (userDao.getUserByUserName("admin") == null) {
			User user = new User();
			user.setUserName("admin");
			user.setPassword("admin");
			user.setUserRoles(new HashSet<UserRoles>());
			UserRoles role = new UserRoles();
			role.setRole(UserRolesConst.ROLE_ADMIN);
			user.getUserRoles().add(role);
			role = new UserRoles();
			role.setRole(UserRolesConst.ROLE_USER);
			user.getUserRoles().add(role);
			userDao.addUser(user);
		}
		if (userDao.getUserByUserName("user1") == null) {
			User user = new User();
			user.setUserName("user1");
			user.setPassword("user1");
			user.setUserRoles(new HashSet<UserRoles>());
			UserRoles role = new UserRoles();
			role.setRole(UserRolesConst.ROLE_USER);
			user.getUserRoles().add(role);
			userDao.addUser(user);
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
