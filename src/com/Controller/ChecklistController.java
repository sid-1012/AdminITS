package com.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.ChecklistDao;
import com.Model.Checklist;;


public class ChecklistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ChecklistController() {
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
		
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("submit").equalsIgnoreCase("Save"))
		{
			
			String applicantName = request.getParameter("applicantName");
			int applicantID = ChecklistDao.getApplicantID(applicantName);
			String checkedQualifications = request.getParameter("checkedQualifications");
			String checkedReferences = request.getParameter("checkedReferences");		
			String date = request.getParameter("startDate");
			//System.out.println(date);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = null;
			try {
				utilDate = format.parse(date);
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			Date startDate = new Date(utilDate.getTime());
			
			Checklist checklist = new Checklist();
			checklist.setApplicantName(applicantName);
			checklist.setApplicantID(applicantID);
			checklist.setCheckedQualifications(checkedQualifications);
			checklist.setCheckedReferences(checkedReferences);
			checklist.setStartDate(startDate);
			
			int	i = ChecklistDao.record(checklist);
			
			System.out.println(i);
			
			if(i>0){
				
					session.setAttribute("checklist", true);
					
					/*request.getRequestDispatcher("InterviewProcess.jsp").forward(request, response);*/
					request.setAttribute("msg", "Your response has been recorded!!!");
					request.getRequestDispatcher("Checklist.jsp").forward(request, response);
					//request.setAttribute("list", list);
					//request.getRequestDispatcher("EducationalDetails.jsp").forward(request, response);
			}else {
					request.setAttribute("msg", "Your response could not be recorded due to an error!!!");
					request.getRequestDispatcher("Checklist.jsp").forward(request, response);
			}
		}
			
	}
			
}
