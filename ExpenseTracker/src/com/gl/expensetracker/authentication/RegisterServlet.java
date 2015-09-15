package com.gl.expensetracker.authentication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expensetracker.connection.DatabaseUtils;
import com.gl.expensetracker.object.ExpenseGroups;
import com.gl.expensetracker.object.ExpenseUser;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/jsp/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	ExpenseUser newUser = new ExpenseUser();
    	newUser.createUserfromRequest(request);
    	int usrid;
    	ArrayList<ExpenseGroups> grpList = null;
    	HttpSession session = request.getSession(true);
    	try {
			insertRecordIntoTable(newUser);
			usrid = getUserIdfromDB(newUser.getUserName());
			newUser.setUserId(usrid);
			session.setAttribute("user", newUser);
			session.setAttribute("grpList", grpList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block			
			response.sendRedirect("LoginFailure.jsp");
		}    	
    	response.sendRedirect("welcome.jsp");
    }

	private int getUserIdfromDB(String userName) throws SQLException {

		int usrid = 0;
		
		Connection dbConnection = DatabaseUtils.getInstance().getConnection();
		PreparedStatement prepareStmt = null;
		String searchQuery = "select userid from userdetails where username=?";
		
		prepareStmt = dbConnection.prepareStatement(searchQuery);

		prepareStmt.setString(1, userName);
		ResultSet rs = prepareStmt.executeQuery();
		while (rs.next()){
			usrid = rs.getInt("userid");
		}
		return usrid;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);	
	}
	
	private static void insertRecordIntoTable(ExpenseUser curUser) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		String insertTableSQL = "INSERT INTO userdetails "+
				"(username,emailid,phone,password,createddate) VALUES "
				+ "(?,?,?,?,?)";

		try {
			
			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(insertTableSQL);

			prepareStmt.setString(1, curUser.getUserName());
			prepareStmt.setString(2, curUser.geteMail());
			prepareStmt.setString(3, curUser.getMobNumber());
			prepareStmt.setString(4, curUser.getPassWord());
			java.util.Date date = new Date();
			Object param = new java.sql.Timestamp(date.getTime());
			prepareStmt.setObject(5, param);
			
			prepareStmt.executeUpdate();
			System.out.println("Record is inserted into user table!");			

		} 
		finally {

			if (prepareStmt != null) {
				prepareStmt.close();
			}
			dbConnection.close();
			
		}
	}

}
