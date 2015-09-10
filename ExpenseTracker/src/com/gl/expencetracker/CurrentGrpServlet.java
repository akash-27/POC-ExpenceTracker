package com.gl.expencetracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expensetracker.authentication.LoginServlet;
import com.gl.expensetracker.connection.DatabaseUtils;
import com.gl.expensetracker.object.ExpenseEntity;
import com.gl.expensetracker.object.ExpenseGroups;

/**
 * Servlet implementation class CurrentGrpServlet
 */
@WebServlet("/jsp/CurrentGrpServlet")
public class CurrentGrpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrentGrpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		String grpName = request.getParameter("grpname");
		int grpId = Integer.parseInt(grpName);
		ExpenseGroups curGroup = null;
		Connection dbConnection = null;
		
		ArrayList<ExpenseEntity> expenseList = null;
		

		dbConnection = DatabaseUtils.getInstance().getConnection();
		try {
			curGroup = LoginServlet.getGroupfromDB(dbConnection, grpId);

			session.setAttribute("curgroup", curGroup);
			expenseList = getGroupListfromDB(dbConnection, curGroup.getGrpId()); 
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		session.setAttribute("expenselist", expenseList);
		response.sendRedirect("ExpenseEntity.jsp");	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private ArrayList<ExpenseEntity> getGroupListfromDB(Connection dbConnection, int grpId) throws SQLException {
		
		ArrayList<ExpenseEntity> expenseList = new ArrayList<>();
		String searchQuery ="select * from expensedetails where grpid=?";
    	PreparedStatement prepareStmt = null;
    	prepareStmt = dbConnection.prepareStatement(searchQuery);
		prepareStmt.setInt(1, grpId);
    	ResultSet rs = prepareStmt.executeQuery();
    	
		while( rs.next()) {
			
			ExpenseEntity expenseEntity = new ExpenseEntity();
			expenseEntity.setExpenseId(rs.getInt("expenseid"));
			expenseEntity.setExpenseName(rs.getString("expensename"));
			expenseEntity.setGrpId(rs.getInt("grpid"));
			expenseEntity.setUserId(rs.getInt("userid"));
			expenseEntity.setAmount(rs.getFloat("amount"));
			
			// TODO date handling			
			//expenseEntity.setCreatedDate(rs.getDate("createddate"));
			
			expenseList.add(expenseEntity);			
		}		
		return expenseList;
	}
}
