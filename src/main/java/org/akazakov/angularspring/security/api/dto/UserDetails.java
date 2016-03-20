package org.akazakov.angularspring.security.api.dto;

import org.akazakov.angularspring.user.api.dto.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetails extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 4262146889093106022L;
	private User user;

	public UserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUserName(), user.getPassword(), authorities);
		this.user = user;
	}

	public UserDetails(User user, boolean enabled,
					   boolean accountNonExpired, boolean credentialsNonExpired,
					   boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUserName(), user.getPassword(),
				enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
