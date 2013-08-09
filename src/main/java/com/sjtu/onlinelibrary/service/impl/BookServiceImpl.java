package com.sjtu.onlinelibrary.service.impl;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.IBookService;
import com.sjtu.onlinelibrary.web.viewmodel.BookEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.Pagination;

import java.util.ArrayList;
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
    public Pager<BookEditModel> findAll(int pageIndex) throws DataAccessException {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        final List<Book> books = mutableDataAccess.paging(Book.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE);
        final List<BookEditModel> bookEditModelList = new ArrayList<BookEditModel>();
        for (final Book book : books) {
            bookEditModelList.add(new BookEditModel("", book));
        }
        final Pager<BookEditModel> bookPager = new Pager<BookEditModel>(pageIndex);
        bookPager.setListObject(bookEditModelList);
        bookPager.setTotalCount(mutableDataAccess.count(Book.class));
        return bookPager;
    }

    @Override
    public BookEditModel findById(final String id) throws DataAccessException {
        Book book = mutableDataAccess.findById(Book.class, id);
        return new BookEditModel("编辑书籍", book);
    }

    @Override
    public boolean delete(final String id) {
        try {
            mutableDataAccess.delete(Book.class, id);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
