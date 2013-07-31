package com.sjtu.onlinelibrary.web.user;

import com.sjtu.onlinelibrary.entity.User;

public interface IUserService {

	public User findById(String id) throws Exception;
	
	public boolean checkLogin(String userName, String password) throws Exception;
}
