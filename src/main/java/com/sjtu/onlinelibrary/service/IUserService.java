package com.sjtu.onlinelibrary.service;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.UserEditModel;

public interface IUserService {

	Pager<UserEditModel> findAll(int pageIndex) throws DataAccessException;
	UserEditModel findById(String id) throws DataAccessException;
	void save(final User user) throws DataAccessException;
	boolean delete(String id);
	 
	User checkLogin(String userName, String password) throws Exception;
	
}
