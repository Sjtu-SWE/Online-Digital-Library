package com.sjtu.onlinelibrary.service.impl;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.IBookService;
import com.sjtu.onlinelibrary.web.viewmodel.BookEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午1:40
 */
public class BookServiceImpl extends BaseService implements IBookService {
    public BookServiceImpl(final MutableDataAccess mutableDataAccess) {
        super(mutableDataAccess);
    }

    @Override
    public void save(final Book book) throws DataAccessException {
        mutableDataAccess.save(book);
    }

    @Override
    public Pager<Book> findAll() throws DataAccessException {
        final List<Book> books = mutableDataAccess.listAll(Book.class);
        final Pager<Book> bookPager = new Pager<Book>();
        bookPager.setListObject(books);
        bookPager.setTotalCount(books.size());
        return bookPager;
    }

    @Override
    public BookEditModel findById(String id) throws DataAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
