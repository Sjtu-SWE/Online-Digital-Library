package com.sjtu.onlinelibrary.web.user;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.common.Constants;
import com.sjtu.onlinelibrary.entity.Classification;
import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.service.IClassificationService;
import com.sjtu.onlinelibrary.service.IUserService;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.util.SpringSecurityUtils;
import com.sjtu.onlinelibrary.web.viewmodel.Category;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.PersonalModel;
import com.sjtu.onlinelibrary.web.viewmodel.RegisterModel;
import com.sjtu.onlinelibrary.web.viewmodel.UserEditModel;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.regex.Pattern;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  @author Crystal
 *
 */

@Controller
public class UserController {

	public static final String ADMIN_USER_MGR_LIST = "admin/userMgr/list";
    public static final String ADMIN_USER_MGR_EDIT = "admin/userMgr/edit";
    public static final String BOOK_SEARCH_BOOK = "book/searchBook";
    public static final String USER_PERSONAL = "user/personal";
    public static final String PAGE_DATE = "pageData";
    public static final String USER_BOOK_SHELF = "user/bookShelf";

    private IUserService userService;
    private IClassificationService classificationService;
    
	public IUserService getUserService() {
		return userService;
	}

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

	public void setClassificationService(IClassificationService classificationService) {
		this.classificationService = classificationService;
	}

	@RequestMapping("/admin/user/list.do")
    public ModelAndView list(@RequestParam(value = "pageIndex", required = false) final String pageIndex
    		,@RequestParam(value="username" ,required = false) final String username) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            Map<String, Object> condition = new HashMap<String, Object>();
            if( username!=null && !"".equals(username.trim()) ){
            	Pattern pattern = Pattern.compile(".*" + username.trim()+ ".*", Pattern.CASE_INSENSITIVE);
            	condition.put("username", pattern);
            }
            final Pager<UserEditModel> users = this.userService.findAll(index,condition);
            
            return new ModelAndView(ADMIN_USER_MGR_LIST, PAGE_DATE, users);

        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping("/admin/user/create.do")
    public ModelAndView create() {
        final Map<String, Object> map = getMapForEdit();
        map.put("user", new UserEditModel("创建用户", new User()));
        return new ModelAndView(ADMIN_USER_MGR_EDIT, map);
    }

