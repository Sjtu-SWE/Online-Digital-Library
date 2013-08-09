package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-28
 * Time: 下午3:50
 */
@Entity
public class Book extends BasePersistable {

    private String name;
    private String author;
    private String category;
    private String keywords;
    private String description;
    private String publisher;
    private Date publishDate;
    private String bookNumber;
    private String bookContentPath;
    private String bookCoverImgPath;
    private int price;
    private int clickAmount;
    private int userLikeAmount;
    private int userUnlikeAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookContentPath() {
        return bookContentPath;
    }

    public void setBookContentPath(String bookContentPath) {
        this.bookContentPath = bookContentPath;
    }

    public String getBookCoverImgPath() {
        return bookCoverImgPath;
    }

    public void setBookCoverImgPath(String bookCoverImgPath) {
        this.bookCoverImgPath = bookCoverImgPath;
    }

    public int getClickAmount() {
        return clickAmount;
    }

    public void setClickAmount(int clickAmount) {
        this.clickAmount = clickAmount;
    }

    public int getUserLikeAmount() {
        return userLikeAmount;
    }

    public void setUserLikeAmount(int userLikeAmount) {
        this.userLikeAmount = userLikeAmount;
    }

    public int getUserUnlikeAmount() {
        return userUnlikeAmount;
    }

    public void setUserUnlikeAmount(int userUnlikeAmount) {
        this.userUnlikeAmount = userUnlikeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
