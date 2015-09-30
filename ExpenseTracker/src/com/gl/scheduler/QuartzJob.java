package com.gl.scheduler;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;



import com.gl.expensetracker.connection.DatabaseUtils;
import com.mysql.jdbc.ResultSet;

public class QuartzJob implements Job  {
	
        public void execute(JobExecutionContext context) throws JobExecutionException  {
        	
        	SendMail send =new SendMail();
        	Calendar mCalendar  = Calendar.getInstance();
        	String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        	String sub = "Monthly Expense Details for the month of "+month;
        	Connection con = null;
    		PreparedStatement prepareStmt = null;
    		ArrayList<String> email=new ArrayList<String>();
    		ResultSet rs=null;
    		ResultSet rs1=null;
    		ResultSet rs2=null;
    		ResultSet rs3=null;
    		int userid =0;
    		float sum =0;
    		float Total =0;
    		int count=0;
    		int groupid=0;
    		String groupname=null;
    		float AverageAmount=0;
    		HashMap<Integer,Float> hm=new HashMap<Integer,Float>();
    		HashMap<Integer,String> hm2=new HashMap<Integer,String>();
    		HashMap<Integer,String> hm3=new HashMap<Integer,String>();
    		try {
    		con = DatabaseUtils.getInstance().getConnection();
    	
    		prepareStmt = con.prepareStatement("select userid,username,emailid from userdetails; ");
    		rs3 = (ResultSet) prepareStmt.executeQuery();
    		while(rs3.next()){	
    			hm2.put(rs3.getInt(1), rs3.getString(2));
    			hm3.put(rs3.getInt(1), rs3.getString(3));
    			
    		}
    		rs3.close();
    		
    		
    		prepareStmt = con.prepareStatement("select grpid , grpname from groupdetails ");
    	
    		rs = (ResultSet) prepareStmt.executeQuery();
    		while(rs.next()){
    			String mesg = " ";
    			count=0;
    			Total=0;
    			AverageAmount=0;
    			groupid=rs.getInt(1);
    			groupname=rs.getString(2);
    			
    			mesg +="Monthly Expense for the Group Members for the GROUP::  "+groupname+"<br>"+"<br>";
    			prepareStmt = con.prepareStatement("select userid  from usrgrpmap where grpid='"+groupid+"'");
    			rs1 = (ResultSet) prepareStmt.executeQuery();
    			while(rs1.next()){
    				sum=0;
    				count=count+1;
    				userid=rs1.getInt(1);
    				
    				prepareStmt = con.prepareStatement("select amount , expensename ,createddate ,expenseid from expensedetails where grpid='"+groupid+"' and isprocessed='0' and userid='"+userid+"'");
    				rs2=(ResultSet) prepareStmt.executeQuery();
    				while(rs2.next()){
    					String expenseid =rs2.getString(4);
    					prepareStmt = con.prepareStatement("update expensedetails set isprocessed='1' where expenseid='"+expenseid+"'");
    					prepareStmt.executeUpdate();  
    	    			mesg += hm2.get(userid)+"  Brought  "+rs2.getString(2)+" FOR AMOUNT  "+rs2.getInt(1)+" DATED "+rs2.getString(3)+"<br>";
    					sum=sum+rs2.getInt(1);
    					
    				}
    				
    				hm.put(userid,sum); 
    				Total=Total+sum;
    			}	rs2.close();
    			
    			try{
    				AverageAmount= Total/count;
    			}catch(ArithmeticException e){
    				
    				mesg+="No member mapped to group "+groupname+"<br>";
    				
    			}  rs1.close();
    		   
    			
    			mesg+="<br>"+"Total amount spent for this Month = "+Total+"RS"+"<br>";
    			
    			mesg+="<br>"+"Amount Per Member  Is =  "+AverageAmount+"RS"+"<br>";
    			
    		    for(Map.Entry<Integer,Float> m:hm.entrySet()){  
				     
    		    	if((AverageAmount- m.getValue())<0){
    		    		
    		    		
    		    		email.add(hm3.get(m.getKey()));
    		    		mesg+="<br>"+hm2.get(m.getKey())+" ::YOU HAVE TO GET RS "+( m.getValue()-AverageAmount)+"<br>";
    		    		
    		    		
    		    	  }else {
				       
				       
				       email.add(hm3.get(m.getKey()));
    		    	   mesg+="<br>"+hm2.get(m.getKey())+" ::YOU HAVE TO PAY RS "+(AverageAmount- m.getValue())+"<br>";
    		    	
    		    	  }
    		    	
    		    	} 
    		     Iterator<String> itr=email.iterator();
    		     while(itr.hasNext()){  
    		     send.sendMessageToMail(itr.next(), sub, mesg);
    		     }
    		    	hm.clear();
    		    	email.clear();
    		    }rs.close();
    		}catch (SQLException e) {
    			System.out.println(e.getMessage());
        	    System.out.println("Java web application + Quartz 2.2.1");
        	    
    		}
        }
		
}