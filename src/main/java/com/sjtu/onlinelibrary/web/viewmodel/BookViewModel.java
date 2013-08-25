package com.sjtu.onlinelibrary.web.viewmodel;


import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.entity.Comment;

import java.util.List;

public class BookViewModel {
    private Book book;
    private Pager<Comment> comments;
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Pager<Comment> getComments() {
        return comments;
    }

    public void setComments(Pager<Comment> comments) {
        this.comments = comments;
    }
}
