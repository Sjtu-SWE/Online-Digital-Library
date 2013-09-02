package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-2
 * Time: 下午10:59
 */
@Entity
public class UserVoteBook extends BasePersistable {
    private String bookId;
    private String userId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
