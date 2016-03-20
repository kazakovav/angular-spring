package org.akazakov.springsecurity.security.api;

import org.akazakov.angularspring.user.api.UserDao;
import org.akazakov.angularspring.user.api.dto.User;
import org.akazakov.angularspring.user.api.dto.UserRoles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-config.xml")
@Transactional
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void testGetUserByUserName() throws Exception {
		User admin = userDao.getUserByUserName("admin");
		Assert.assertNotNull(admin);
		Assert.assertNotNull(admin.getUserRoles());
		Assert.assertFalse(CollectionUtils.isEmpty(admin.getUserRoles()));
		Assert.assertTrue(admin.getUserRoles().size() == 2);
		Assert.assertNotNull(admin.getUserRoles().toArray()[0]);
		Assert.assertNotNull(admin.getUserRoles().toArray()[1]);
		User user1 = userDao.getUserByUserName("user1");
		Assert.assertNotNull(user1);
		Assert.assertNotNull(user1.getUserRoles());
		Assert.assertFalse(CollectionUtils.isEmpty(user1.getUserRoles()));
		Assert.assertTrue(user1.getUserRoles().size() == 1);
		Assert.assertNotNull(user1.getUserRoles().toArray()[0]);
	}

	@Test
	public void testAddUser() throws Exception {
		User user = new User();
		user.setUserName("user123");
		user.setPassword("user123");
		UserRoles role = new UserRoles();
		role.setRole("ROLE_USER");
		user.setUserRoles(new HashSet<UserRoles>());
		user.getUserRoles().add(role);
		userDao.addUser(user);

		User user1 = userDao.getUserByUserName("user1");
		Assert.assertNotNull(user1);
		Assert.assertNotNull(user1.getUserRoles());
		Assert.assertFalse(CollectionUtils.isEmpty(user1.getUserRoles()));
		Assert.assertTrue(user1.getUserRoles().size() == 1);
		Assert.assertNotNull(user1.getUserRoles().toArray()[0]);
	}

	@Test
	public void testGetUserList() {
		List<User> userList = userDao.getUserList();
		Assert.assertNotNull(userList);
		Assert.assertTrue(userList.size() > 0);
	}
}