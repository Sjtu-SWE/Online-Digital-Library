package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
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
    @Reference
    private User user;
    private String bookId;
    @Reference
    private Book book;
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

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }
}
