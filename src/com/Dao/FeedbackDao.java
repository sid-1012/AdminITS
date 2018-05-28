package com.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Feedback;
import com.Util.DbUtil;

public class FeedbackDao {

	public static int getApplicantID(String applicantName) {
		// TODO Auto-generated method stub
		Connection conn = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int applicantID = 0;
		
		try {
			ps = conn.prepareStatement("select * from interviewprocess where applicantName = '" + applicantName + "'");
			rs = ps.executeQuery();
			while(rs.next()) {
				applicantID = rs.getInt("applicantID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applicantID;
	}

	public static int record(Feedback feedback) {
		// TODO Auto-generated method stub
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null, pstmt = null;
		ResultSet rs = null;
		int i = 0;
		
		try {
			pstmt = connection.prepareStatement("select * from feedback where applicantID = ?");
			pstmt.setInt(1, feedback.getApplicantID());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("roundID") == feedback.getRoundID()) {
					ps = connection.prepareStatement("update feedback set applicantName = ?, domain = ?, technical = ?, communication = ?,"
												+ "leadership = ?, teamwork = ?, status = ?, roundID = ?, roundName = ? where applicantID = ?");
				}
				else {
					ps = connection.prepareStatement("insert into feedback(applicantName, domain, technical, communication, leadership, teamwork,"
							+ "status, roundID, roundName, applicantID) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				}
			}
			else {
				ps = connection.prepareStatement("insert into feedback(applicantName, domain, technical, communication, leadership, teamwork,"
												+ "status, roundID, roundName, applicantID) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			}
			ps.setString(1, feedback.getApplicantName());
			ps.setString(2, feedback.getDomain());
			ps.setString(3, feedback.getTechnical());
			ps.setString(4, feedback.getCommunication());
			ps.setString(5, feedback.getLeadership());
			ps.setString(6, feedback.getTeamwork());
			ps.setString(7, feedback.getStatus());
			ps.setInt(8, feedback.getRoundID());
			ps.setString(9, feedback.getRoundName());
			ps.setInt(10, feedback.getApplicantID());
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public static int closeProcess(int applicantID) {
		// TODO Auto-generated method stub
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			ps = connection.prepareStatement("update signup set flag = 1 where applicantID = ?");
			ps.setInt(1, applicantID);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public static String getRoundName(int roundID) {
		// TODO Auto-generated method stub
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String roundName = "";
		
		try {
			ps = connection.prepareStatement("select roundName from rounds where roundID = ?");
			ps.setInt(1, roundID);
			rs = ps.executeQuery();
			while(rs.next()) {
				roundName = rs.getString("roundName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roundName;
	}

	
}
