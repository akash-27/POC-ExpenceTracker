package com.gl.expencetracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ExpenseEntity {
	
	private String expenseName;
	private int grpId, expenseId, userId;
	private float amount;
	private Date createdDate;
	
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	
	
	public void createExpenseEntityfromRequest(HttpServletRequest request) 
	{
		this.expenseId = Integer.parseInt(request.getParameter("expenseid"));
		this.expenseName = request.getParameter("expensename");
		this.grpId = Integer.parseInt(request.getParameter("grpid"));
		this.userId = Integer.parseInt(request.getParameter("usrid"));
		this.amount = Float.parseFloat(request.getParameter("mobno"));
		try {
			this.createdDate = new SimpleDateFormat("mm/dd/yyyy").parse((request.getParameter("createdate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
