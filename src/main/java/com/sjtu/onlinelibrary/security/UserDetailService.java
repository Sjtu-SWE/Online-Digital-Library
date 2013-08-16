package com.sjtu.onlinelibrary.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.sjtu.onlinelibrary.service.IUserService;
import org.springframework.security.core.userdetails.User;

public class UserDetailService  implements UserDetailsService {

	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
      * arg0 --->登录的用户名
      */
     @Override
     public UserDetails loadUserByUsername(String arg0)    throws UsernameNotFoundException, DataAccessException   {
    	 String name= "";
    	 String password = "";
    	 String roleName = "";
    	 try {
				if(userService.findByName(arg0) != null){
					name = userService.findByName(arg0).getUserName();//从数据库中来取
			    	password = userService.findByName(arg0).getPassword();//数据库中保存的用户密码
			    	roleName = userService.findByName(arg0).getRoleName();
				}else {
					throw new UsernameNotFoundException("User: " + arg0 + ", has no GrantedAuthority"); 
				}
    	 } catch (Exception e) {
 			e.printStackTrace();
 		}
    	 //系统管理员为ROLE_ADMIN，普通用户为ROLE_USER
         List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
         GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(roleName);
         grantedAuthorities.add(grantedAuthority);
 
         User user = new User(arg0, password, true, true, true, true, grantedAuthorities);
         return user;
     }

}
