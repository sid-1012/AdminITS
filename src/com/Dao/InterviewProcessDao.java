package com.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Interview;
import com.Util.DbUtil;

public class InterviewProcessDao {

	static int applicantID = 0;
	
	public static String getApplicantName(int applicantID) {
		String applicantName="";
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement("SELECT applicantName from jobapplication where applicantID = '" + applicantID + "'");
			rs = ps.executeQuery();
			while(rs.next()) {
				applicantName = rs.getString("applicantName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applicantName;
	}
	
	public static int getJobApplicationID(int applicantID) {
		int jobApplicationID = 0;
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement("SELECT jobApplicationID FROM jobapplication where applicantID = '" + applicantID + "'");
			rs = ps.executeQuery();
			while(rs.next()) {
				jobApplicationID = rs.getInt("jobApplicationID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobApplicationID;
	}
	
	public static int getInterviewerID(String interviewWith) {
		int interviewerID = 0;
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement("SELECT interviewerID from interviewer where interviewerName = '" + interviewWith + "'");
			rs = ps.executeQuery();
			while(rs.next()) {
				interviewerID = rs.getInt("interviewerID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return interviewerID;
	}

	
	public static int Interview(Interview interview) {
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null, pstmt = null;
		ResultSet rs = null;
		int i =0;
		
		try {
			 pstmt = connection.prepareStatement("select * from interviewprocess where applicantID = ?");
			 pstmt.setInt(1, interview.getApplicantID());
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 if(rs.getInt("roundID") == interview.getRoundNumber()) {
					 ps = connection.prepareStatement("update interviewprocess set applicantName = ?, interviewWith = ?, interviewDate = ?, "
					 								+ "interviewTime = ?, jobTitle = ?, jobApplicationID = ?, interviewerID = ?, department = ?,"
					 								+ "roundID = ?, roundName = ? where applicantID = ?");
				 }
				 else {
					 ps = connection.prepareStatement("insert into interviewprocess (applicantName, interviewWith, interviewDate, interviewTime, jobTitle,"
		 						+ "jobApplicationID, interviewerID, department, roundID, roundName, applicantID)"
		 						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				 }
			 }
			 else {
				 ps = connection.prepareStatement("insert into interviewprocess (applicantName, interviewWith, interviewDate, interviewTime, jobTitle,"
	 						+ "jobApplicationID, interviewerID, department, roundID, roundName, applicantID)"
	 						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			 }
			 
			 ps.setString(1, interview.getApplicantName());
			 ps.setString(2, interview.getInterviewWith());
			 ps.setDate(3, interview.getInterviewDate());
			 ps.setTime(4, interview.getInterviewTime());
			 ps.setString(5, interview.getJobTitle());
			 ps.setInt(6, interview.getJobApplicationID());
			 ps.setInt(7, interview.getInterviewerID());
			 ps.setString(8, interview.getDepartment());
			 ps.setInt(9, interview.getRoundNumber());
			 ps.setString(10, interview.getRoundName());
			 ps.setInt(11, interview.getApplicantID());
			 
			 i = ps.executeUpdate();
			 
			 ps.close();
			 
			 connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}

	public static String getUsername(String applicantName) {
		// TODO Auto-generated method stub
		String username="";
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			ps = connection.prepareStatement("select username from jobapplication where applicantName = '" + applicantName + "'");
			rs = ps.executeQuery();
			while(rs.next()) {
				username = rs.getString("username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}

	public static String getDepartment(int applicantID) {
		// TODO Auto-generated method stub
		String department="";
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			ps = connection.prepareStatement("select department from jobapplication where applicantID = '" + applicantID + "'");
			rs = ps.executeQuery();
			while(rs.next()) {
				department = rs.getString("department");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return department;
	}

	public static int getRoundNumber(String roundNumbertemp) {
		// TODO Auto-generated method stub
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		
		try {
			ps = connection.prepareStatement("select * from rounds where roundName = ?");
			ps.setString(1, roundNumbertemp);
			rs = ps.executeQuery();
			while(rs.next()) {
				i = rs.getInt("roundID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	public static String getJobTitle(int applicantID) {
		// TODO Auto-generated method stub
		String jobTitle="";
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			ps = connection.prepareStatement("select jobTitle from jobapplication where applicantID = '" + applicantID + "'");
			rs = ps.executeQuery();
			while(rs.next()) {
				jobTitle = rs.getString("jobTitle");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobTitle;
	}
}