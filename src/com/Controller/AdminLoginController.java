package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.AdminLoginDao;
import com.Model.Admin;

/**
 * Servlet implementation class LoginController
 */
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		HttpSession session = null;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		Admin admin2 = AdminLoginDao.authenticate(admin);
		
		if(admin2!=null)
		{
			session = request.getSession(true);
			//session.setMaxInactiveInterval(30);
			session.setAttribute("authenticated", true);
			session.setAttribute("usertype", admin2.getUsertype());
			System.out.println(admin2.getUsertype());
			//if(rs.next()){
			response.sendRedirect("ViewApplicants.jsp");
			
		}
		else
		{
			request.setAttribute("msg", "Incorrect Username / Password!!!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}		
	}

}

