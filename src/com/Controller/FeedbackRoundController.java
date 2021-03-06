package com.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Util.DbUtil;


/**
 * Servlet implementation class CityController
 */
public class FeedbackRoundController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackRoundController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String applicantName=request.getParameter("name");
	 	String buffer="<select name='roundID' class='form-control input-sm' style='font-size: 15px' required><option value=''>Choose</option>";  
	 	Statement stmt = null;
	 	ResultSet rs = null;
	 	try{ 
	 		Connection con = DbUtil.getCon();   
	 		stmt = con.createStatement();  
	 		rs = stmt.executeQuery("Select * from interviewprocess where applicantName = '" + applicantName + "' and roundID not in "
	 							+ "(select roundID from feedback where applicantName = '" + applicantName + "')");  
	 		
	   		while(rs.next()){
					buffer=buffer+"<option value='"+rs.getString("roundID")+"'>"+rs.getInt("roundID")+"</option>";
	   		}  
	 		buffer=buffer+"</select>";  

	 		response.getWriter().println(buffer); 
	 	}
	 	catch(Exception e){
	     	System.out.println(e);
	 	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
