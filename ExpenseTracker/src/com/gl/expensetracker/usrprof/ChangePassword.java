package com.gl.expensetracker.usrprof;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expencetracker.ExpenseUser;
import com.gl.expenetracker.service.managegroup.ManageGroupService;
import com.gl.expensetracker.connection.DatabaseUtils;
import com.gl.expensetracker.service.manageprofile.ManageProfileService;

import java.sql.*;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/jsp/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
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
		ManageProfileService manageprof = new ManageProfileService();
		manageprof.ChangePassword(request, response);
	}

}


