package com.sjtu.onlinelibrary.service.impl;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.entity.PointCard;
import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.entity.UserBook;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.IBusinessService;
import com.sjtu.onlinelibrary.web.viewmodel.BusinessResult;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-1
 * Time: 下午6:06
 */
public class BusinessServiceImpl extends BaseService implements IBusinessService {
    public BusinessServiceImpl(MutableDataAccess mutableDataAccess) {
        super(mutableDataAccess);
    }

    @Override
    public BusinessResult buy(String userId, String bookId) throws DataAccessException {
        UserBook userBook = new UserBook();
        User user = mutableDataAccess.findById(User.class, userId);
        Book book = mutableDataAccess.findById(Book.class, bookId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("bookId", bookId);
        List<UserBook> userBooks = mutableDataAccess.paging(UserBook.class, 1, 1, map);
        if (userBooks.size() > 0) {
            if (userBooks.get(0).isHasBuyed() && book.getPrice() > 0) {
                return new BusinessResult(BusinessResult.ResultStatus.FAIL, "该书已经购买，不需要重复购买");
            }
            userBook = userBooks.get(0);
        }

        userBook.setBookId(bookId);
        userBook.setUserId(userId);
        if (book.getPrice() <= 0) {
            userBook.setHasBuyed(userBook.isHasBuyed());
            mutableDataAccess.save(userBook);
            return new BusinessResult(BusinessResult.ResultStatus.OK, "免费书籍,已经保存到书架上");
        } else {
            if (user.getCredits() < book.getPrice()) {
                return new BusinessResult(BusinessResult.ResultStatus.FAIL, "购买失败，您的信用值余额不足，请充值");
            } else {
                user.setCredits(user.getCredits() - book.getPrice());
                userBook.setHasBuyed(true);
                mutableDataAccess.save(userBook);
                mutableDataAccess.save(user);
                book.setSellAmount(book.getSellAmount() + 1);
                mutableDataAccess.save(book);
                return new BusinessResult(BusinessResult.ResultStatus.OK, "购买成功，已经保存到书架上");
            }
        }
    }

    @Override
    public BusinessResult hasPurchased(final String userId, final String bookId) throws DataAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("bookId", bookId);
        List<UserBook> userBooks = mutableDataAccess.paging(UserBook.class, 1, 1, map);
        if(userBooks.size()==0||!userBooks.get(0).isHasBuyed()) return new BusinessResult(BusinessResult.ResultStatus.FAIL,"您还未购买该书籍，不能阅读");
        return new BusinessResult(BusinessResult.ResultStatus.OK,"");
    }

    @Override
    public BusinessResult recharge(String userId, String serialNumber) throws DataAccessException {
        PointCard pointCard = mutableDataAccess.findById(PointCard.class, serialNumber);
        if (pointCard == null) {
            return new BusinessResult(BusinessResult.ResultStatus.FAIL, "充值失败，信用值卡序列号错误");
        }
        if (pointCard.isUsed()) {
            return new BusinessResult(BusinessResult.ResultStatus.FAIL, "充值失败，该信用值卡已经使用过了");
        }

        User user = mutableDataAccess.findById(User.class, userId);
        user.setCredits(user.getCredits() + pointCard.getCredits());
        pointCard.setUsed(true);
        mutableDataAccess.save(user);
        mutableDataAccess.save(pointCard);
        BusinessResult result = new BusinessResult(BusinessResult.ResultStatus.OK, String.format("充值成功，您成功的在账号里充值<small>%s</small>信用值",pointCard.getCredits()));
        return result;
    }

    @Override
    public BusinessResult addToBookshelf(String userId, String bookId) throws DataAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("bookId", bookId);
        List<UserBook> userBooks = mutableDataAccess.paging(UserBook.class, 1, 1, map);
        if (userBooks.size() > 0) {
            return new BusinessResult(BusinessResult.ResultStatus.FAIL, "该书已经在你的书架中了，无需再添加");
        }
        final UserBook userBook = new UserBook();
        userBook.setHasBuyed(false);
        userBook.setUserId(userId);
        userBook.setBookId(bookId);
        userBook.setUser(mutableDataAccess.findById(User.class,userId));
        userBook.setBook(mutableDataAccess.findById(Book.class,bookId));
        mutableDataAccess.save(userBook);
        Book book = mutableDataAccess.findById(Book.class, bookId);
        book.setUserFavoriteAmount(book.getUserFavoriteAmount() + 1);
        mutableDataAccess.save(book);
        return new BusinessResult(BusinessResult.ResultStatus.OK, "加入书架成功");
    }

    @Override
    public BusinessResult generatePointCord(final int count, final int value) throws DataAccessException {
        for (int i = 0; i < count; i++) {
            PointCard pointCard = new PointCard();
            pointCard.setCredits(value);
            pointCard.setUsed(false);
            mutableDataAccess.save(pointCard);
        }
        return new BusinessResult(BusinessResult.ResultStatus.OK, "信用值卡生成成功");
    }

    @Override
    public Pager<PointCard> pagingAll(int pageIndex) throws DataAccessException {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }

        final List<PointCard> pointCards = mutableDataAccess.paging(PointCard.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE,null,"used,createdOn");
        final Pager<PointCard> pointCardPager = new Pager<PointCard>(pageIndex);
        pointCardPager.setListObject(pointCards);
        pointCardPager.setTotalCount(mutableDataAccess.count(PointCard.class));
        return pointCardPager;
    }
}
