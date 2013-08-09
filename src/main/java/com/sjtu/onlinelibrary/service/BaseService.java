package com.sjtu.onlinelibrary.service;

import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.Persistable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午1:40
 */
public class BaseService {
    protected MutableDataAccess mutableDataAccess;

    public BaseService(final MutableDataAccess mutableDataAccess) {
        this.mutableDataAccess = mutableDataAccess;
    }

}
