package com.Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.InterviewProcessDao;
import com.Model.Interview;;


public class InterviewProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InterviewProcessController() {
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
		
		if(request.getParameter("submit").equalsIgnoreCase("Schedule Interview"))
		{
			
			int applicantID = Integer.parseInt(request.getParameter("applicantName"));
			String applicantName = InterviewProcessDao.getApplicantName(applicantID);
			String interviewWith = request.getParameter("interviewWith");
			
			String date = request.getParameter("interviewDate");
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
		
			Date interviewDate = new Date(utilDate.getTime());
			
			String time = request.getParameter("interviewTime");
			DateFormat format1 = new SimpleDateFormat("HH:mm");
			java.util.Date utilDate1 = null;
			try {
				utilDate1 = format1.parse(time);
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Time interviewTime = new Time(utilDate1.getTime());
			String roundName = request.getParameter("roundName");
			String roundNumbertemp = request.getParameter("roundNumber");
			String jobTitle = InterviewProcessDao.getJobTitle(applicantID);
			int roundNumber = InterviewProcessDao.getRoundNumber(roundNumbertemp);
			int jobApplicationID = InterviewProcessDao.getJobApplicationID(applicantID);
			int interviewerID = InterviewProcessDao.getInterviewerID(interviewWith);
			String department = InterviewProcessDao.getDepartment(applicantID);
			
			System.out.println(applicantName);
			System.out.println(jobTitle);
			System.out.println(applicantID);
			System.out.println(interviewWith);
			System.out.println(interviewDate);
			System.out.println(interviewTime);
			System.out.println(interviewerID);
			System.out.println(jobApplicationID);
			
				
			Interview interview = new Interview();
			interview.setApplicantName(applicantName);
			interview.setInterviewWith(interviewWith);
			interview.setInterviewDate(interviewDate);
			interview.setInterviewTime(interviewTime);
			interview.setJobTitle(jobTitle);
			interview.setApplicantID(applicantID);
			interview.setInterviewerID(interviewerID);
			interview.setJobApplicationID(jobApplicationID);
			interview.setDepartment(department);
			interview.setRoundName(roundName);
			interview.setRoundNumber(roundNumber);
			
			int	i = InterviewProcessDao.Interview(interview);
			
			System.out.println(i);
			
			if(i>0){
				
					session.setAttribute("scheduled", true);
					
					/*request.getRequestDispatcher("InterviewProcess.jsp").forward(request, response);*/
					
					MailAppController ob=new MailAppController();
					ob.doPost(request, response, applicantID, jobApplicationID);
					request.setAttribute("msg", "Interview Scheduled");
					request.getRequestDispatcher("InterviewProcess.jsp").forward(request, response);
					//request.setAttribute("list", list);
					//request.getRequestDispatcher("EducationalDetails.jsp").forward(request, response);
			}else {
					request.setAttribute("msg", "Interview not scheduled");
					request.getRequestDispatcher("InterviewProcess.jsp").forward(request, response);
			}
		}
			
	}
			
}
