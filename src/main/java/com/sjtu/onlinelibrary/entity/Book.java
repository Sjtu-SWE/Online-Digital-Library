package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;

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
}
