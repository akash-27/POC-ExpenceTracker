package com.gl.expenetracker.service.managegroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expencetracker.ExpenseGroups;
import com.gl.expencetracker.ExpenseUser;
import com.gl.expencetracker.LoginServlet;
import com.gl.expensetracker.connection.DatabaseUtils;

public class ManageGroupService {

	public void insertGroupDetails(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		String grpname = request.getParameter("grp");
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null; 
		PreparedStatement prepareStmt1 = null;
		ExpenseUser user;
		int grpid = 0;
		ArrayList<ExpenseGroups> grpList;
		LoginServlet logser = new LoginServlet();
		String insertsql = "insert into groupdetails (grpname,createdby,createddate) values (?,?,?)";
		String grpmapsql = "insert into usrgrpmap (grpid,userid) values(?,?)";

		try {
			HttpSession session = request.getSession(true);
			user = (ExpenseUser) session.getAttribute("user");

			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(insertsql);
			java.util.Date date = new Date();
			Object param = new java.sql.Timestamp(date.getTime());
			prepareStmt.setObject(3, param); 
			prepareStmt.setString(1, grpname);
			prepareStmt.setInt(2, user.getUserId());
			// execute update SQL stetement
			prepareStmt.executeUpdate();

			grpList = logser.getGroupListfromDB(dbConnection, user.getUserId());
			for (ExpenseGroups grp: grpList) {
				if(grp.getGrpName().equals(grpname)){
					grpid = grp.getGrpId();
				}
			}
			prepareStmt1 = dbConnection.prepareStatement(grpmapsql);
			prepareStmt1.setInt(1, grpid);
			prepareStmt1.setInt(2, user.getUserId());
			prepareStmt1.executeUpdate();

			session.setAttribute("grpList", grpList);
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
			if (prepareStmt1 != null) {
				try {
					prepareStmt1.close();
					if(dbConnection != null)
						dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}


	@SuppressWarnings("unchecked")
	public void EditGroupDetails(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String selectedItem = request.getParameter("grpname");
		String newgrpname = request.getParameter("newgrpname");
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		ArrayList<ExpenseGroups> grpList;
		ArrayList<ExpenseGroups> grpList1 = new ArrayList<ExpenseGroups>();
		String updatesql = "update groupdetails set grpname=?"
				+ " where grpid=?";

		try {
			HttpSession session = request.getSession(true);
			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(updatesql);
			prepareStmt.setString(1, newgrpname);
			int grpid = Integer.parseInt(selectedItem);
			prepareStmt.setInt(2, grpid);
			// execute update SQL stetement
			prepareStmt.executeUpdate();
			grpList = (ArrayList<ExpenseGroups>)session.getAttribute("grpList");
			for (ExpenseGroups grp: grpList) {
				ExpenseGroups grpusr = new ExpenseGroups();
				if(grp.getGrpId() == grpid){
					grpusr.setGrpName(newgrpname);
				}
				else{
					grpusr.setGrpName(grp.getGrpName());
				}
				grpusr.setCreatedBy(grp.getCreatedBy());
				grpusr.setCreatedDate(grp.getCreatedDate());
				grpusr.setGrpId(grp.getGrpId());
				grpList1.add(grpusr);
			} 
			session.setAttribute("grpList", grpList1);
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

}



