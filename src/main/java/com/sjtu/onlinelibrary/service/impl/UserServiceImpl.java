package com.sjtu.onlinelibrary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.IUserService;
import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.Pagination;
import com.sjtu.onlinelibrary.web.viewmodel.UserEditModel;

/**
 *  @author Crystal
 *
 */
public class UserServiceImpl  extends BaseService implements IUserService {
	
	public UserServiceImpl(MutableDataAccess mutableDataAccess) {
		super(mutableDataAccess);
	}
	
	@Override
	public UserEditModel findById(String id) throws DataAccessException{
		User user = mutableDataAccess.findById(User.class, id);
        return new UserEditModel("编辑用户", user);
	}
	
	@Override
	public User checkLogin(String userName, String password) throws Exception{//前台页面登录用
	    Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("username", userName);
        condition.put("password", password);
        Iterable<User> result = mutableDataAccess.listByFilter(User.class, condition);
        if( result.iterator().hasNext() ){
        	return result.iterator().next();
        }
		return null;
	}

	@Override
	public Pager<UserEditModel> findAll(int pageIndex, Map<String, Object> condition) 	throws DataAccessException {
		if (pageIndex <= 0) {
            pageIndex = 1;
        }
		//根据用户名进行排序
        final List<User> users = mutableDataAccess.paging(User.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE, condition , "username");
        final List<UserEditModel> userEditModelList = new ArrayList<UserEditModel>();
        for (final User user : users) {
        	userEditModelList.add(new UserEditModel("", user));
        }
        final Pager<UserEditModel> userPager = new Pager<UserEditModel>(pageIndex);
        userPager.setListObject(userEditModelList);
        userPager.setTotalCount(mutableDataAccess.count(User.class,condition));
        return userPager;
	}

	@Override
	public void save(User user) throws DataAccessException {
		mutableDataAccess.save(user);
	}

	@Override
	public boolean delete(String id) {
		try {
            mutableDataAccess.delete(User.class, id);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
	}

	@Override
	public User findByName(String username) throws DataAccessException {
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		List<User> users = mutableDataAccess.listByFilter(User.class, condition);
		if(users!=null && users.size()>0){
			return users.get(0);
		}
        return null;
	}
	
}
