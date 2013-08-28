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
    public static final String TEST_BOOK_NUMBER = "test.book.number";
    public static final String TEST_CATEGORY = "test.category";
    public static final String TEST_KEYWORDS = "test.keywords";
    public static final String TEST_DESCRIPTION = "test.description";
    public static final String TEST_CHAPTER_ID = "test.chapter.id";

    public static void main(final String[] args) throws DataAccessException, UnknownHostException {
        final MongoConfig mongoConfig = new MongoConfig();
        mongoConfig.setDbName("onlineLibrary");
//        mongoConfig.setServerList("ec2-46-137-198-225.ap-southeast-1.compute.amazonaws.com");
        mongoConfig.setServerList("localhost");

        final MutableDataAccess db = new DataAccessMongoImpl(mongoConfig);
//        db.delete(Book.class, TEST_ID);

        final Book book = new Book();
        book.setId(TEST_ID);
        book.setAuthor(TEST_AUTHOR);
        book.setName(TEST_NAME);
        book.setBookNumber(TEST_BOOK_NUMBER);
        book.setCategory(TEST_CATEGORY);
        book.setKeywords(TEST_KEYWORDS);
        book.setDescription(TEST_DESCRIPTION);
        book.setPublishDate(new Date());
        book.setPublisher("新华出版社");
        db.save(book);
//        final Book book2 = new Book();
//        book2.setAuthor(TEST_AUTHOR);
//        book2.setName(TEST_NAME);
//        db.save(book2);
//        final Book book3 = new Book();
//        book3.setAuthor(TEST_AUTHOR);
//        book3.setName(TEST_NAME);
//        db.save(book3);
//        final Book book4 = new Book();
//        book4.setAuthor(TEST_AUTHOR);
//        book4.setName(TEST_NAME);
//        db.save(book4);
        System.out.println("save book success");

        final Book book1 = db.findById(Book.class, TEST_ID);
        assert book1 != null;

        final Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("name", TEST_NAME);
        final Iterable<Book> result = db.listByFilter(Book.class, condition);
        System.out.println("listByFilter result : name is " + result.iterator().next().getName());
//        db.delete(Book.class,TEST_ID);
        System.out.println("deleted test data");

        final User user = new User();
        user.setId("user.id");
        user.setCreatedOn(new Date());
        user.setUsername("admin");
        user.setPassword("admin");
        user.setRoleName("ROLE_ADMIN");
        user.setRole(1);
        db.save(user);

        for (int i = 1; i <= 200; i++) {
            Chapter chapter = new Chapter();
            chapter.setId(String.format("chapter%s",i));
            chapter.setBookId(TEST_ID);
            chapter.setContent("测试");
            chapter.setTitle(String.format("第%s章 测试", i));
            chapter.setOrderNumber(i);
            db.save(chapter);
        }

        System.out.println("save user success");
    }
}
