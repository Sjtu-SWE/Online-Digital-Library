package com.sjtu.onlinelibrary.web.viewmodel;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import com.sjtu.onlinelibrary.entity.Classification;

/**
 * @author Crystal
 *
 */
public class ClassificationEditModel {

	private String editType;
    private Classification classificationEntity;

    public ClassificationEditModel() {
        this("添加类别", new Classification());
    }

    public ClassificationEditModel(final String editType, final Classification classification) {
        setEditType(editType);
        this.classificationEntity = classification;
    }

    public Classification innerClassificationEntity() {
        return classificationEntity;
    }

    public String getId() {
        return innerClassificationEntity().getId();
    }

    public void setId(String id) {
    	innerClassificationEntity().setId(id);
    }

    @NotEmpty(message = "类别名不能为空。")
    public String getClassificationName() {
        return innerClassificationEntity().getClassificationName();
    }

    public void setClassificationName(String name) {
    	innerClassificationEntity().setClassificationName(name);
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public Date getCreateDate(){
    	return innerClassificationEntity().getCreateDate();
    }
    
    public void setCreateDate(){
    	if(getCreateDate() == null){
    		innerClassificationEntity().setCreateDate(new Date());
    	}
    }
    
    public String getNote(){
    	return innerClassificationEntity().getNote(); 
    }
    
    public void setNote(String note){
    	innerClassificationEntity().setNote(note);
    }
}
