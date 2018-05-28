package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dao.FeedbackDao;
import com.Model.Feedback;;


public class FeedbackFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FeedbackFormController() {
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
			int applicantID = FeedbackDao.getApplicantID(applicantName);
			String domain = request.getParameter("domain");
			String technical = request.getParameter("technical");
			String communication = request.getParameter("communication");
			String leadership = request.getParameter("leadership");
			String teamwork = request.getParameter("teamwork");
			String status = request.getParameter("status");
			int roundID = Integer.parseInt(request.getParameter("roundID"));
			String roundName = FeedbackDao.getRoundName(roundID);
				
			Feedback feedback = new Feedback();
			feedback.setApplicantName(applicantName);
			feedback.setApplicantID(applicantID);
			feedback.setDomain(domain);
			feedback.setTechnical(technical);
			feedback.setCommunication(communication);
			feedback.setLeadership(leadership);
			feedback.setTeamwork(teamwork);
			feedback.setStatus(status);
			feedback.setRoundID(roundID);
			feedback.setRoundName(roundName);
			
			int	i = FeedbackDao.record(feedback);
			
			System.out.println(i);
			
			if(i>0){
				
				if(status.equals("Not Cleared")) {
					int j = FeedbackDao.closeProcess(applicantID);
					if (j>0) {
						MailAppController mailAppController = new MailAppController();
						mailAppController.doPost(request, response, feedback);
					}
				}
				else if(status.equals("Cleared") && roundID<=2) {
					MailAppController mailAppController = new MailAppController();
					mailAppController.doPost(request, response, applicantID, applicantName, roundID);
				}
				else if(status.equals("Cleared") && roundID==3) {
					int k = FeedbackDao.closeProcess(applicantID);
					if(k>0) {
						MailAppController mailAppController = new MailAppController();
						mailAppController.doPost(request, response, applicantName, feedback);
					}
				}
				
					session.setAttribute("feedback", true);
					
					/*request.getRequestDispatcher("InterviewProcess.jsp").forward(request, response);*/
					request.setAttribute("msg", "Your response has been recorded!!!");
					request.getRequestDispatcher("Feedback.jsp").forward(request, response);
					//request.setAttribute("list", list);
					//request.getRequestDispatcher("EducationalDetails.jsp").forward(request, response);
			}else {
					request.setAttribute("msg", "Your response could not be recorded due to an error!!!");
					request.getRequestDispatcher("Feedback.jsp").forward(request, response);
			}
		}
			
	}
			
}
