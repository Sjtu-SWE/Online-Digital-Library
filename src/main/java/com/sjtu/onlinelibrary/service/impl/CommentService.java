package com.sjtu.onlinelibrary.service.impl;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.Comment;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.ICommentServcie;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-25
 * Time: 上午11:21
 */
public class CommentService extends BaseService implements ICommentServcie {
    public CommentService(MutableDataAccess mutableDataAccess) {
        super(mutableDataAccess);
    }

    @Override
    public void save(Comment comment) throws DataAccessException {
        mutableDataAccess.save(comment);
    }

    @Override
    public Pager<Comment> findAll(String bookId, int pageIndex) throws DataAccessException {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("bookId", bookId);
        final List<Comment> comments = mutableDataAccess.paging(Comment.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE, condition);

        final Pager<Comment> commentPager = new Pager<Comment>(pageIndex);
        commentPager.setListObject(comments);
        commentPager.setTotalCount(mutableDataAccess.count(Comment.class, condition));
        return commentPager;
    }

    @Override
    public Comment findById(String id) throws DataAccessException {
        return mutableDataAccess.findById(Comment.class,id);
    }

    @Override
    public boolean delete(String id) {
        try {
            mutableDataAccess.delete(Comment.class, id);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
