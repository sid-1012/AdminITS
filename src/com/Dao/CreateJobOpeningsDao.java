package com.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Model.JobOpenings;
import com.Util.DbUtil;

public class CreateJobOpeningsDao {

	public static int JobOpenings(JobOpenings jobOpenings) {
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null, pstmt = null;
		ResultSet rs = null;
		int i = 0;
		
		try {
			 pstmt = connection.prepareStatement("select * from jobopenings where jobTitle = ? and department = ?");
			 pstmt.setString(1, jobOpenings.getJobTitle());
			 pstmt.setString(2, jobOpenings.getDepartment());
			 rs = pstmt.executeQuery();
			 if(rs.next())
			 {
				 ps = connection.prepareStatement("update jobopenings set noOfOpenings = ? where department = ? and jobTitle = ?");
			 }
			 else
			 {
				 ps = connection.prepareStatement("insert into jobopenings(noOfOpenings, department, jobTitle) values(?,?,?)");
			 }
			 
			 ps.setInt(1, jobOpenings.getNoOfOpenings());			 
			 ps.setString(2, jobOpenings.getDepartment());
			 ps.setString(3, jobOpenings.getJobTitle());
			 
			 i = ps.executeUpdate();
			 
			 ps.close();
			 
			 connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
}
