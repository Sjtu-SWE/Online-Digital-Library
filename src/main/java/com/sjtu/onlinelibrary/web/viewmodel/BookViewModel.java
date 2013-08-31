package com.sjtu.onlinelibrary.web.viewmodel;


import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.entity.Comment;
import com.sjtu.onlinelibrary.util.LangUtil;

import java.math.BigDecimal;
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

    public String getLikeRate() {
        int total = getBook().getUserLikeAmount() + getBook().getUserUnlikeAmount();
        if (total == 0) {
            return "0";
        }
        return LangUtil.covertDouble2(((double) getBook().getUserLikeAmount()) / ((double) total) * 100);
    }

    public String getUnlikeRate() {
        int total = getBook().getUserLikeAmount() + getBook().getUserUnlikeAmount();
        if (total == 0) {
            return "0";
        }
        return LangUtil.covertDouble2(((double) getBook().getUserUnlikeAmount()) / ((double) total) * 100);
    }

    public int getTotalLikeUnlikeAmount() {
        return getBook().getUserLikeAmount() + getBook().getUserUnlikeAmount();
    }
}
