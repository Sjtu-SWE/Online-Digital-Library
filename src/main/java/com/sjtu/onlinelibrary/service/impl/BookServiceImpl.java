package com.sjtu.onlinelibrary.service.impl;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.entity.UserBook;
import com.sjtu.onlinelibrary.entity.UserVoteBook;
import com.sjtu.onlinelibrary.service.AmountType;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.IBookService;
import com.sjtu.onlinelibrary.web.viewmodel.BookEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.BusinessResult;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public BusinessResult hasVoted(String bookId, String userId) throws DataAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bookId", bookId);
        map.put("userId", userId);
        List<UserVoteBook> userVoteBooks = mutableDataAccess.paging(UserVoteBook.class, 1, 1, map);
        if (userVoteBooks.size() > 0) {
            return new BusinessResult(BusinessResult.ResultStatus.FAIL, "您已经投过鲜花或者鸡蛋了");
        }
        return new BusinessResult(BusinessResult.ResultStatus.OK, "");
    }
    private void createVote(String bookId,String userId) throws DataAccessException {
        UserVoteBook userVoteBook=new UserVoteBook();
        userVoteBook.setBookId(bookId);
        userVoteBook.setUserId(userId);
        mutableDataAccess.save(userVoteBook);
    }

    @Override
    public BusinessResult increaseAmount(String bookId, String userId, AmountType amountType) throws DataAccessException {
        Book book = this.mutableDataAccess.findById(Book.class, bookId);
        if (book == null) return new BusinessResult(BusinessResult.ResultStatus.FAIL, "不存在该书");
        BusinessResult result = new BusinessResult(BusinessResult.ResultStatus.OK, "");
        switch (amountType) {
            case clickAmount:
                book.setClickAmount(book.getClickAmount() + 1);
                break;
            case userFavoriteAmount:
                book.setUserFavoriteAmount(book.getUserFavoriteAmount() + 1);
                break;
            case userLikeAmount:
                result = hasVoted(bookId, userId);
                if (result.getResultStatus() == BusinessResult.ResultStatus.OK) {
                    book.setUserLikeAmount(book.getUserLikeAmount() + 1);
                    createVote(bookId,userId);
                }
                break;
            case userUnlikeAmount:
                result = hasVoted(bookId, userId);
                if (result.getResultStatus() == BusinessResult.ResultStatus.OK) {
                    book.setUserUnlikeAmount(book.getUserUnlikeAmount() + 1);
                    createVote(userId,bookId);
                }
                break;
            case sellAmount:
                book.setSellAmount(book.getSellAmount() + 1);
                break;
        }
        this.mutableDataAccess.save(book);
        return result;
    }

    @Override
    public void decreaseAmount(String bookId, AmountType amountType) throws DataAccessException {
        Book book = this.mutableDataAccess.findById(Book.class, bookId);
        if (book == null) return;
        switch (amountType) {
            case clickAmount:
                book.setClickAmount(book.getClickAmount() - 1);
                break;
            case userFavoriteAmount:
                book.setUserFavoriteAmount(book.getUserFavoriteAmount() - 1);
                break;
            case userLikeAmount:
                book.setUserLikeAmount(book.getUserLikeAmount() - 1);
                break;
            case userUnlikeAmount:
                book.setUserUnlikeAmount(book.getUserUnlikeAmount() - 1);
                break;
            case sellAmount:
                book.setSellAmount(book.getSellAmount() - 1);
                break;
        }
        this.mutableDataAccess.save(book);
    }

    /**
     * 根据类别查询所有的图书
     */
    @Override
    public Pager<BookEditModel> findBooksByType(int pageIndex, final String category) throws DataAccessException {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("categoryId", category);

        final List<Book> books = mutableDataAccess.paging(Book.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE, condition);
        final List<BookEditModel> bookEditModelList = new ArrayList<BookEditModel>();
        for (final Book book : books) {
            bookEditModelList.add(new BookEditModel("", book));
        }
        final Pager<BookEditModel> bookPager = new Pager<BookEditModel>(pageIndex);
        bookPager.setListObject(bookEditModelList);
        bookPager.setTotalCount(mutableDataAccess.count(Book.class, condition));
        return bookPager;
    }

    /**
     * 判断是否有图书属于该类别
     */
    @Override
    public boolean findBookByType(String category) throws DataAccessException {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("category", category);

        List<Book> books = mutableDataAccess.listByFilter(Book.class, condition);
        if (books != null && books.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Pager<BookEditModel> findBooksByName(int pageIndex, Map<String, Object> condition) throws DataAccessException {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        //根据书名进行排序
        final List<Book> books = mutableDataAccess.paging(Book.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE, condition, "name");
        final List<BookEditModel> bookEditModelList = new ArrayList<BookEditModel>();
        for (final Book book : books) {
            bookEditModelList.add(new BookEditModel("", book));
        }
        final Pager<BookEditModel> bookPager = new Pager<BookEditModel>(pageIndex);
        bookPager.setListObject(bookEditModelList);
        bookPager.setTotalCount(mutableDataAccess.count(Book.class, condition));
        return bookPager;
    }

    @Override
    public Pager<BookEditModel> findAll(int pageIndex, String orderFields) throws DataAccessException {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        final List<Book> books = mutableDataAccess.paging(Book.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE, null, orderFields);
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
    public List<BookEditModel> findTop(String orderFields) throws DataAccessException {
        final List<Book> books = mutableDataAccess.listByFilter(Book.class, new HashMap<String, Object>(), orderFields);
        final List<BookEditModel> bookEditModelList = new ArrayList<BookEditModel>();
        for (final Book book : books) {
            bookEditModelList.add(new BookEditModel("", book));
        }
        return bookEditModelList;
    }

    @Override
    public List<UserBook> findUserBook(final String userId, final boolean purchased) throws DataAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hasBuyed", purchased);
        map.put("userId", userId);
        return mutableDataAccess.listByFilter(UserBook.class, map);
    }

    @Override
    public UserBook deleteUserBook(String userBookId) throws DataAccessException {
        UserBook userBook = mutableDataAccess.findById(UserBook.class, userBookId);
        mutableDataAccess.delete(UserBook.class, userBookId);
        return userBook;
    }
}
