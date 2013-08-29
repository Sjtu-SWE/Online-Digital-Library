package com.sjtu.onlinelibrary.service;

import java.util.List;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.web.viewmodel.BookEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午1:18
 */
public interface IBookService {
    void save(final Book book) throws DataAccessException;

    Pager<BookEditModel> findAll(int pageIndex) throws DataAccessException;
    BookEditModel findById(String id) throws DataAccessException;
    boolean delete(String id);
    /**
     * 根据类别查询所有的图书
     */
    Pager<BookEditModel> findBooksByType(int pageIndex,final String category) throws DataAccessException;
    /**
     * 判断是否有图书属于该类别
     */
    boolean findBookByType(final String category) throws DataAccessException;
    
}
