package com.sjtu.onlinelibrary.service;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.Comment;
import com.sjtu.onlinelibrary.web.viewmodel.CommentModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-25
 * Time: 上午11:18
 */
public interface ICommentServcie {
    void save(Comment comment) throws DataAccessException;

    Comment findById(String id) throws DataAccessException;

    boolean delete(String id);

    Pager<Comment> findAll(String bookId, int pageIndex) throws DataAccessException;
}
