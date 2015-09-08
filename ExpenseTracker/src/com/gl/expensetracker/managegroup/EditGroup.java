package com.gl.expensetracker.managegroup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expencetracker.ExpenseGroups;
import com.gl.expencetracker.ExpenseUser;
import com.gl.expensetracker.connection.DatabaseUtils;

/**
 * Servlet implementation class EditGroup
 */
@WebServlet("/jsp/EditGroup")
public class EditGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditGroup() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String selectedItem = request.getParameter("grpname");
		String newgrpname = request.getParameter("newgrpname");
		Connection dbConnection = null;
		PreparedStatement prepareStmt = null;
		ArrayList<ExpenseGroups> grpList = new ArrayList<ExpenseGroups>();
		ArrayList<ExpenseGroups> grpList1 = new ArrayList<ExpenseGroups>();
		RequestDispatcher requestDispatcher;
		String updatesql = "update groupdetails set grpname=?"
				+ " where grpid=?";
		
		try {
			HttpSession session = request.getSession(true);
			DatabaseUtils db = new DatabaseUtils();
			dbConnection = db.getConnection();
			prepareStmt = dbConnection.prepareStatement(updatesql);
			prepareStmt.setString(1, newgrpname);
			int grpid = Integer.parseInt(selectedItem);
			prepareStmt.setInt(2, grpid);
			// execute update SQL stetement
			prepareStmt.executeUpdate();
			grpList = (ArrayList<ExpenseGroups>)session.getAttribute("grpList");
			for (ExpenseGroups grp: grpList) {
				ExpenseGroups grpusr = new ExpenseGroups();
			    System.out.println(grp.getGrpName());
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
			requestDispatcher = request.getRequestDispatcher("welcome.jsp");
			requestDispatcher.forward(request, response);


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


