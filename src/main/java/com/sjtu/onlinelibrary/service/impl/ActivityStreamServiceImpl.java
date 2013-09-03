package com.sjtu.onlinelibrary.service.impl;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.*;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.IActivityStreamService;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.Pagination;

import java.util.HashMap;
import java.util.Map;

public class ActivityStreamServiceImpl extends BaseService implements IActivityStreamService {
    public ActivityStreamServiceImpl(MutableDataAccess mutableDataAccess) {
        super(mutableDataAccess);
    }

    @Override
    public void addPurchaseActivity(String userId, String bookId) throws DataAccessException {
        ActivityStream activityStream=new ActivityStream();
        activityStream.setUserId(userId);
        activityStream.setUser(mutableDataAccess.findById(User.class,userId));
        activityStream.setBookId(bookId);
        activityStream.setBook(mutableDataAccess.findById(Book.class,bookId));
        activityStream.setActivityType(ActivityType.PurshaseBook);
        mutableDataAccess.save(activityStream);
    }

    @Override
    public void addRechargeActivity(String userId, String pointCardId) throws DataAccessException {
        ActivityStream activityStream=new ActivityStream();
        activityStream.setUserId(userId);
        activityStream.setUser(mutableDataAccess.findById(User.class,userId));
        activityStream.setPointCard(mutableDataAccess.findById(PointCard.class,pointCardId));
        activityStream.setPointCardId(pointCardId);
        activityStream.setActivityType(ActivityType.Recharge);
        mutableDataAccess.save(activityStream);
    }

    @Override
    public Pager<ActivityStream> listPurchaseRecords(String userId, int index) throws DataAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("activityType", ActivityType.PurshaseBook);
        return findAll(index,map,"-createdOn");
    }

    @Override
    public Pager<ActivityStream> listRechargeRecords(String userId, int index) throws DataAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("activityType", ActivityType.Recharge);
        return findAll(index,map,"-createdOn");
    }

    @Override
    public Pager<ActivityStream> findAll(int index, Map<String, Object> condition, String orderFields) throws DataAccessException {
        Pager<ActivityStream> activityStreamPager = new Pager<ActivityStream>(index);
        activityStreamPager.setListObject(mutableDataAccess.paging(ActivityStream.class, index, Pagination.DEFAULT_PAGE_SIZE, condition, orderFields));
        activityStreamPager.setTotalCount(mutableDataAccess.count(ActivityStream.class, condition));
        return activityStreamPager;
    }
}
