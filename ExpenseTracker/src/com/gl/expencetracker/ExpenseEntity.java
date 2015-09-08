package com.gl.expencetracker;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ExpenseEntity {
	
	private String expenseName;
	private int grpId, expenseId, userId;
	private float amount;
	private String createdDate;
	
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public int getGrpId() {
		return grpId;
	}
	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}
	public int getExpendeId() {
		return expenseId;
	}
	public void setExpendeId(int expendeId) {
		this.expenseId = expendeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}	
	
	public void createExpenseEntityfromRequest(HttpServletRequest request) 
	{
		this.expenseName = request.getParameter("expensename");
		this.grpId = Integer.parseInt(request.getParameter("grpid"));
		this.userId = Integer.parseInt(request.getParameter("usrid"));
		this.amount = Float.parseFloat(request.getParameter("amount"));
		this.createdDate = new SimpleDateFormat("mm/dd/yyyy").format(new Date());
	}
}
