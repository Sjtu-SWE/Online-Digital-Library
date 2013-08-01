package com.sjtu.onlinelibrary.web.user;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *  @author Crystal
 *
 */

@Controller

public class UserController {
	private static final Log log = LogFactory.getLog(UserController.class);

	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login.do")
    public ModelAndView login(HttpServletResponse response,  
    		@RequestParam(value = "username", required = false) String username,  
    		@RequestParam(value = "password", required = false) String password) throws Exception{
        //在请求/login.do时，执行该方法验证登录
		if (userService.checkLogin(username, password) ) {
        	return new ModelAndView("forward:/index.jsp");
        }
        return new ModelAndView("forward:/loginError.jsp");
    }

	
}