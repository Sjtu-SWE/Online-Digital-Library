package com.sjtu.onlinelibrary.web.viewmodel;

public class StatisticsUserModel {

	private String registerDate;
	private String userCount;
	
	public StatisticsUserModel (String registerDate,String userCount){
		this.registerDate = registerDate;
		this.userCount = userCount;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getUserCount() {
		return userCount;
	}
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	
}
