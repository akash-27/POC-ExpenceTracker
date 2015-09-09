package com.gl.expensetracker.object;

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
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
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
		this.amount =  Float.parseFloat(request.getParameter("price"));
	}
	@Override
	public String toString() {
		return "ExpenseEntity [expenseName=" + expenseName + " - "
				+ amount ;
	}	
	
}
