package com.sjtu.onlinelibrary.web.viewmodel;

import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.util.LangUtil;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    public BookEditModel() {
        this("创建书籍", new Book());
    }

    public BookEditModel(final String editType, final Book book) {
        setEditType(editType);
        this.bookEntity = book;
    }


    public Book innerBookEntity() {
        return bookEntity;
    }

    public String getId() {
        return innerBookEntity().getId();
    }

    public void setId(String id) {
        innerBookEntity().setId(id);
    }

    @NotEmpty(message = "书名不能为空。")
    public String getName() {
        return innerBookEntity().getName();
    }

    public void setName(String name) {
        innerBookEntity().setName(name);
    }

    public void setAuthor(final String author) {
        innerBookEntity().setAuthor(author);
    }

    @NotEmpty(message = "作者不能为空。")
    public String getAuthor() {
        return innerBookEntity().getAuthor();
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public void setBookNumber(final String bookNumber) {
        innerBookEntity().setBookNumber(bookNumber);
    }

    public String getBookNumber() {
        return innerBookEntity().getBookNumber();
    }

    public void setPublisher(final String publisher) {
        innerBookEntity().setPublisher(publisher);
    }

    public String getPublisher() {
        return innerBookEntity().getPublisher();
    }

    public void setPrice(final int price) {
        innerBookEntity().setPrice(price);
    }

    @Min(value = 0, message = "请输入合法的数字")
    @Max(value = Integer.MAX_VALUE, message = "请输入合法的数字")
    public int getPrice() {
        return innerBookEntity().getPrice();
    }

    @NotEmpty(message = "请输入合法发的日期")
    public String getPublishDate() {
        if (innerBookEntity().getPublishDate() == null) {
            return "";
        }
        return LangUtil.getDefaultDateFormat().format(innerBookEntity().getPublishDate());
    }

    public void setPublishDate(final String date) {
        try {
            if (LangUtil.isNullOrEmpty(date)) return;
            innerBookEntity().setPublishDate(LangUtil.getDefaultDateFormat().parse(date));

        } catch (Exception e) {
            return;
        }
    }

    public void setCategory(final String category) {
        innerBookEntity().setCategory(category);
    }

    public String getCategory() {
        return innerBookEntity().getCategory();
    }

    public void setKeywords(String keywords) {
        innerBookEntity().setKeywords(keywords);
    }

    public String getKeywords() {
        return innerBookEntity().getKeywords();
    }

    public String getBookCoverImgPath() {
        return innerBookEntity().getBookCoverImgPath();
    }

    public void setBookCoverImgPath(String bookCoverImgPath) {
        innerBookEntity().setBookCoverImgPath(bookCoverImgPath);
    }
    public Date getCreatedOn() {
        return innerBookEntity().getCreatedOn();
    }

    public void setCreatedOn(final Date createdOn) {
        innerBookEntity().setCreatedOn(createdOn);
    }
}
