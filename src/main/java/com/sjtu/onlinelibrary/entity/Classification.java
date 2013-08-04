package com.sjtu.onlinelibrary.entity;

import java.util.Date;
import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;

/**
 * @author Crystal
 *
 */

@Entity
public class Classification extends BasePersistable {

	private String classificationName;
	private Date createDate;
	private String note;
	
	public String getClassificationName() {
		return classificationName;
	}
	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
