package org.akazakov.angularspring.security.rest;

import org.akazakov.angularspring.security.api.UserRolesConst;
import org.akazakov.angularspring.security.api.dto.UserDetails;
import org.akazakov.angularspring.user.api.dto.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(UserAuthenticationController.BASE_PATH)
@Secured({UserRolesConst.ROLE_USER})
public class UserAuthenticationController {
	public static final String BASE_PATH = "authenticate";

	public static final String AUTH_SUCCESS_PARAM = "success";

	public static final String AUTH_FAILURE_PARAM = "failure";

	@RequestMapping(method = RequestMethod.GET)
	public Map<String, String> getUserDetails(Principal user) {
		Map<String, String> result = new HashMap<>();
		User authUser = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
		if (authUser != null) {
			result.put(AUTH_SUCCESS_PARAM, authUser.getUserName());
		} else {
			result.put(AUTH_FAILURE_PARAM, "invalid user name or password");
		}
		return result;
	}
}
