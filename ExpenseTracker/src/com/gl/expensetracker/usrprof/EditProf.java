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
		String updatesql = "update userdetails set phone=?, emailid=?, address=? "
				+ " where userid=?";



		try {
			HttpSession session = request.getSession(true);
			DatabaseUtils db = new  DatabaseUtils();
			dbConnection = db.getConnection();
			prepareStmt = dbConnection.prepareStatement(updatesql);
			ExpenseUser curruser = new ExpenseUser();
			curruser = (ExpenseUser) session.getAttribute("user");
			if(!(phone.equals(curruser.getMobNumber()))){
				prepareStmt.setString(1, phone);
			}
			else{
				prepareStmt.setString(1, curruser.getMobNumber());
			}
			if(!(address.equals(curruser.getAddress()))){
				prepareStmt.setString(3, address);
			}
			else{
				prepareStmt.setString(3, curruser.getAddress());
			}
			if(!(email.equals(curruser.geteMail()))){
				prepareStmt.setString(2, email);
			}
			else{
				prepareStmt.setString(2, curruser.geteMail());
			}
			prepareStmt.setInt(4, 1);
			// execute update SQL stetement
			prepareStmt.executeUpdate();
			request.setAttribute("msg", "Profile Successfully update");
			RequestDispatcher requestDispatcher; 
			requestDispatcher = request.getRequestDispatcher("/Welcome.jsp");
			requestDispatcher.forward(request, response);

		} catch (SQLException e) {
			request.setAttribute("msg", "Error occured while updating profile");
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


