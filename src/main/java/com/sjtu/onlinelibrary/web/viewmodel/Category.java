package com.sjtu.onlinelibrary.web.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午8:24
 */
public class Category {
    private String name;
    private String value;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name,String value){
    	this.name = name;
    	this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
}
