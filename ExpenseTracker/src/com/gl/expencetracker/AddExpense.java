package com.gl.expencetracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expensetracker.connection.DatabaseUtils;
import com.gl.expensetracker.object.ExpenseEntity;
import com.gl.expensetracker.object.ExpenseGroups;
import com.gl.expensetracker.object.ExpenseUser;

/**
 * Servlet implementation class AddExpense
 */
@WebServlet("/jsp/AddExpense")
public class AddExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExpense() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
				
		ExpenseEntity expenseEntity = new ExpenseEntity();
		expenseEntity.createExpenseEntityfromRequest(request);
		try {
			insertExpenseEntityIntoDB(expenseEntity, session);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("ExpenseEntity.jsp");   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
	
	private static void insertExpenseEntityIntoDB(ExpenseEntity expenseEntity, HttpSession session) throws SQLException {

		int rc = 0;
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		ArrayList<ExpenseEntity>  expenseList = null;
		ExpenseGroups curGroup = (ExpenseGroups) session.getAttribute("curgroup"); 
		ExpenseUser curUser = (ExpenseUser) session.getAttribute("user");
		String insertTableSQL = "INSERT INTO expensedetails "+
				"(expensename,grpid,userid,amount,createddate) VALUES "
				+ "(?,?,?,?,?)";

		try {
			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(insertTableSQL);
			prepareStmt.setString(1, expenseEntity.getExpenseName());
			prepareStmt.setInt(2, curGroup.getGrpId());
			prepareStmt.setInt(3, curUser.getUserId());
			prepareStmt.setInt(4, (int)expenseEntity.getAmount());
			//TODO Need to change date handling
			prepareStmt.setDate(5, java.sql.Date.valueOf("2000-11-01"));
			
			// execute insert SQL statement
			rc = prepareStmt.executeUpdate();
			
			if(rc > 0) {
				expenseList = (ArrayList<ExpenseEntity>) session.getAttribute("expenselist");
				expenseList.add(expenseEntity);
			}		

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (prepareStmt != null) {
				prepareStmt.close();
				dbConnection.close();
			}
			dbConnection.close();
		}
	}
}
