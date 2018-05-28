package com.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.Model.Feedback;
import com.Util.DbUtil;

public class SendMailDao {
    
	public static String GetEmail(int applicantID) {
		// TODO Auto-generated method stub
		String email=null;
    	ResultSet rs = null;
    	Connection connection = DbUtil.getCon();
    	try {
			PreparedStatement ps = connection.prepareStatement("select email from signup where applicantID = ?");
			ps.setInt(1, applicantID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				email = rs.getString("email");
				System.out.println(email);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return email;
	}
    
	public static String GetBody(int jobApplicationID) {
		String result=null;
		String interviewWith=null;
		String interviewDate=null;
		String interviewTime=null;
		String applicantName=null;
		String jobTitle=null;
		String roundName = null;
		
    	ResultSet rs = null;
    	Connection connection = DbUtil.getCon();
    	try {
			PreparedStatement ps = connection.prepareStatement("select * from interviewprocess where jobApplicationID = ?");
			ps.setInt(1, jobApplicationID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				interviewWith = rs.getString("interviewWith");
				interviewDate = rs.getString("interviewDate");
				interviewTime = rs.getString("interviewTime");
				applicantName = rs.getString("applicantName");
				jobTitle = rs.getString("jobTitle");
				roundName = rs.getString("roundName");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	result = "Dear Mr./Ms." + " " + applicantName + "," + System.lineSeparator()
    				 + System.lineSeparator()
    				 + "Interview Schedule :" + System.lineSeparator() + System.lineSeparator() 
    				 + "Position Applied for :" + " " + jobTitle + System.lineSeparator()
    				 + "Interview Date       :" + " " + interviewDate + System.lineSeparator()
    				 + "Interview Time       :" + " " + interviewTime + System.lineSeparator()
    				 + "Interview With       :" + " " + interviewWith + System.lineSeparator()
    				 + "Type of Round        :" + " " + roundName + System.lineSeparator()
    				 + System.lineSeparator()
    				 /*+ "Your interview has been scheduled for the job title " + jobTitle + "dated "
    				 + interviewDate +  "at" + interviewTime + "with " + interviewWith*/
    				 + "Report 20 minutes before the scheduled time to avoid last minute rush. "
    				 + "Wishing you all the best for the interview." 
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "Regards," + System.lineSeparator()
    				 + "Team ITS";
    	
    	System.out.println(result);
    	return result;
    	
	}
	
	
    public static String sendFromGMail(String from, String pass, String email, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[email.length()];

            // To get the array of addresses
            for( int i = 0; i < email.length(); i++ ) {
                toAddress[i] = new InternetAddress(email);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "Failure";
        }
        return "Success";
    }

	public static String getResultBody(Feedback feedback) {
		// TODO Auto-generated method stub
		String resultBody = null;
		String applicantName = feedback.getApplicantName();
		String jobTitle = null;
		int applicantID = feedback.getApplicantID();
		
    	ResultSet rs = null;
    	Connection connection = DbUtil.getCon();
    	try {
			PreparedStatement ps = connection.prepareStatement("select jobTitle from jobapplication where applicantID = ?");
			ps.setInt(1, applicantID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				jobTitle = rs.getString("jobTitle");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	resultBody = "Dear Mr./Ms." + " " + applicantName + "," + System.lineSeparator()
    				 + System.lineSeparator()
    				 + "We appreciate your interest in our company and the time you’ve invested in "
    				 + "applying for the " + jobTitle + " opening." + System.lineSeparator() + System.lineSeparator()
    				 + "We ended up moving forward with another candidate, but we’d like to thank you for talking "
    				 + "to our team and giving us the opportunity to learn about your skills and accomplishments."
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "We will be advertising more positions in the coming months. We hope you’ll keep us in mind "
    				 + "and we encourage you to apply again." + System.lineSeparator() + System.lineSeparator()
    				 + "We wish you good luck with your job search and professional future endeavors."
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "Regards," + System.lineSeparator()
    				 + "Team ITS";
    	
    	System.out.println(resultBody);
    	return resultBody;
	}

	public static String getRoundBody(String applicantName, int roundID) {
		// TODO Auto-generated method stub
		String roundBody = null;
		String applicantname = applicantName;
		String roundName = null;
		int roundid = roundID;
		
    	ResultSet rs = null;
    	Connection connection = DbUtil.getCon();
    	try {
			PreparedStatement ps = connection.prepareStatement("select roundName from rounds where roundID = ?");
			ps.setInt(1, roundid);
			rs = ps.executeQuery();
			while(rs.next())
			{
				roundName = rs.getString("roundName");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	roundBody = "Dear Mr./Ms." + " " + applicantname + "," + System.lineSeparator()
    				 + System.lineSeparator()
    				 + "Congratulations for successfully clearing the " + roundName + " round."
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "You will be informed with further updates."
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "Regards," + System.lineSeparator()
    				 + "Team ITS";
    	
    	System.out.println(roundBody);
    	return roundBody;
	}

	public static String getOfferBody(String applicantName, Feedback feedback) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	Calendar c = Calendar.getInstance();
    	c.setTime(new Date());
    	c.add(Calendar.MONTH, 2);
    	String joinDate = sdf.format(c.getTime());
    	
		String offerBody = null;
		String jobTitle = null;
		String department = null;
		String roundName = null;
		String applicantname = applicantName;
		int roundID = feedback.getRoundID();
		
		PreparedStatement ps = null, pstmt = null;
    	ResultSet rs = null, rs1 = null;
    	Connection connection = DbUtil.getCon();
    	try {
			ps = connection.prepareStatement("select roundName from rounds where roundID = ?");
			ps.setInt(1, roundID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				roundName = rs.getString("roundName");
			}
			
			pstmt = connection.prepareStatement("select * from jobapplication where applicantID = ?");
			pstmt.setInt(1, feedback.getApplicantID());
			rs1 = pstmt.executeQuery();
			while(rs1.next()) {
				jobTitle = rs1.getString("jobTitle");
				department = rs1.getString("department");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	offerBody = "Dear Mr./Ms." + " " + applicantname + "," + System.lineSeparator()
    				 + System.lineSeparator()
    				 + "With reference to your application and subsequent interview, we congratulate you "
    				 + "for successfully clearing the " + roundName + " round. We are pleased to "
    				 + "offer you the job for post of " + jobTitle + " at our company, in "
    				 + department + " department."
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "The detailed appointment letter will be given to you at the time of joining. "
    				 + "You have to join on or before " + joinDate + " otherwise this offer will stand "
    				 + "withdrawn automatically."
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "You will be paid salary as discussed during the " + roundName + " round."
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "You are requested to bring attested copies of, along with the original certificates/testimonials "
    				 + "at the time of joining, the following:" + System.lineSeparator()
    				 + "			* Educational certificates" + System.lineSeparator()
    				 + "			* Three passport size photographs" + System.lineSeparator()
    				 + "			* Any Government issued valid identity proof" + System.lineSeparator()
    				 + "			* Medical fitness certificate" + System.lineSeparator() + System.lineSeparator()
    				 + "We would like to take this opportunity to wish you a successful career with us."
    				 + System.lineSeparator() + System.lineSeparator()
    				 + "Regards," + System.lineSeparator()
    				 + "Team ITS";
    	
    	System.out.println(offerBody);
    	return offerBody;
	}
	
}