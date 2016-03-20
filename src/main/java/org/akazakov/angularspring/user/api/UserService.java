package org.akazakov.angularspring.user.api;

import org.akazakov.angularspring.user.api.dto.User;

import java.util.List;

public interface UserService {
	User getCurrentUser();

	List<User> getUsers();
}
