package com.gl.expencetracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gl.expensetracker.connection.DatabaseUtils;

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

    	response.getWriter().append("Welcome to registration");
    	ExpenseUser newUser = new ExpenseUser();
    	newUser.createUserfromRequest(request);
    	try {
			insertRecordIntoTable(newUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
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
				"(username,emailid,phone,password) VALUES "
				+ "(?,?,?,?)";
		DatabaseUtils db = new DatabaseUtils();

		try {
			
			dbConnection = db.getConnection();
			prepareStmt = dbConnection.prepareStatement(insertTableSQL);

			prepareStmt.setString(1, curUser.getUserName());
			prepareStmt.setString(2, curUser.geteMail());
			prepareStmt.setString(3, curUser.getMobNumber());
			prepareStmt.setString(4, curUser.getPassWord());

			// execute insert SQL stetement
			prepareStmt.executeUpdate();

			System.out.println("Record is inserted into user table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (prepareStmt != null) {
				prepareStmt.close();
			}
			db.close();
		}
	}

}
