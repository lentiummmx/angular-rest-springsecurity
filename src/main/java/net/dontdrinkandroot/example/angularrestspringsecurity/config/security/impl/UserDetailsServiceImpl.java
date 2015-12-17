/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.config.security.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.Role;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import net.dontdrinkandroot.example.angularrestspringsecurity.repository.UserRepository;

/**
 * @author lentiummmx
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(Role.ROLE_PREFIX + role.getName()));
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
	
	private SecurityUser createSecurityUser(User user) {
		return new SecurityUser(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			String message = "Username not found " + username;
			LOGGER.info(message);
			throw new UsernameNotFoundException(message);
		}
		LOGGER.info("Found user in database: " + user);
		return createSecurityUser(user);
	}
	
	private Authentication authenticate(User user) {
		return new UsernamePasswordAuthenticationToken(createSecurityUser(user), null, getGrantedAuthorities(user));
	}

	public void signin(User user) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(user));
	}

}
