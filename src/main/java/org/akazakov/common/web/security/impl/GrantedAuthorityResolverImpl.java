package org.akazakov.common.web.security.impl;

import org.akazakov.common.web.security.api.GrantedAuthorityResolver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GrantedAuthorityResolverImpl implements GrantedAuthorityResolver<String> {

	@Override
	public Collection<? extends GrantedAuthority> resolveAuthorities(Collection<String> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : userRoles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
