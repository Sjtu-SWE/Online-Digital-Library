package com.sjtu.onlinelibrary.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.service.IUserService;

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
					User temp = userService.findByName(arg0);
					name = temp.getUsername();//从数据库中取出
			    	password = temp.getPassword();//数据库中保存的用户密码
			    	roleName = temp.getRoleName();
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

         User user = new User(name, password, true, true, true, true, grantedAuthorities);
         return user;
     }

}
