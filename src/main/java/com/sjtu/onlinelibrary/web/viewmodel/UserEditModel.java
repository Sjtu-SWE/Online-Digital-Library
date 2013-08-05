package com.sjtu.onlinelibrary.web.viewmodel;

import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.util.LangUtil;

public class UserEditModel {

	private String editType;
    private User userEntity;

    public UserEditModel() {
        this("添加用户", new User());
    }

    public UserEditModel(final String editType, final User user) {
        setEditType(editType);
        this.userEntity = user;
    }

    public User innerUserEntity() {
        return userEntity;
    }

    public String getId() {
        return innerUserEntity().getId();
    }

    public void setId(String id) {
    	innerUserEntity().setId(id);
    }

    @NotEmpty(message = "用户名不能为空。")
    public String getUserName() {
        return innerUserEntity().getUserName();
    }

    public void setUserName(String name) {
    	innerUserEntity().setUserName(name);
    }

    @NotEmpty(message = "真实名不能为空。")
    public String getRealName() {
        return innerUserEntity().getRealName();
    }

    public void setRealName(String name) {
    	innerUserEntity().setRealName(name);
    }
    
    @NotEmpty(message = "密码不能为空。")
    public String getPassword() {
        return innerUserEntity().getPassword();
    }

    public void setPassword(String name) {
    	innerUserEntity().setPassword(name);
    }
    
    @NotEmpty(message = "手机号码不能为空。")
    public String getPhone() {
        return innerUserEntity().getPhone();
    }

    public void setPhone(String phone) {
    	innerUserEntity().setPhone(phone);
    }
    
    @NotEmpty(message = "邮箱不能为空。")
    public String getEmail() {
        return innerUserEntity().getEmail();
    }

    public void setEmail(String email) {
    	innerUserEntity().setEmail(email);
    }
    
    public int getCredits() {
        return innerUserEntity().getCredits();
    }

    public void setCredits(int credits) {
    	innerUserEntity().setCredits(credits);
    }
    
    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public String getCreateDate(){
    	 if (innerUserEntity().getCreateDate() == null) {
             return "";
         }
    	return LangUtil.getDefaultDateFormat().format(innerUserEntity().getCreateDate());
    }
    
    public void setCreateDate(String createDate){
    	try {
            if (LangUtil.isNullOrEmpty(createDate)) return;
            innerUserEntity().setCreateDate(LangUtil.getDefaultDateFormat().parse(createDate));
        } catch (Exception e) {
            return;
        }
    }
    
    public Date getLastLogonTime(){
    	return innerUserEntity().getLastLogonTime();
    }
    
    public void setLastLogonTime(Date lastLogonTime){
    	innerUserEntity().setLastLogonTime(lastLogonTime);
    }
    
    public String getRoleName(){
    	return innerUserEntity().getRoleName(); 
    }
    
    public void setRoleName(String roleName){
    	innerUserEntity().setRoleName(roleName);
    }
    
    public String getNote(){
    	return innerUserEntity().getNote(); 
    }
    
    public void setNote(String note){
    	innerUserEntity().setNote(note);
    }

}
