package com.gl.expenetracker.service.managegroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gl.expencetracker.UserId;
import com.gl.expensetracker.object.ExpenseGroups;
import com.gl.expensetracker.object.ExpenseUser;
import com.gl.expensetracker.authentication.LoginServlet;
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
			dbConnection.setAutoCommit(false);
			
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
			dbConnection.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				if(dbConnection != null)
					dbConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.sendRedirect("Error.jsp");
		} finally {

			if (prepareStmt != null) {
				try {
					prepareStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (prepareStmt1 != null) {
				try {
					prepareStmt1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(dbConnection != null){
				try {
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
	public void addMemberDetails(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String grp = request.getParameter("grpname");
		ArrayList<UserId> usrList;
		try {
			HttpSession session = request.getSession(true);
			usrList = getUserNameFromDb();
			session.setAttribute("usrList", usrList);
			session.setAttribute("grpname",grp);
			response.sendRedirect("AddMember.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<UserId> getUserNameFromDb() throws SQLException{
		
		ArrayList<UserId> usrlist = new ArrayList<UserId>();
		
		String searchQuery ="select userid, username from userdetails";
		Statement stmt = null;
    	Connection dbConnection = null;
    	dbConnection = DatabaseUtils.getInstance().getConnection();
    	stmt = dbConnection.createStatement();
    	ResultSet rs = stmt.executeQuery(searchQuery);
		while(rs.next()) {
			UserId user = new UserId();
			user.setUserId(rs.getInt("userid"));
			user.setUserName(rs.getString("username"));
			usrlist.add(user);
		}
		return usrlist;
	}
	
	
	public void DeleteGroupDetails(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String selectedItem = request.getParameter("grpname");
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		ArrayList<ExpenseGroups> grpList;
		ExpenseUser user;
		LoginServlet logser = new LoginServlet();
		String updatesql = "delete from groupdetails where grpid=?";

		try {
			HttpSession session = request.getSession(true);
			dbConnection = DatabaseUtils.getInstance().getConnection();
			prepareStmt = dbConnection.prepareStatement(updatesql);
			int grpid = Integer.parseInt(selectedItem);
			prepareStmt.setInt(1, grpid);
			// execute update SQL stetement
			prepareStmt.executeUpdate();
		    user = (ExpenseUser) session.getAttribute("user");
			grpList = logser.getGroupListfromDB(dbConnection, user.getUserId());   
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

		}
	}

}



