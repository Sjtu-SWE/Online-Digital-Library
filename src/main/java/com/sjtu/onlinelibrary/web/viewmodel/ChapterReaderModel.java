package com.sjtu.onlinelibrary.web.viewmodel;

import com.sjtu.onlinelibrary.entity.Chapter;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-1
 * Time: 上午12:36
 */
public class ChapterReaderModel {
    private Chapter current;
    private Chapter previous;
    private Chapter next;
    private boolean hasNext;
    private boolean hasPrevious;
    public ChapterReaderModel(Chapter currenChapter) {
        current = currenChapter;
    }

    public Chapter getCurrent() {
        return current;
    }

    public void setCurrent(Chapter current) {
        this.current = current;
    }

    public Chapter getPrevious() {
        return previous;
    }

    public void setPrevious(Chapter previous) {
        this.previous = previous;
    }

    public Chapter getNext() {
        return next;
    }

    public void setNext(Chapter next) {
        this.next = next;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
