package com.sjtu.onlinelibrary.service;

import java.util.List;
import java.util.Map;

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

    void increaseAmount(String bookId, AmountType amountType) throws DataAccessException;
    /**
     * 根据类别查询�?��的图�?
     */
    Pager<BookEditModel> findBooksByType(int pageIndex, final String category) throws DataAccessException;

    /**
     * 根据条件查询图书
     */
    Pager<BookEditModel> findBooksByName(int pageIndex, Map<String, Object> condition) throws DataAccessException;
    
    /**
     * 判断是否有图书属于该类别
     */
    boolean findBookByType(final String category) throws DataAccessException;
    
    /**
     * 根据点击�?鲜花/鸡蛋/购买量排序得到图书列�?
     */
    Pager<BookEditModel> findAll(int pageIndex, String orderFields) throws DataAccessException;
    
}
