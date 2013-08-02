package com.sjtu.onlinelibrary.web.user;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.impl.DataAccessMongoImpl;
import com.sjtu.onlinelibrary.util.MongoConfig;

/**
 *  @author Crystal
 *
 */
@Service
public class UserServiceImpl implements IUserService{
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	
	final MongoConfig mongoConfig = new MongoConfig();
    
	public User findById(String id) throws Exception{
		mongoConfig.setDbName("onlineLibrary");
	    mongoConfig.setServerList("localhost");
	    MutableDataAccess db = new DataAccessMongoImpl(mongoConfig);
	    
	    User user = db.findById(User.class, id);
        assert user != null:"该用户不存在";
		return user;
	}
	
	public User checkLogin(String userName, String password) throws Exception{
		mongoConfig.setDbName("onlineLibrary");
	    mongoConfig.setServerList("localhost");
	    MutableDataAccess db = new DataAccessMongoImpl(mongoConfig);
	    
	    Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userName", userName);
        condition.put("password", password);
        Iterable<User> result = db.listByFilter(User.class, condition);
        if( result.iterator().hasNext() ){
        	return result.iterator().next();
        }
		return null;
	}
	
	
}
