package org.akazakov.common.web.security.api;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface for load userDetails by user name
 */
public interface UserAuthenticationService {

	UserDetails loadUserByUserName(String username);

}
