package com.gl.expensetracker.object;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ExpenseUser {
	private String userName, passWord, eMail;
	private String mobNumber;
	private int userId;
	private String createdOn;
	private String address;
	private boolean isValidated;
	
	public boolean compareUser(String name, String pswd)
	{
		if((this.userName.contains(name)) && (this.passWord.contains(pswd)))
		{
			return true;
		}
		return false;		
	}

	@Override
	public String toString() {
		return "ExpenseUser [userName=" + userName + ", passWord=" + passWord
				+ ", eMail=" + eMail + ", mobNumber=" + mobNumber
				+ ", createdOn=" + createdOn + ", userId=" + userId + "]";
	}	
	
	public void createUserfromRequest(HttpServletRequest request) 
	{
		this.userName = request.getParameter("user");
		this.passWord = request.getParameter("password");
		this.eMail = request.getParameter("email");
		this.mobNumber = request.getParameter("mobno");
		this.createdOn = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		this.isValidated = false;
		this.address = " ";
	}
	
	public void extractUserIdfromRequest(HttpServletRequest request)
	{
		this.userName = request.getParameter("user");
		this.passWord = request.getParameter("password");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getIsValidated() {
		return isValidated;
	}

	public void setIsValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}
}
