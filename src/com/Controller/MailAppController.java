package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.SendMailDao;
import com.Model.Feedback;

/**
 * Servlet implementation class MailApp
 */
public class MailAppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String from = "team.its46326";  // GMail user name (just the part before "@gmail.com")
    final String pass = "its46326"; // GMail password
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailAppController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @param ApplicantID 
	 * @param jobApplicationID 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response, int ApplicantID, int jobApplicationID) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        /*out.println("in the mail app servlet");*/
        final String subject = "Interview Details";
        
        String email = SendMailDao.GetEmail(ApplicantID);
        String body = SendMailDao.GetBody(jobApplicationID);
        /*String to = request.getParameter("to");*/
        /*String subject = request.getParameter("msg");*/
        /*String body =  request.getParameter("msg");*/
        /*String from = request.getParameter("user");
        String pass = request.getParameter("pass");*/
        /*SendMail.send(to,subject, message, user, pass);*/
        
        String str = SendMailDao.sendFromGMail(from, pass, email, subject, body);
        while(str.equals("Failure")) {
        	str = SendMailDao.sendFromGMail(from, pass, email, subject, body);
        }
        /*out.println("Mail send successfully");*/

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response,  Feedback feedback) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        /*out.println("in the mail app servlet");*/
        final String subject = "Interview Result";
        
        String email = SendMailDao.GetEmail(feedback.getApplicantID());
        String resultBody = SendMailDao.getResultBody(feedback);
        
        String str = SendMailDao.sendFromGMail(from, pass, email, subject, resultBody);
        while(str.equals("Failure")) {
        	str = SendMailDao.sendFromGMail(from, pass, email, subject, resultBody);
        }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, int applicantID, String applicantName, int roundID) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        /*out.println("in the mail app servlet");*/
        final String subject = "Interview Result";
        
        String email = SendMailDao.GetEmail(applicantID);
        String roundBody = SendMailDao.getRoundBody(applicantName, roundID);
        
        String str = SendMailDao.sendFromGMail(from, pass, email, subject, roundBody);
        while(str.equals("Failure")) {
        	str = SendMailDao.sendFromGMail(from, pass, email, subject, roundBody);
        }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, String applicantName, Feedback feedback) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		final String subject = "Successful Completion of Interview Process";
        
        String email = SendMailDao.GetEmail(feedback.getApplicantID());
        String offerBody = SendMailDao.getOfferBody(applicantName, feedback);
        
        String str = SendMailDao.sendFromGMail(from, pass, email, subject, offerBody);
        while(str.equals("Failure")) {
        	str = SendMailDao.sendFromGMail(from, pass, email, subject, offerBody);
        }
	}
}
