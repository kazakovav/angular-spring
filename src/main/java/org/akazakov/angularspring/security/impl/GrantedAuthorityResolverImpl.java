package org.akazakov.angularspring.security.impl;

import org.akazakov.angularspring.user.api.dto.UserRoles;
import org.akazakov.common.web.security.api.GrantedAuthorityResolver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GrantedAuthorityResolverImpl implements GrantedAuthorityResolver<UserRoles> {

	@Override
	public Collection<? extends GrantedAuthority> resolveAuthorities(Collection<UserRoles> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (UserRoles role : userRoles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}
}
