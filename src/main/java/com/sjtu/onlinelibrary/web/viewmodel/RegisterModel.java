package com.sjtu.onlinelibrary.web.viewmodel;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.sjtu.onlinelibrary.entity.User;
import com.sjtu.onlinelibrary.util.LangUtil;

public class RegisterModel {

	private User userEntity;

    public RegisterModel() {
        this( new User());
    }

    public RegisterModel( final User user) {
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

    @Pattern(regexp = "^[a-zA-Z_]\\w{4,19}$", message = "用户名必须以字母下划线开头，由字母数字下划线组成，长度5～20位")
    public String getUsername() {
        return innerUserEntity().getUsername();
    }

    public void setUsername(String name) {
    	innerUserEntity().setUsername(name);
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
    
    public String getCreateDate(){
   	 if (innerUserEntity().getCreateDate() == null) {
            return "";
        }
        return LangUtil.getDefaultTimeFormat().format(innerUserEntity().getCreateDate());
   }
   
   public void setCreateDate(String createDate){
   	 try {
            if (LangUtil.isNullOrEmpty(createDate)) return;
            innerUserEntity().setCreateDate(LangUtil.getDefaultTimeFormat().parse(createDate));

        } catch (Exception e) {
            return;
        }
   }
   
}
