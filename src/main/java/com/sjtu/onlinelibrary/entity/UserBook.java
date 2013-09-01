package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-1
 * Time: 下午5:38
 */
@Entity
public class UserBook extends BasePersistable {
    private String userId;
    private String bookId;
    private boolean hasBuyed;
    private String tags;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public boolean isHasBuyed() {
        return hasBuyed;
    }

    public void setHasBuyed(boolean hasBuyed) {
        this.hasBuyed = hasBuyed;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
