package com.gl.mailapi;

import com.gl.scheduler.SendMail;

public class MailAPI {
	
	public static SendMail mailer ;
	public  void VerificationMail(String toId)
	{
		String sub = "Account Verification";
		String msg = "Hi,\n You have been registred at Expence Tracker. Please verify your account at below link."
				+ "\nThis is an automated mail please do not reply to this."
				+ "\n Thanks ";
		
		mailer.sendMessageToMail(toId, sub, msg);
	}
	public static void RegisterMail(String toId)
	{
		SendMail mailer1 = new SendMail();
		String sub = "Account Registration";
		String msg = "Hi";
		msg += "<br>"+" You have been successfully registered at Expense Tracker.";
		msg += "<br>"+"This is an automated mail please do not reply to this.";
		msg += "<br>"+"Thanks ";
		
		mailer1.sendMessageToMail(toId, sub, msg);		
	}
	public static void PasswordChange(String toId)
	{
		SendMail mailer1 = new SendMail();
		String sub = "Password change notification";
		String msg = "Hi,"+"<br>"+"Your password has been successfully changed. If you have not"
				+ " changed your password, please visit our site for further details."
				+ "<br>"+"This is an automated mail please do not reply to this."
				+ "<br>"+"Thanks ";
		
		mailer1.sendMessageToMail(toId, sub, msg);		
	}
	public static void AddMemberNotify(String toId)
	{
		String sub = "AddMember Notification";
		String msg = "Hi,"+"<br>"+"Someone has added you as a member in his group. Log in to our"
				+ " website for more details."
				+ "<br>"+"This is an automated mail please do not reply to this."
				+ "<br>"+"Thanks ";
		
		mailer.sendMessageToMail(toId, sub, msg);		
	}
	public static void CreateMemberNotify(String toId)
	{
		String sub = "Group Created";
		String msg = "Hi, \n You have successfully created a group in your Expense Tracker account"
				+ "\nThis is an automated mail please do not reply to this."
						+ "\n Thanks ";
		
		mailer.sendMessageToMail(toId, sub, msg);		
	}

}
