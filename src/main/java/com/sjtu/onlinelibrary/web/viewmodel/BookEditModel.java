package com.sjtu.onlinelibrary.web.viewmodel;

import com.sjtu.onlinelibrary.entity.Book;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午4:28
 */
public class BookEditModel {
    private String editType;
    private Book bookEntity;


    public BookEditModel(String editType, Book book) {
        setEditType(editType);
        this.bookEntity = book;
    }


    public Book getBookEntity() {
        return bookEntity;
    }

    public String getId() {
        return getBookEntity().getId();
    }

    public void setId(String id) {
        getBookEntity().setId(id);
    }

    public String getName() {
        return getBookEntity().getName();
    }

    public void setName(String name) {
        getBookEntity().setBookNumber(name);
    }

    public void setAuthor(final String author) {
        getBookEntity().setAuthor(author);
    }

    public String getAuthor() {
        return getBookEntity().getAuthor();
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public void setBookNumber(final String bookNumber) {
        getBookEntity().setBookNumber(bookNumber);
    }

    public String getBookNumber() {
        return getBookEntity().getBookNumber();
    }

    public void setPublisher(final String publisher) {
        getBookEntity().setPublisher(publisher);
    }

    public String getPublisher() {
        return getBookEntity().getPublisher();
    }

    public void setPrice(final int price) {
        getBookEntity().setPrice(price);
    }

    public int getPrice() {
        return getBookEntity().getPrice();
    }

    public Date getPublishDate() {
        return getBookEntity().getPublishDate();
    }

    public void setPublishDate(final Date date) {
        getBookEntity().setPublishDate(date);
    }

    public void setCategory(final String category) {
        getBookEntity().setCategory(category);
    }

    public String getCategory() {
        return getBookEntity().getCategory();
    }

    public void setKeywords(String keywords) {
        getBookEntity().setKeywords(keywords);
    }

    public String getKeywords() {
        return getBookEntity().getKeywords();
    }
}
