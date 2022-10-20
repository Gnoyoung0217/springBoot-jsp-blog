package com.jerryLog.www.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jerryLog.www.Model.UserBean;
import com.jerryLog.www.service.LoginService;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private LoginService loginSvc;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		UserBean userBean = new UserBean();
		userBean.setEmail(email);
		
		userBean = loginSvc.loginAuth(email);
		
		if(userBean != null) {
			grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
			return new User(userBean.getEmail(), userBean.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("Cannot find User" + email);
		}
		
	}

}
