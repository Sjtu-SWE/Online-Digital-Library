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
    	 try {
				if(userService.findByName(arg0) != null){
					name = userService.findByName(arg0).getUserName();//从数据库中来取  
			    	 password = userService.findByName(arg0).getPassword();//数据库中保存的用户密码
			    	System.out.println("=========username:"+name);
			    	System.out.println("=========password:"+password);
				}else {
					throw new UsernameNotFoundException("User: " + arg0 + ", has no GrantedAuthority"); 
				}
    	 } catch (Exception e) {
 			e.printStackTrace();
 		}
    	 //ToDo:modify following
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
 
         User user = new User(arg0, password, true, true, true, true, grantedAuthorities);
         return user;
     }

}
