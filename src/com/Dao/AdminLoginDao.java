package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Model.Admin;
import com.Util.DbUtil;


public class AdminLoginDao {
	
	public static Admin authenticate(Admin admin) {
		Connection connection = DbUtil.getCon();
		PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		
		Admin admin1 = null;
		try {
			 ps = connection.prepareStatement("select * from adminlogin where username = '" + admin.getUsername() +"' and password = '" + admin.getPassword() +"'");
			 rs= ps.executeQuery();
			 
			 while(rs.next()){
				 
				 admin1 = new Admin();
				 admin1.setUsertype(rs.getString("usertype"));
				 admin1.setUsername(rs.getString("username"));
				 admin1.setPassword(rs.getString("password"));
			 }
		}
			 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return admin1;
}

}
