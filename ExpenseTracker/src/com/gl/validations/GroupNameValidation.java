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

/**
 * Servlet implementation class GroupNameValidation
 */
@WebServlet("/jsp/GroupNameValidation")
public class GroupNameValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GroupNameValidation() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	Connection dbConnection = null;
    	PreparedStatement prepareStmt = null;
    	ResultSet rs=null;
    	String grpName =null;
    	PrintWriter out=response.getWriter();
    	grpName =request.getParameter("grp");
    	try {

    		dbConnection = DatabaseUtils.getInstance().getConnection();
    		prepareStmt = dbConnection.prepareStatement("select grpname from groupdetails ");
    		rs = (ResultSet) (prepareStmt.executeQuery());

    		while(rs.next()){

    			if(rs.getString("grpname").equals(grpName)){
    				out.append("true");
    				break;
    			}	
    		}
    	} catch (SQLException e) {

    		System.out.println(e.getMessage());

    	}		
    }
}
