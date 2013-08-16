package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-15
 * Time: 下午8:23
 */
@Entity
public class Chappter extends BasePersistable {
    private String title;
    private String content;
    private int orderNumber;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
