package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-1
 * Time: 下午5:46
 */
@Entity
public class PointCard extends BasePersistable {
    private int credits;
    private boolean used;

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
