package com.gl.expensetracker.service.manageprofile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expensetracker.object.ExpenseUser;
import com.gl.expensetracker.connection.DatabaseUtils;

public class ManageProfileService {

	public void ChangePassword(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String newpass = request.getParameter("newpass");


		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		ExpenseUser user = new ExpenseUser();
		String updatesql = "update userdetails set password=? "
				+ " where userid=?";

		try {
			HttpSession session = request.getSession(true);
			user = (ExpenseUser) session.getAttribute("user");

			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(updatesql);
			prepareStmt.setString(1, newpass);
			prepareStmt.setInt(2, user.getUserId());
			// execute update SQL stetement
			prepareStmt.executeUpdate();

			user.setPassWord(newpass);
			session.setAttribute("user", user);
			response.sendRedirect("welcome.jsp");

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
	
	public void EditProfile(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(updatesql);
			ExpenseUser curruser = new ExpenseUser();
			curruser = (ExpenseUser) session.getAttribute("user");
			prepareStmt.setString(1, phone);
			prepareStmt.setString(3, address);
			prepareStmt.setString(2, email);
			prepareStmt.setInt(4, curruser.getUserId());
			// execute update SQL stetement
			prepareStmt.executeUpdate();
			curruser.setAddress(address);
			curruser.seteMail(email);
			curruser.setMobNumber(phone);
			session.setAttribute("user", curruser); 
			response.sendRedirect("welcome.jsp");

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