    @RequestMapping("/admin/user/{id}/edit.do")
    public ModelAndView edit(@PathVariable("id") final String id) {
        try {
            final UserEditModel user = this.userService.findById(id);
            final Map<String, Object> map = getMapForEdit();
            map.put("user", user);
            return new ModelAndView(ADMIN_USER_MGR_EDIT, map);
        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/admin/user/save.do", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("user") final UserEditModel userEditModel, final BindingResult bindingResult) throws DataAccessException {
        if (bindingResult.hasErrors()) {
            final Map<String, Object> map = getMapForEdit();
            userEditModel.setEditType("编辑用户");
            map.put("user", userEditModel);
            return new ModelAndView(ADMIN_USER_MGR_EDIT, map);
        }
        ModelMap mm = new ModelMap();
        String username  = userEditModel.getUsername();
        User temp = userService.findByName(username);
        if(userEditModel.getEditType().equals("创建用户")){
        	 //判断用户名是否已存在
            if( temp != null ){
            	mm.put("message", "保存用户失败,此用户名已存�?");
            	mm.put("url", "/admin/user/create.do");
            	return new ModelAndView("forward:/success.jsp", mm);
            }
        }
        temp.setUsername(userEditModel.getUsername());
        temp.setRealName(userEditModel.getRealName());
        temp.setPassword(userEditModel.getPassword());
        temp.setEmail(userEditModel.getEmail());
        temp.setPhone(userEditModel.getPhone());
        temp.setRoleName(userEditModel.getRoleName());
        temp.setCredits(userEditModel.getCredits());
        temp.setNote(userEditModel.getNote());
        userService.save(temp);

        mm.put("message", "保存用户成功");
        mm.put("url", "/admin/user/list.do");
        return new ModelAndView("forward:/success.jsp", mm);
    }

    @RequestMapping("/admin/user/{id}/delete.do")
    public ModelAndView delete(@PathVariable("id") final String id) {
        String result = "删除用户失败";
        if (userService.delete(id)) {
            result = "删除用户成功";
        }
        ModelMap mm = new ModelMap();
        mm.put("message", result);
        mm.put("url", "/admin/user/list.do");
        return new ModelAndView("forward:/success.jsp", mm);
    }

    @RequestMapping("/login.do")
    public ModelAndView login(HttpServletResponse response,
                              @RequestParam(value = "j_username", required = false) String username,
                              @RequestParam(value = "j_password", required = false) String password) throws Exception {
        //在请求ogin.do时，执行该方法验证登�?
        if (userService.checkLogin(username, password) != null) {
            return new ModelAndView("forward:/index.jsp", "user", userService.checkLogin(username, password));
        }
        return new ModelAndView("forward:/loginError.jsp");
    }

    private Map<String, Object> getMapForEdit() {
        final Map<String, Object> map = new HashMap<String, Object>();
        final List<Category> types = new ArrayList<Category>();
        types.add(new Category("系统管理", Constants.ROLE_NAME_ADMIN));
        types.add(new Category("普�?用户", Constants.ROLE_NAME_USER));
        map.put("types", types);
        return map;
    }
	
	@RequestMapping("/index.do")
    public ModelAndView index() throws Exception{
        //在访问首页面时，生成动�?菜单sidebar
		List<Classification> classifications = classificationService.findAll();
		ModelMap mm = new ModelMap();
        mm.put("classifications", classifications);
        return new ModelAndView("forward:/index.jsp", mm);
    }
	
	@RequestMapping("/toSearch.do")
	public ModelAndView toSearchBook() throws Exception{
		return new ModelAndView(BOOK_SEARCH_BOOK);
	}
	
	/**
	 * 跳转到注册页�?
	 */
	
	@RequestMapping("/register.do")
    public ModelAndView register() {
        final Map<String, Object> map = getMapForEdit();
        map.put("user", new RegisterModel( new User() ));
        return new ModelAndView("forward:/register.jsp", map);
    }
	
	/**
	 * 提交注册请求
	 * @return
	 */
	 @RequestMapping(value = "/saveUser.do", method = RequestMethod.POST)
	    public ModelAndView saveUser(@Valid @ModelAttribute("user") final RegisterModel registerModel, final BindingResult bindingResult) 
	    		throws DataAccessException {
	        ModelMap mm = new ModelMap();
	        if (bindingResult.hasErrors()) {
	        	mm.put("message", "用户注册失败,请输入正确的格式!");
	        	return new ModelAndView("forward:/register.jsp", mm);
	        }
	        //判断用户名是否已存在
	        String nameTemp  = registerModel.getUsername();
	        User temp = userService.findByName(nameTemp);
	        if( temp != null ){
	        	mm.put("message", "用户注册失败,此用户名已存�?");
	        	return new ModelAndView("redirect:/register.do", mm);
	        }
	        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        if (registerModel.innerUserEntity().getId() == null || "".equals(registerModel.innerUserEntity().getId())) {
	        	registerModel.setCreateDate(dateformat.format(new Date()));
	        }
	        registerModel.innerUserEntity().setRoleName("ROLE_USER");
	        userService.save(registerModel.innerUserEntity());

	        mm.put("message", "用户注册成功�?);
	        return new ModelAndView("redirect:/register.do", mm);
	    }
	 
	    /**
		 * 保存修改的新密码
		 * @return
		 */
		 @RequestMapping(value = "/savePassword.do", method = RequestMethod.POST)
		    public ModelAndView savePassword(@RequestParam(value = "password", required = false) String password, @RequestParam(value = "newPassword", required = false) String newPassword
		    		,@RequestParam(value = "renewPassword", required = false) String renewPassword)  throws DataAccessException {
		        ModelMap mm = new ModelMap();
		        if (!newPassword.equals(renewPassword)) {
		        	mm.put("message", "两次输入的新密码不一致，请重新输�?");
		        	return new ModelAndView("redirect:/modifyPassword.jsp", mm);
		        }
		        String username = SecurityContextHolder.getContext().getAuthentication().getName();
		        User temp = userService.findByName(username);//当前登录用户
		        
		        //�?��原密码是否正�?
		        if( !password.equals(temp.getPassword())){
		        	mm.put("message", "原密码不正确，请重新输入!");
		        	return new ModelAndView("redirect:/modifyPassword.jsp", mm);
		        }
		        temp.setPassword(newPassword);
		        
		        userService.save(temp);
		        mm.put("message", "修改用户密码成功�?);
		        return new ModelAndView("redirect:/modifyPassword.jsp", mm);
		    }
	
		 @RequestMapping("/personal.do")
		 public ModelAndView personal() throws DataAccessException{
			 String username = SpringSecurityUtils.getCurrentUserName();
			 try{
				    final User user = userService.findByName(username);
		            final Map<String, Object> map = getMapForEdit();
		            map.put("user", new UserEditModel("编辑用户",user));
				 return new ModelAndView(USER_PERSONAL, map);
			 }catch(DataAccessException ex){
				 return new ModelAndView("error");
			 }
		 }
		 
    @RequestMapping("/user/myBookShelf")
    public ModelAndView myBookShelf(){
        return new ModelAndView(USER_BOOK_SHELF);
    }

		 
		 /**
		  * 更新个人主页信息
		  * @throws DataAccessException
		  */
		 @RequestMapping(value = "/savePersonal.do", method = RequestMethod.POST)
		    public ModelAndView savePersonal(@Valid @ModelAttribute("user") final PersonalModel personalEditModel, final BindingResult bindingResult) throws DataAccessException {
		        if (bindingResult.hasErrors()) {
		            final Map<String, Object> map = getMapForEdit();
		            map.put("message", "用户注册失败,请输入正确的格式!");
		        	return new ModelAndView("forward:/personal.do", map);
		        }
		        ModelMap mm = new ModelMap();
		        String username  = personalEditModel.getUsername();
		        User temp = userService.findByName(username);
		        temp.setRealName(personalEditModel.getRealName());
		        temp.setEmail(personalEditModel.getEmail());
		        temp.setPhone(personalEditModel.getPhone());
		        temp.setNote(personalEditModel.getNote());
		        userService.save(temp);

		        mm.put("message", "保存个人信息成功");
		        mm.put("url", "/personal.do");
		        return new ModelAndView("forward:/success.jsp", mm);
		    }
}