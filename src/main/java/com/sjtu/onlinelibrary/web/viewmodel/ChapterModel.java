package com.sjtu.onlinelibrary.web.viewmodel;

import com.sjtu.onlinelibrary.entity.Chapter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-24
 * Time: 上午10:55
 */
public class ChapterModel {
    private String editType;
    private Chapter chapterEntiry;

    public ChapterModel() {
        this("创建章节", new Chapter());
    }

    public ChapterModel(String type, Chapter chapter) {
        this.editType = type;
        this.chapterEntiry = chapter;
    }
    public String getId() {
        return getChapterEntity().getId();
    }

    public void setId(String id) {
        getChapterEntity().setId(id);
    }
    public Chapter getChapterEntity() {
        return this.chapterEntiry;
    }

    @NotEmpty(message = "章节名不能为空。")
    public String getTitle() {
        return getChapterEntity().getTitle();
    }

    public void setTitle(String title) {
        this.getChapterEntity().setTitle(title);
    }

    public String getContent() {
        return getChapterEntity().getContent();
    }

    public void setContent(String content) {
        getChapterEntity().setContent(content);
    }

    public int getOrderNumber() {
        return getChapterEntity().getOrderNumber();
    }

    public void setOrderNumber(int orderNumber) {
        getChapterEntity().setOrderNumber(orderNumber);
    }

    public String getBookId() {
        return getChapterEntity().getBookId();
    }

    public void setBookId(String bookId) {
        getChapterEntity().setBookId(bookId);
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }
    public Date getCreatedOn() {
        return getChapterEntity().getCreatedOn();
    }

    public void setCreatedOn(final Date createdOn) {
        getChapterEntity().setCreatedOn(createdOn);
    }
}
