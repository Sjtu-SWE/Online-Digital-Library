package com.sjtu.onlinelibrary.web.user;

import javax.servlet.http.HttpServletResponse;
import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.service.IUserService;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.UserEditModel;
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

	public static final String ADMIN_USER_MGR_LIST = "admin/userMgr/list";
    public static final String ADMIN_USER_MGR_EDIT = "admin/userMgr/edit";
    public static final String PAGE_DATE = "pageData";
    
    private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/admin/user/list.do")
    public ModelAndView list(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            final Pager<UserEditModel> users = this.userService.findAll(index);
            return new ModelAndView(ADMIN_USER_MGR_LIST, PAGE_DATE, users);

        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }
	
	@RequestMapping("/login.do")
    public ModelAndView login(HttpServletResponse response,  
    		@RequestParam(value = "username", required = false) String username,  
    		@RequestParam(value = "password", required = false) String password) throws Exception{
        //在请求/login.do时，执行该方法验证登录
		if (userService.checkLogin(username, password) != null ) {
        	return new ModelAndView("forward:/index.jsp","user", userService.checkLogin(username, password));
        }
        return new ModelAndView("forward:/loginError.jsp");
    }
	
}