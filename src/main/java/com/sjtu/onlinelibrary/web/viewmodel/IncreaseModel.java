package com.sjtu.onlinelibrary.web.viewmodel;

import com.sjtu.onlinelibrary.service.AmountType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-31
 * Time: 上午11:05
 */
public class IncreaseModel {
    private String bookId;
    private AmountType amountType;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }
}
