package org.akazakov.common.web.security.api;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface GrantedAuthorityResolver<RoleType> {

	Collection<? extends GrantedAuthority> resolveAuthorities(Collection<RoleType> userRoles);
}
