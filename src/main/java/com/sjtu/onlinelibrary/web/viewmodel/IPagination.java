package com.sjtu.onlinelibrary.web.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午2:43
 */
public interface IPagination {
    int getTotalCount();
    int getPageIndex();
    int getPageSize();
    int getTotalPage();
}
