package com.gl.expensetracker.managegroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expencetracker.ExpenseGroups;
import com.gl.expencetracker.ExpenseUser;
import com.gl.expencetracker.LoginServlet;
import com.gl.expensetracker.connection.DatabaseUtils;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/jsp/CreateGroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String grpname = request.getParameter("grp");
//		String usrname = request.getParameter("usr");
		insertGroupDetails(request, response, grpname);
	}

	private void insertGroupDetails(HttpServletRequest request,
			HttpServletResponse response, String grpname)
			throws ServletException, IOException {
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		RequestDispatcher requestDispatcher; 
		ExpenseUser user;
		ArrayList<ExpenseGroups> grpList;
		LoginServlet logser = new LoginServlet();
		String insertsql = "insert into groupdetails (grpname,createdby,createddate) values (?,?,?)";
		
		try {
			HttpSession session = request.getSession(true);
			user = (ExpenseUser) session.getAttribute("user");
			
			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(insertsql);
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			prepareStmt.setTimestamp(3, date);
			prepareStmt.setString(1, grpname);
			prepareStmt.setInt(2, user.getUserId());
			// execute update SQL stetement
			prepareStmt.executeUpdate();

			grpList = logser.getGroupListfromDB(dbConnection, user.getUserId());   
			session.setAttribute("grpList", grpList);
			requestDispatcher = request.getRequestDispatcher("welcome.jsp");
			requestDispatcher.forward(request, response);


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.sendRedirect("Error.jsp");
		} finally {

			if (prepareStmt != null) {
				try {
					prepareStmt.close();
					if(dbConnection != null)
						dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}


