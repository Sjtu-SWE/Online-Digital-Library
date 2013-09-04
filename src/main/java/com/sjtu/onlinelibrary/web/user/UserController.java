package com.sjtu.onlinelibrary.web.user;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.common.Constants;
import com.sjtu.onlinelibrary.entity.Classification;
import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.mail.MailSenderInfo;
import com.sjtu.onlinelibrary.mail.SimpleMailSender;
import com.sjtu.onlinelibrary.service.IClassificationService;
import com.sjtu.onlinelibrary.service.IUserService;
import com.sjtu.onlinelibrary.service.impl.ActivityStreamServiceImpl;
import com.sjtu.onlinelibrary.service.impl.BookServiceImpl;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.util.SpringSecurityUtils;
import com.sjtu.onlinelibrary.web.viewmodel.*;

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
 * @author Crystal
 */

@Controller
public class UserController {

    public static final String ADMIN_USER_MGR_LIST = "admin/userMgr/list";
    public static final String ADMIN_USER_MGR_EDIT = "admin/userMgr/edit";
    public static final String BOOK_SEARCH_BOOK = "book/searchBook";
    public static final String USER_PERSONAL = "user/personal";
    public static final String PAGE_DATE = "pageData";

    private IUserService userService;
    private BookServiceImpl bookService;
    private IClassificationService classificationService;
    private ActivityStreamServiceImpl activityStreamService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setClassificationService(IClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    public void setBookService(BookServiceImpl bookService) {
	    this.bookService = bookService;
	}
	
    @RequestMapping("/admin/user/list.do")
    public ModelAndView list(@RequestParam(value = "pageIndex", required = false) final String pageIndex
            , @RequestParam(value = "username", required = false) final String username) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            Map<String, Object> condition = new HashMap<String, Object>();
            if (username != null && !"".equals(username.trim())) {
                Pattern pattern = Pattern.compile(".*" + username.trim() + ".*", Pattern.CASE_INSENSITIVE);
                condition.put("username", pattern);
            }
            final Pager<UserEditModel> users = this.userService.findAll(index, condition);

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
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//        String username = userEditModel.getUsername();
//        User temp = userService.findByName(username);

        UserEditModel user = this.userService.findById(userEditModel.getId());
        if (user.innerUserEntity() == null) {//创建用户
            user = new UserEditModel("", new User());
            user.innerUserEntity().setCreateDate(new Date());//保存创建时间
        }
      //判断用户名是否已存在
        User temp = userService.findByName(userEditModel.getUsername());
        if (temp != null && !user.getUsername().equals(userEditModel.getUsername())) {
            mm.put("message", "保存用户失败,此用户名已存在");
            mm.put("url", "/admin/user/create.do");
            return new ModelAndView("forward:/success.jsp", mm);
        }
        user.setUsername(userEditModel.getUsername());
        user.setRealName(userEditModel.getRealName());
        user.setPassword(userEditModel.getPassword());
        user.setEmail(userEditModel.getEmail());
        user.setPhone(userEditModel.getPhone());
        user.setRoleName(userEditModel.getRoleName());
        user.setCredits(userEditModel.getCredits());
        user.setNote(userEditModel.getNote());
        userService.save(user.innerUserEntity());

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
        //在请求ogin.do时，执行该方法验证登录
        if (userService.checkLogin(username, password) != null) {
            return new ModelAndView("forward:/index.jsp", "user", userService.checkLogin(username, password));
        }
        return new ModelAndView("forward:/loginError.jsp");
    }

    private Map<String, Object> getMapForEdit() {
        final Map<String, Object> map = new HashMap<String, Object>();
        final List<Category> types = new ArrayList<Category>();
        types.add(new Category("系统管理", Constants.ROLE_NAME_ADMIN));
        types.add(new Category("普通用户", Constants.ROLE_NAME_USER));
        map.put("types", types);
        return map;
    }

    @RequestMapping("/index.do")
    public ModelAndView index() throws Exception {
        //在访问首页面时，生成动态菜单sidebar
        List<Classification> classifications = classificationService.findAll();
        ModelMap mm = new ModelMap();
        mm.put("classifications", classifications);
        final List<BookEditModel> books = this.bookService.findTop("-clickAmount");
        mm.put("books", books.subList(0, books.size()<10?books.size():10));//取点击量前五的图书
        final List<BookEditModel> books2 = this.bookService.findTop("-sellAmount");
        mm.put("books2", books2.subList(0, books2.size()<10?books2.size():10));//取购买量前五的图书
        final List<BookEditModel> books3 = this.bookService.findTop("-userLikeAmount");
        mm.put("books3", books3.subList(0, books3.size()<10?books3.size():10));//取鲜花前五的图书
        final List<BookEditModel> books4 = this.bookService.findTop("-userFavoriteAmount");
        mm.put("books4", books4.subList(0, books4.size()<10?books4.size():10));//取收藏前五的图书
        final List<BookEditModel> books5 = this.bookService.findTop("-sellAmount,-userFavoriteAmount,-clickAmount,-userLikeAmount");
        mm.put("books5", books5.subList(0, books5.size()<10?books5.size():10));//系统推荐的图书（购买量、收藏、点击、鲜花）
        return new ModelAndView("forward:/index.jsp", mm);
    }

    @RequestMapping("/toSearch.do")
    public ModelAndView toSearchBook() throws Exception {
        return new ModelAndView(BOOK_SEARCH_BOOK);
    }

    /**
     * 跳转到注册页面
     */

    @RequestMapping("/register.do")
    public ModelAndView register() {
        final Map<String, Object> map = getMapForEdit();
        map.put("user", new RegisterModel(new User()));
        return new ModelAndView("forward:/register.jsp", map);
    }

