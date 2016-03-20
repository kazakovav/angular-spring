package org.akazakov.angularspring.user.api;


import org.akazakov.angularspring.user.api.dto.User;

import java.util.List;

public interface UserDao {
	User getUserByUserName(String userName);

	void addUser(User user);

	List<User> getUserList();
}
