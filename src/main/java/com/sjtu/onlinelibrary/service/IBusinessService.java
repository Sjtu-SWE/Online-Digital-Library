package com.sjtu.onlinelibrary.service;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.web.viewmodel.BusinessResult;

public interface IBusinessService {
    BusinessResult buy(String userId, String bookId) throws DataAccessException;

    BusinessResult recharge(String userIdr, String serialNumber) throws DataAccessException;

    BusinessResult addToBookshelf(String userId, String bookId) throws DataAccessException;
}
