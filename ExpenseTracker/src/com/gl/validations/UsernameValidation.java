package com.gl.validations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gl.expensetracker.connection.DatabaseUtils;
import com.mysql.jdbc.ResultSet;

@WebServlet("/UsernameValidation")
public class UsernameValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		ResultSet rs=null;
		String Name =null;
		PrintWriter out=response.getWriter();
		Name =request.getParameter("username");
		try {

			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement("select username from userdetails ");
			rs = (ResultSet) (prepareStmt.executeQuery());

			while(rs.next()){

				if(rs.getString(1).equals(Name)){
					out.append("true");
					break;
				}	
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}		
	}
}
