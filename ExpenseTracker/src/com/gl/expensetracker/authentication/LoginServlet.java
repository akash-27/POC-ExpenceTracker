package com.gl.expensetracker.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expensetracker.connection.DatabaseUtils;
import com.gl.expensetracker.object.ExpenseGroups;
import com.gl.expensetracker.object.ExpenseUser;

import java.sql.*;
import java.util.ArrayList;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/jsp/LoginServlet")
public class LoginServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	ExpenseUser user = new ExpenseUser();
    	user.extractUserIdfromRequest(request);
    	ArrayList<ExpenseGroups> grpList;
    	
    	Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		String searchQuery ="select * from userdetails where username=?AND password=?";
		
		try {
			dbConnection =DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(searchQuery);

			prepareStmt.setString(1, user.getUserName());
			prepareStmt.setString(2, user.getPassWord());
			
			ResultSet rs = prepareStmt.executeQuery();
    		boolean isEmpty = rs.next();
    		if (!isEmpty) {
    			response.sendRedirect("LoginFailure.jsp");
    		}
    		else if (isEmpty) {
    			// fetch the session from request, create new session if session
    			// is not present in the request
    			HttpSession session = request.getSession(true);
    			
    			user.seteMail(rs.getString("emailid"));
    			user.setMobNumber(rs.getString("phone"));
    			user.setUserId(rs.getInt("userid"));
    			user.setAddress(rs.getString("address"));
    			user.setIsValidated(rs.getBoolean("isvalidated"));
//    			user.setCreatedOn(rs.getDate("createdon"));
    			
    			//setting user-details in session
    			session.setAttribute("user", user);
    			
    			grpList = getGroupListfromDB(dbConnection, user.getUserId());   
    			session.setAttribute("grpList", grpList);
    			
    			//redirecting to home page
    			response.sendRedirect("welcome.jsp");    			
    		}
    	}
    	catch (SQLException e) {
    		System.out.println("SQLException occured: " + e.getMessage());
    		e.printStackTrace();
    	}
    	finally {

    		if (prepareStmt != null) {
    			try {
    				prepareStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}

    		if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
    	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    
    public ArrayList<ExpenseGroups> getGroupListfromDB(Connection con, int userId) throws SQLException
    {
    	ArrayList<ExpenseGroups> grpList = new ArrayList<>();
    	
    //	String searchQuery ="select grpid from usrgrpmap where userid=?";
    	String searchQuery ="select * from groupdetails where createdby=?";
    	PreparedStatement prepareStmt = null;
    	prepareStmt = con.prepareStatement(searchQuery);

		prepareStmt.setInt(1, userId);
    	ResultSet rs = prepareStmt.executeQuery();
		while( rs.next()) {
			ExpenseGroups grpDetail ;
			int grpId = rs.getInt("grpid");
			grpDetail = getGroupfromDB(con,grpId);			
			grpList.add(grpDetail);
		}    	
    	return grpList;    	
    }
    
    public static ExpenseGroups getGroupfromDB(Connection con, int grpId) throws SQLException 
    {
    	ExpenseGroups grpDetail = new ExpenseGroups();
    	String searchQuery ="select * from groupdetails where grpid=?";
    	PreparedStatement prepareStmt = null;
    	prepareStmt = con.prepareStatement(searchQuery);
		prepareStmt.setInt(1, grpId);
    	ResultSet rs = prepareStmt.executeQuery();
		while( rs.next()) {
		
			grpDetail.setGrpId(rs.getInt("grpid"));
			grpDetail.setGrpName(rs.getString("grpname"));
			grpDetail.setCreatedBy(rs.getString("createdby"));
			grpDetail.setNumber(getCountofGroup(con,grpDetail.getGrpId()));
			
			//TODO : handling of created date
//			try {
//				grpDetail.setCreatedDate(new SimpleDateFormat().parse(rs.getString("createddate")));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
    	return grpDetail;
    }
    public static int getCountofGroup(Connection con, int grpId) throws SQLException 
    {
    	String countQuery = "select count(*) AS total from usrgrpmap where grpid=?";
    	PreparedStatement prepareStmt = null;
    	prepareStmt = con.prepareStatement(countQuery);
    	prepareStmt.setInt(1, grpId);
    	ResultSet rs = prepareStmt.executeQuery(); 
    	if(rs.next()){
    		return rs.getInt("total");
        }  
		return 0;   
    }
}
