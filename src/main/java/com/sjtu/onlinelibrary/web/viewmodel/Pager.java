package com.sjtu.onlinelibrary.web.viewmodel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午2:57
 */
public class Pager<T> extends Pagination {
    private List<T> listObject;

    public List<T> getList(){
        return listObject;
    }

    public void setListObject(final List<T> listObject) {
        this.listObject = listObject;
    }
}
