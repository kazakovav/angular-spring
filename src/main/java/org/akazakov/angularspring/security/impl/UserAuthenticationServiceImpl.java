package org.akazakov.angularspring.security.impl;

import org.akazakov.angularspring.user.api.UserDao;
import org.akazakov.angularspring.user.api.dto.User;
import org.akazakov.angularspring.user.api.dto.UserRoles;
import org.akazakov.common.web.security.api.GrantedAuthorityResolver;
import org.akazakov.common.web.security.api.UserAuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private GrantedAuthorityResolver<UserRoles> grantedAuthorityResolver;

	private UserDao userDao;

	@Override
	public UserDetails loadUserByUserName(String username) {
		User user = userDao.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with name not found");
		}
		return new org.akazakov.angularspring.security.api.dto.UserDetails(user, grantedAuthorityResolver.resolveAuthorities(user.getUserRoles()));
	}

	public GrantedAuthorityResolver<UserRoles> getGrantedAuthorityResolver() {
		return grantedAuthorityResolver;
	}

	public void setGrantedAuthorityResolver(GrantedAuthorityResolver<UserRoles> grantedAuthorityResolver) {
		this.grantedAuthorityResolver = grantedAuthorityResolver;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
