package com.sjtu.onlinelibrary.service;


import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.ActivityStream;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

import java.util.Map;

public interface IActivityStreamService  {
    void addPurchaseActivity(String userId, String bookId) throws DataAccessException;
    void addRechargeActivity(String userId, String pointCardId) throws DataAccessException;
    Pager<ActivityStream> listPurchaseRecords(String userId,int index) throws DataAccessException;
    Pager<ActivityStream> listRechargeRecords(String userId,int index) throws DataAccessException;
    Pager<ActivityStream> findAll(int index,Map<String,Object> condition,String orderFields) throws DataAccessException;
}
