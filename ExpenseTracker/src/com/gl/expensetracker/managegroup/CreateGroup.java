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
import com.gl.expenetracker.service.managegroup.ManageGroupService;
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
		ManageGroupService managegrp = new ManageGroupService();
		managegrp.insertGroupDetails(request, response);
	}

	
}


