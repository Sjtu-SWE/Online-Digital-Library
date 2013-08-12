package com.sjtu.onlinelibrary.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 
 * @author Crystal
 *
 */
public class UserSecurityFilter extends AbstractSecurityInterceptor implements Filter {
	
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	 
     public FilterInvocationSecurityMetadataSource getSecurityMetadataSource()   {
         return securityMetadataSource;
     }
 
     public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource)  {
         this.securityMetadataSource = securityMetadataSource;
     }
 
     @Override
     public void destroy()  {
 
     }
 
     @Override
     public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException  {
         FilterInvocation fileInvocation = new FilterInvocation(arg0, arg1, arg2);
         InterceptorStatusToken interceptorStatusToken = this.beforeInvocation(fileInvocation);
         fileInvocation.getChain().doFilter(arg0, arg1);
         this.afterInvocation(interceptorStatusToken, null);
     }
 
     @Override
     public void init(FilterConfig arg0) throws ServletException    {
 
     }
 
     @Override
     public Class<? extends Object> getSecureObjectClass()  {
         return FilterInvocation.class;
     }
 
     @Override
     public SecurityMetadataSource obtainSecurityMetadataSource()  {
         return this.securityMetadataSource;
     }

}
