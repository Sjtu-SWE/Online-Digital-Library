package com.sjtu.onlinelibrary.web.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.service.IUserService;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.UserEditModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	 @RequestMapping("/admin/user/create.do")
	    public ModelAndView create() {
	        final Map<String, Object> map = new HashMap<String, Object>();
	        map.put("user", new UserEditModel("创建用户", new User()));
	        return new ModelAndView(ADMIN_USER_MGR_EDIT, map);
	    }
	 
	 @RequestMapping("/admin/user/{id}/edit.do")
	    public ModelAndView edit(@PathVariable("id") final String id) {
	        try {
	            final UserEditModel user = this.userService.findById(id);
	            final Map<String, Object> map = new HashMap<String, Object>();
	            map.put("user", user);
	            return new ModelAndView(ADMIN_USER_MGR_EDIT, map);

	        } catch (DataAccessException e) {
	            return new ModelAndView("error");
	        }
	    }
	 
	 @RequestMapping(value = "/admin/user/save.do", method = RequestMethod.POST)
	    public ModelAndView save(@Valid @ModelAttribute("user") final UserEditModel userEditModel, final BindingResult bindingResult) throws DataAccessException {
	        if (bindingResult.hasErrors()) {
	            final Map<String, Object> map = new HashMap<String, Object>();
	            userEditModel.setEditType("编辑用户");
	            map.put("user", userEditModel);
	            return new ModelAndView(ADMIN_USER_MGR_EDIT, map);
	        }
	        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        if( userEditModel.innerUserEntity().getId() == null ||  "".equals(userEditModel.innerUserEntity().getId()) ){
	        	userEditModel.setCreateDate(dateformat.format(new Date()));
	        }
	        userService.save(userEditModel.innerUserEntity());
	        
	        ModelMap mm = new ModelMap();
	        mm.put("message", "保存用户成功！");
	        mm.put("url", "/admin/user/list.do");
	        return new ModelAndView( "forward:/success.jsp", mm);
	    }
	 
	 @RequestMapping("/admin/user/{id}/delete.do")
	    public ModelAndView delete(@PathVariable("id") final String id) {
	        String result = "删除用户失败！";
	        if (userService.delete(id)) {
	            result = "删除用户成功！";
	        }
	        ModelMap mm = new ModelMap();
	        mm.put("message", result);
	        mm.put("url", "/admin/user/list.do");
	        return new ModelAndView("forward:/success.jsp", mm);
	    }
	 
	@RequestMapping("/j_spring_security_check.do")
    public ModelAndView login(HttpServletResponse response,  
    		@RequestParam(value = "j_username", required = false) String username,  
    		@RequestParam(value = "j_password", required = false) String password) throws Exception{
        //在请求/login.do时，执行该方法验证登录
		if (userService.checkLogin(username, password) != null ) {
        	return new ModelAndView("forward:/index.jsp","user", userService.checkLogin(username, password));
        }
        return new ModelAndView("forward:/loginError.jsp");
    }
	
}