package com.gl.expensetracker.usrprof;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expencetracker.ExpenseUser;
import com.gl.expensetracker.connection.DatabaseUtils;

/**
 * Servlet implementation class EditProf
 */
@WebServlet("/jsp/EditProf")
public class EditProf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProf() {
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
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		//extract userid value from session
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		ExpenseUser user = new ExpenseUser();
		String updatesql = "update userdetails set phone=?, emailid=?, address=? "
				+ " where userid=?";

		try {
			HttpSession session = request.getSession(true);
			DatabaseUtils db = new  DatabaseUtils();
			dbConnection = db.getConnection();
			prepareStmt = dbConnection.prepareStatement(updatesql);
			ExpenseUser curruser = new ExpenseUser();
			curruser = (ExpenseUser) session.getAttribute("user");
			prepareStmt.setString(1, phone);
			prepareStmt.setString(3, address);
			prepareStmt.setString(2, email);
			prepareStmt.setInt(4, 1);
			// execute update SQL stetement
			prepareStmt.executeUpdate();
			user.setCreatedOn(curruser.getCreatedOn());
			user.setPassWord(curruser.getPassWord());
			user.setUserId(curruser.getUserId());
			user.setUserName(curruser.getUserName());
			user.setAddress(address);
			user.seteMail(email);
			user.setMobNumber(phone);
			session.setAttribute("user", user);
			RequestDispatcher requestDispatcher; 
			requestDispatcher = request.getRequestDispatcher("welcome.jsp");
			requestDispatcher.forward(request, response);

		} catch (SQLException e) {
			response.sendRedirect("Error.jsp");
			System.out.println(e.getMessage());

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


