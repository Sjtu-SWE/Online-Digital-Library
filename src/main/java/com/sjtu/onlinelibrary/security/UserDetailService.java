package com.sjtu.onlinelibrary.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {
	/**
      * arg0 --->登录的用户名
      */
     @Override
     public UserDetails loadUserByUsername(String arg0)    throws UsernameNotFoundException, DataAccessException   {
    	 System.out.println("-----------------------UserDetailService");
    	 System.out.println("================登录的用户名"+arg0);
         List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
         GrantedAuthority grantedAuthority = null;
 
         if ("admin".equals(arg0))  {
             grantedAuthority = new GrantedAuthorityImpl("Role_ADMIN");
         }  else if ("manager".equals(arg0))  {
             grantedAuthority = new GrantedAuthorityImpl("Role_MANAGER");
         }  else    {
             grantedAuthority = new GrantedAuthorityImpl("Role_USER");
         }
         grantedAuthorities.add(grantedAuthority);
 
         User user = new User(arg0, "admin", true, true, true, true, grantedAuthorities);
         return user;
     }

}
