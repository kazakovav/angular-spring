package org.akazakov.angularspring.user.rest;

import org.akazakov.angularspring.security.api.UserRolesConst;
import org.akazakov.angularspring.user.api.UserService;
import org.akazakov.angularspring.user.api.dto.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserController.BASE_PATH)
@Secured({UserRolesConst.ROLE_USER})
public class UserController {
	public static final String BASE_PATH = "users";

	private UserService userService;

	@RequestMapping(value = "current", method = RequestMethod.GET)
	public User getCurrentUser() {
		return userService.getCurrentUser();
	}

	@Secured(UserRolesConst.ROLE_ADMIN)
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return getUserService().getUsers();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
