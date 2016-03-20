package org.akazakov.common.web.security.impl;

import org.akazakov.common.web.security.api.UserAuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

	private UserAuthenticationService userAuthenticationService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userAuthenticationService.loadUserByUserName(username);
	}

	public UserAuthenticationService getUserAuthenticationService() {
		return userAuthenticationService;
	}

	public void setUserAuthenticationService(UserAuthenticationService userAuthenticationService) {
		this.userAuthenticationService = userAuthenticationService;
	}

}
