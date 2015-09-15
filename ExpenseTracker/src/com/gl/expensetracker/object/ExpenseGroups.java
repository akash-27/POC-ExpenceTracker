package com.gl.expensetracker.object;

import java.util.Date;

public class ExpenseGroups {
	
	private int grpId;
	private String grpName;
	private Date createdDate;
	private int number, createdBy;
	
	public int getGrpId() {
		return grpId;
	}
	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "ExpenseGroups [grpId=" + grpId + ", grpName=" + grpName
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ "]";
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}	
}
