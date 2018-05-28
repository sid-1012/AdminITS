package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.Checklist;
import com.Util.DbUtil;

public class ChecklistDao {

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

	public static int record(Checklist checklist) {
		// TODO Auto-generated method stub
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			ps = connection.prepareStatement("insert into checklist(applicantName, applicantID, checkedQualifications, checkedReferences, startDate)"
												+ "values(?, ?, ?, ?, ?)");
			ps.setString(1, checklist.getApplicantName());
			ps.setInt(2, checklist.getApplicantID());
			ps.setString(3, checklist.getCheckedQualifications());
			ps.setString(4, checklist.getCheckedReferences());
			ps.setDate(5, checklist.getStartDate());
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
