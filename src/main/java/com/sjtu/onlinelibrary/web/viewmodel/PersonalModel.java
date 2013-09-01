package com.sjtu.onlinelibrary.web.viewmodel;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.sjtu.onlinelibrary.entity.User;

public class PersonalModel {

	private String editType;
	private User userEntity;

    public PersonalModel() {
        this( new User());
    }

    public PersonalModel( final User user) {
        this.userEntity = user;
    }

    public User innerUserEntity() {
        return userEntity;
    }
    
    public String getId() {
        return innerUserEntity().getId();
    }

    public String getUsername() {
        return innerUserEntity().getUsername();
    }

    public void setUsername(String name) {
    	innerUserEntity().setUsername(name);
    }
    
    public void setId(String id) {
    	innerUserEntity().setId(id);
    }

    @Pattern(regexp="^[\u4e00-\u9fa5]+$",message="真实名必须是中文。")
    public String getRealName() {
        return innerUserEntity().getRealName();
    }

    public void setRealName(String name) {
    	innerUserEntity().setRealName(name);
    }
    
    @Pattern(regexp = "^[a-zA-Z]\\w{5,7}$",message = "密码必须以字母开头，字符、数字、下划线组成，长度6~8位")
    public String getPassword() {
        return innerUserEntity().getPassword();
    }

    public void setPassword(String name) {
    	innerUserEntity().setPassword(name);
    }
    
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "请输入正确的邮箱格式，例如xxxx@sina.com")
    public String getEmail() {
        return innerUserEntity().getEmail();
    }

    public void setEmail(String email) {
    	innerUserEntity().setEmail(email);
    }
    
    @Pattern(regexp="^(13+[0-9]{9})|(15+[0-9]{9})|(18+[0-9]{9})$",message="请输入正确格式的11位手机号码。")
    public String getPhone() {
        return innerUserEntity().getPhone();
    }

    public void setPhone(String phone) {
    	innerUserEntity().setPhone(phone);
    }
    
    public String getNote(){
    	return innerUserEntity().getNote(); 
    }
    
    public void setNote(String note){
    	innerUserEntity().setNote(note);
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }
}
