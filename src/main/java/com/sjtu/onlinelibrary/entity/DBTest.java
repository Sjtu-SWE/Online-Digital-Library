package com.sjtu.onlinelibrary.entity;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.impl.DataAccessMongoImpl;
import com.sjtu.onlinelibrary.util.MongoConfig;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-28
 * Time: 下午5:16
 */
public class DBTest {

    public static final String TEST_ID = "test.id";
    public static final String TEST_AUTHOR = "test.author";
    public static final String TEST_NAME = "test.name";

    public static void main(final String[] args) throws DataAccessException, UnknownHostException {
        final MongoConfig mongoConfig = new MongoConfig();
        mongoConfig.setDbName("onlineLibrary");
        mongoConfig.setServerList("localhost");

        final MutableDataAccess db = new DataAccessMongoImpl(mongoConfig);
        db.delete(Book.class, TEST_ID);

        final Book book = new Book();
        book.setId(TEST_ID);
        book.setAuthor(TEST_AUTHOR);
        book.setName(TEST_NAME);
        db.save(book);
        System.out.println("save book success");

        final Book book1 = db.findById(Book.class, TEST_ID);
        assert book1 != null;

        final Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("name", TEST_NAME);
        final Iterable<Book> result = db.listByFilter(Book.class, condition);
        System.out.println("listByFilter result : name is " + result.iterator().next().getName());
        db.delete(Book.class,TEST_ID);
        System.out.println("deleted test data");
        
//        final User user = new User();
//        user.setId("user.id");
//        user.setCreatedOn(new Date());
//        user.setUserName("admin");
//        user.setPassword("admin");
//        db.save(user);
//        System.out.println("save user success");
    }
}
