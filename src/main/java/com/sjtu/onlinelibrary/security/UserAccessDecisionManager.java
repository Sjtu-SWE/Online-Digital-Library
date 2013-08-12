package com.sjtu.onlinelibrary.security;

import java.util.Collection;
import java.util.Iterator;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author Crystal
 *
 */
public class UserAccessDecisionManager implements AccessDecisionManager {
	/**
      * Authentication arg0 --->用户具有的角色权限 
      * Collection<ConfigAttribute> arg2 --->访问该资源所需的角色权限
      */
     @Override
     public void decide(Authentication arg0, Object arg1, Collection<ConfigAttribute> arg2) throws AccessDeniedException, InsufficientAuthenticationException  {
    	 Iterator<ConfigAttribute> iter = arg2.iterator();
         while (iter.hasNext())   {
             String accessResourceNeedRole = ((SecurityConfig) iter.next()).getAttribute();
             for (GrantedAuthority grantedAuthority : arg0.getAuthorities())  {
                 String userOwnRole = grantedAuthority.getAuthority();
                 if (accessResourceNeedRole.equals(userOwnRole))  {
                     return;
                 }
             }
         }
         throw new AccessDeniedException("访问被拒绝!");
     }

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
