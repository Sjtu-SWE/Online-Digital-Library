package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

@Entity
public class Comment extends BasePersistable {
    private String bookId;
    private String content;
    private Pager<Comment> comments;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Pager<Comment> getComments() {
        return comments;
    }

    public void setComments(Pager<Comment> comments) {
        this.comments = comments;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
