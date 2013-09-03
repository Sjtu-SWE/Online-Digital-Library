package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import com.sjtu.onlinelibrary.BasePersistable;

@Entity
public class ActivityStream extends BasePersistable {
    @Indexed
    private String userId;
    @Reference
    private User user;
    @Indexed
    private String pointCardId;
    @Reference
    private PointCard pointCard;
    @Indexed
    private String bookId;
    @Reference
    private Book book;

    private ActivityType activityType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPointCardId() {
        return pointCardId;
    }

    public void setPointCardId(String pointCardId) {
        this.pointCardId = pointCardId;
    }

    public PointCard getPointCard() {
        return pointCard;
    }

    public void setPointCard(PointCard pointCard) {
        this.pointCard = pointCard;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
}
