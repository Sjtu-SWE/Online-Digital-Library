package com.sjtu.onlinelibrary.web.viewmodel;

import com.sjtu.onlinelibrary.entity.Comment;

public class CommentModel {
    private Comment comment;

    public String getId() {
        return getCommentEntity().getId();
    }

    public void setId(String id) {
        getCommentEntity().setId(id);
    }
    public String getContent() {
        return getCommentEntity().getContent();
    }

    public void setContent(String content) {
        getCommentEntity().setContent(content);
    }

    public String getBookId() {
        return getCommentEntity().getBookId();
    }

    public void setBookId(String bookId) {
        getCommentEntity().setBookId(bookId);
    }

    public String getUserId() {
        return getCommentEntity().getUserId();
    }

    public void setUserId(String userId) {
        getCommentEntity().setUserId(userId);
    }

    public String getUserName() {
      return   getCommentEntity().getUserName();
    }

    public void setUserName(String userName) {
        getCommentEntity().setUserName(userName);
    }

    public Comment getCommentEntity() {
        return comment;
    }
}
