package com.gl.expensetracker.usrprof;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gl.expensetracker.object.ExpenseUser;

/**
 * Servlet implementation class VerifyOldPass
 */
@WebServlet("/jsp/VerifyOldPass")
public class VerifyOldPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyOldPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oldpass = request.getParameter("oldpass");
		HttpSession session = request.getSession(true);
		ExpenseUser curruser = new ExpenseUser();
		curruser = (ExpenseUser) session.getAttribute("user");
		String Msg = null;
		response.setContentType("text/plain");
		if(!(oldpass.equals(curruser.getPassWord()))){
			 Msg = "FAILURE";
		}
		else{
			 Msg = "SUCCESS";
		}
		
		response.getWriter().write(Msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
