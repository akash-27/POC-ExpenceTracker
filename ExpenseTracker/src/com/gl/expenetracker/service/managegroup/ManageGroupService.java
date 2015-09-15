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

	@SuppressWarnings("unchecked")
	public void insertGroupDetails(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		String grpname = request.getParameter("grp");
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		PreparedStatement prepareStmt2 = null; 
		PreparedStatement prepareStmt1 = null;
		ExpenseUser user;
		int grpid = 0;
		ExpenseGroups grp  = new ExpenseGroups();
		ArrayList<ExpenseGroups> grpList;
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
			String searchQuery ="select grpid from groupdetails where grpname=?";
			prepareStmt2 = dbConnection.prepareStatement(searchQuery);
			prepareStmt2.setString(1, grpname);
			ResultSet rs1 = prepareStmt2.executeQuery();
			while(rs1.next()){
				grpid = rs1.getInt("grpid");
			}
			prepareStmt1 = dbConnection.prepareStatement(grpmapsql);
			prepareStmt1.setInt(1, grpid);
			prepareStmt1.setInt(2, user.getUserId());
			System.out.println(prepareStmt1);
			prepareStmt1.executeUpdate();
			dbConnection.commit();
			grp.setCreatedBy(user.getUserId());
			grp.setGrpId(grpid);
			grp.setGrpName(grpname);
			grp.setNumber(1);
			grpList = (ArrayList<ExpenseGroups>) session.getAttribute("grpList");
			if(grpList == null)
			{
				grpList = new ArrayList<ExpenseGroups>();
			}
			grpList.add(grp);
			session.setAttribute("grpList", grpList);
			response.sendRedirect("welcome.jsp");

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
				grpusr.setNumber(grp.getNumber());
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

	public void AddMembersDetails(HttpServletRequest request, HttpServletResponse response){
		String selectedItem = request.getParameter("usrname1");
		String newgrpname = request.getParameter("addgrpname1");
		String phone = request.getParameter("phoneno1");
		String emailid = request.getParameter("emailid1");
		if (request.getParameter("add") != null) {
			Connection dbConnection = null;
			PreparedStatement prepareStmt = null;
			String selectsql = null;
			int usrid = 0;
			ArrayList<ExpenseGroups> grpList;


			try {
				dbConnection = DatabaseUtils.getInstance().getConnection();
				if(selectedItem != null){
					if(selectedItem.equalsIgnoreCase("")){
						try {
							response.sendRedirect("Error.jsp");
							return;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(!selectedItem.equalsIgnoreCase("")){
						selectsql = "insert into usrgrpmap(grpid,userid) values(?,?);";
						usrid = Integer.parseInt(selectedItem);
						int grpid = Integer.parseInt(newgrpname);
						prepareStmt = dbConnection.prepareStatement(selectsql);
						prepareStmt.setInt(1, grpid);
						prepareStmt.setInt(2, usrid);
						prepareStmt.executeUpdate();
					}
				}
				else if(!phone.equalsIgnoreCase("")){
					selectsql = "insert into usrgrpmap(grpid,userid) values(?,?);";
					usrid = getUserIdFromDbPhone(phone); 
					int grpid = Integer.parseInt(newgrpname);
					prepareStmt = dbConnection.prepareStatement(selectsql);
					prepareStmt.setInt(1, grpid);
					prepareStmt.setInt(2, usrid);
					prepareStmt.executeUpdate();
				}
				else if(!emailid.equalsIgnoreCase("")){
					selectsql = "insert into usrgrpmap(grpid,userid) values(?,?);";
					usrid = getUserIdFromDb(emailid);
					int grpid = Integer.parseInt(newgrpname);
					prepareStmt = dbConnection.prepareStatement(selectsql);
					prepareStmt.setInt(1, grpid);
					prepareStmt.setInt(2, usrid);
					prepareStmt.executeUpdate();
				}
				HttpSession session = request.getSession(true);
				ExpenseUser user;
				user = (ExpenseUser) session.getAttribute("user");
				LoginServlet log = new LoginServlet();
				grpList = log.getGroupListfromDB(dbConnection,user.getUserId() );   
				session.setAttribute("grpList", grpList);
				try {
					response.sendRedirect("welcome.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					try {
						response.sendRedirect("Error.jsp");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				try {
					response.sendRedirect("Error.jsp");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		else if (request.getParameter("cancel") != null) {
			try {
				response.sendRedirect("welcome.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void verifyEmail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String oldpass = request.getParameter("emailid1");
		String Msg = null;

		try {

			int rs = getUserIdFromDb(oldpass); 
			response.setContentType("text/plain");

			if (rs == 0){
				Msg = "FAILURE";
			}
			else{
				Msg = "SUCCESS";
			}

			response.getWriter().write(Msg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("Error.jsp");
			e.printStackTrace();
		}
	}
	public void verifyPhone(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String oldpass = request.getParameter("phoneno1");
		String Msg = null;
		try {

			int rs = getUserIdFromDbPhone(oldpass); 
			response.setContentType("text/plain");

			if (rs == 0){
				Msg = "FAILURE";
			}
			else{
				Msg = "SUCCESS";
			}

			response.getWriter().write(Msg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("Error.jsp");
			e.printStackTrace();
		}
	}

	public int getUserIdFromDb(String oldpass) throws SQLException{
		Statement stmt = null;
		Connection dbConnection = null;
		String updatesql = "select userid from userdetails where emailid='" + oldpass + "';";
		int usrid = 0;
		dbConnection = DatabaseUtils.getInstance().getConnection();
		stmt = dbConnection.createStatement();
		ResultSet rs = stmt.executeQuery(updatesql);
		while(rs.next()){
			usrid = rs.getInt("userid");
		}

		return usrid;
	}
	public int getUserIdFromDbPhone(String oldpass) throws SQLException{
		Statement stmt = null;
		Connection dbConnection = null;
		String updatesql = "select userid from userdetails where phone='" + oldpass + "';";
		int usrid = 0;

		dbConnection = DatabaseUtils.getInstance().getConnection();
		stmt = dbConnection.createStatement();
		ResultSet rs = stmt.executeQuery(updatesql);
		while(rs.next()){
			usrid = rs.getInt("userid");
		}

		return usrid;
	}
}



