package com.sjtu.onlinelibrary.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

/**
 * 
 * @author Crystal
 *
 */
public class UserFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private Map<String, List<ConfigAttribute>> mp;
	private UrlMatcher urlMatcher;

	/**
      * 构造每一种资源所需要的角色权限
      */
     public UserFilterInvocationSecurityMetadataSource()  {
         super();
         this.mp = new HashMap<String, List<ConfigAttribute>>();
         this.urlMatcher = new AntUrlPathMatcher();
         List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
         ConfigAttribute cb = new SecurityConfig("Role_ADMIN"); // 构造一个权限(角色)
         ConfigAttribute cbUser = new SecurityConfig("Role_USER"); // 构造一个权限(角色)
         ConfigAttribute cbManager = new SecurityConfig("Role_MANAGER"); // 构造一个权限(角色)
         list.add(cb);
         list.add(cbUser);
         list.add(cbManager);
         
         mp.put("/success.jsp", list);
         list.remove(2);
         mp.put("/error.jsp", list);
     }

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
      * 获取访问某一个url所需的角色
      */
     @Override
     public Collection<ConfigAttribute> getAttributes(Object arg0)   throws IllegalArgumentException  {
         String requestUrl = ((FilterInvocation) arg0).getRequestUrl();
         Iterator<String> iter = this.mp.keySet().iterator();
         while (iter.hasNext())  {
             String temp = iter.next();
             if (this.urlMatcher.pathMatchesUrl(requestUrl, temp))  {
                 return mp.get(temp);
             }
         } 
         return null;
     }
     
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