    /**
     * 提交注册请求
     *
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
        String nameTemp = registerModel.getUsername();
        User temp = userService.findByName(nameTemp);
        if (temp != null) {
            mm.put("message", "用户注册失败,此用户名已存在");
            return new ModelAndView("redirect:/register.do", mm);
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (registerModel.innerUserEntity().getId() == null || "".equals(registerModel.innerUserEntity().getId())) {
            registerModel.setCreateDate(dateformat.format(new Date()));
        }
        registerModel.innerUserEntity().setRoleName("ROLE_USER");
        userService.save(registerModel.innerUserEntity());

        mm.put("message", "用户注册成功");
        return new ModelAndView("redirect:/register.do", mm);
    }

    /**
     * 保存修改的新密码
     *
     * @return
     */
    @RequestMapping(value = "/savePassword.do", method = RequestMethod.POST)
    public ModelAndView savePassword(@RequestParam(value = "password", required = false) String password, @RequestParam(value = "newPassword", required = false) String newPassword
            , @RequestParam(value = "renewPassword", required = false) String renewPassword) throws DataAccessException {
        ModelMap mm = new ModelMap();
        if (!newPassword.equals(renewPassword)) {
            mm.put("message", "两次输入的新密码不一致，请重新输入");
            return new ModelAndView("redirect:/modifyPassword.jsp", mm);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User temp = userService.findByName(username);//当前登录用户

        //�?��原密码是否正�?
        if (!password.equals(temp.getPassword())) {
            mm.put("message", "原密码不正确，请重新输入!");
            return new ModelAndView("redirect:/modifyPassword.jsp", mm);
        }
        temp.setPassword(newPassword);

        userService.save(temp);
        mm.put("message", "修改用户密码成功");
        return new ModelAndView("redirect:/modifyPassword.jsp", mm);
    }

    @RequestMapping("/personal.do")
    public ModelAndView personal() throws DataAccessException {
        String username = SpringSecurityUtils.getCurrentUserName();
        try {
            final User user = userService.findByName(username);
            final Map<String, Object> map = getMapForEdit();
            map.put("user", new UserEditModel("编辑用户", user));
            map.put("rechargeRecodes",this.activityStreamService.listRechargeRecords(SpringSecurityUtils.getCurrentUser().getId(),1));
            map.put("purchaseRecodes",this.activityStreamService.listPurchaseRecords(SpringSecurityUtils.getCurrentUser().getId(),1));
            return new ModelAndView(USER_PERSONAL, map);
        } catch (DataAccessException ex) {
            return new ModelAndView("error");
        }
    }

    /**
     * 更新个人主页信息
     *
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
        String username = personalEditModel.getUsername();
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
    
    /**
     * 忘记密码
     */
    @RequestMapping(value = "/forgetPassword.do", method = RequestMethod.POST)
    public ModelAndView forgetPassword(@RequestParam(value = "email", required = false) String email) throws DataAccessException{
    	ModelMap mm = new ModelMap();
    	String newPassword = generatePassword();
    	
    	//判断是否有用户是此邮箱
    	List<User> users = userService.findByEmail(email);
    	if( users==null || users.size()==0 ){
    		mm.put("message", "没有用户使用此邮箱，请重新输入。");
            mm.put("url", "/resetPassword.jsp");
            return new ModelAndView("forward:/success.jsp", mm);
    	}
    	
       MailSenderInfo mailInfo = new MailSenderInfo(); 
   	   mailInfo.setMailServerHost("smtp.126.com"); 
   	   mailInfo.setMailServerPort("25"); 
   	   mailInfo.setValidate(true); 
   	   mailInfo.setUserName("chenmj4444@126.com"); 
   	   mailInfo.setPassword("4360449");//您的邮箱密码 
   	   mailInfo.setFromAddress("chenmj4444@126.com"); 
   	   mailInfo.setToAddress(email); //用户邮箱
   	   mailInfo.setSubject("在线数字图书馆系统新密码"); 
   	   mailInfo.setContent("这是您的新密码："+ newPassword +"，请登录后尽快修改。"); 
           //这个类主要来发送邮件
   	   SimpleMailSender sms = new SimpleMailSender();
       boolean result = sms.sendTextMail(mailInfo);//发送文体格式 
       
       if(result){
    	   //更新用户的密码
    	   for(User user : users){
    		   user.setPassword(newPassword);
    		   userService.save(user);
    	   }
    	   
    	   mm.put("message", "重置密码成功！");
           mm.put("url", "/index.do");
           return new ModelAndView("forward:/success.jsp", mm);
       }else{
    	   mm.put("message", "重置密码邮件发送失败");
           mm.put("url", "/resetPassword.jsp");
           return new ModelAndView("forward:/success.jsp", mm);
       }
    }
    
    /**
     * 生成随机密码
     */
    public String generatePassword(){
    	String s="abcdefghijklmnopqrstuvwxyz";
    	String s2="abcdefghijklmnopqrstuvwxyz0123456789";
    	char [] pw = new char[8];
    	
    	Random rd=new Random();
    	int m=rd.nextInt(24);//生成0-23的随机数
    	pw[0] = s.charAt(m);
    	for(int i = 1; i < 8; i++){
    		int m2=rd.nextInt(34);//生成0-33的随机数
    		pw[i] =  s2.charAt(m2);
    	}
    	return new String(pw);
    }

    public void setActivityStreamService(ActivityStreamServiceImpl activityStreamService) {
        this.activityStreamService = activityStreamService;
    }
}
