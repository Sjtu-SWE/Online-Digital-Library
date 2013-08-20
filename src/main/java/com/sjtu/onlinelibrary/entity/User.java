package com.sjtu.onlinelibrary.entity;

import com.google.code.morphia.annotations.Entity;
import com.sjtu.onlinelibrary.BasePersistable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-28
 * Time: 下午3:52
 */
@Entity
public class User  extends BasePersistable implements UserDetails {
    private String username;
    private String realName;
    private String phone;
    private String email;
    private String password;
    private int credits;
    private Date lastLogonTime;
    private Date createDate;
    private String note;
    private int role;
    private String roleName;
       
   private List<GrantedAuthority> authorities;
   private boolean accountNonExpired;       
   private boolean accountNonLocked;      
   private boolean credentialsNonExpired;      
   private boolean enabled;
    
   public User(){}
   
    public User(String username, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, 
    		boolean enabled, List<GrantedAuthority> authorities) {
    	if (username == null || "".equals(username) || password == null)  
    		throw new IllegalArgumentException("Cannot pass null or empty values to constructor");  
    	this.username = username;
    	this.password = password;
    	this.accountNonExpired = accountNonExpired;
    	this.accountNonLocked = accountNonLocked;
    	this.credentialsNonExpired = credentialsNonExpired;
    	this.enabled = enabled;
    	this.authorities = authorities;
    }

    public void setUsername(String username) {
       this.username = username;
    }
    
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Date getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(Date lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
    
}
