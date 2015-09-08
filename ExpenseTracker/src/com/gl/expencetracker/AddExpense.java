package com.gl.expencetracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gl.expensetracker.connection.DatabaseUtils;

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

		response.getWriter().append("Adding Expense");
		ExpenseEntity expenseEntity = new ExpenseEntity();
		expenseEntity.createExpenseEntityfromRequest(request);
		try {
			insertExpenseEntityIntoDB(expenseEntity);
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
	
	private static void insertExpenseEntityIntoDB(ExpenseEntity expenseEntity) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		String insertTableSQL = "INSERT INTO expensedetails "+
				"(expensename,grpid,userid,amount,createddate) VALUES "
				+ "(?,?,?,?,?)";
		

		try {
			System.out.println("Inserting");
			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(insertTableSQL);

			prepareStmt.setString(1, expenseEntity.getExpenseName());
			prepareStmt.setInt(2, expenseEntity.getGrpId());
			prepareStmt.setInt(3, expenseEntity.getUserId());
			prepareStmt.setInt(4, (int)expenseEntity.getAmount());
			//Need to change date handling
			prepareStmt.setDate(5, java.sql.Date.valueOf("2000-11-01"));
			
			// execute insert SQL stetement
			prepareStmt.executeUpdate();

			System.out.println("Record is inserted into user table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (prepareStmt != null) {
				prepareStmt.close();
				dbConnection.close();
			}
		}
	}

}
