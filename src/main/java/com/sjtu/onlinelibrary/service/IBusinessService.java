package com.sjtu.onlinelibrary.service;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.web.viewmodel.BusinessResult;

public interface IBusinessService {
    /**
     * 购买图书
     * @param userId
     * @param bookId
     * @throws DataAccessException
     */
    BusinessResult buy(String userId, String bookId) throws DataAccessException;

    /**
     * 给用户充值
     * @param userIdr
     * @param serialNumber 点卡序列号 (uuid)
     * @throws DataAccessException
     */
    BusinessResult recharge(String userIdr, String serialNumber) throws DataAccessException;

    /**
     * 添加书籍到书架
     * @param userId
     * @param bookId
     * @return
     * @throws DataAccessException
     */
    BusinessResult addToBookshelf(String userId, String bookId) throws DataAccessException;

    /**
     * 生成点卡
     * @param count 需要生成点卡数量
     * @param value 面值
     */
    BusinessResult generatePointCord(int count,final int value) throws DataAccessException;
}
