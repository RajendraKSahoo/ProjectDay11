package com.ibm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public int countQuery(String query) throws SQLException
	{
		int count=0;
		Connection con = DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries", "foodsonfinger_atoz", "welcome@123");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(query);
		if(rs.next())
		{
			count = rs.getInt(1);
		}
		return count;
	}
	
	public String singleDataQuery(String query) throws SQLException
	{
		String text=null;
		Connection con = DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries", "foodsonfinger_atoz", "welcome@123");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(query);
		if(rs.next())
		{ 		
			text = rs.getString(1);
		}
		return text;
	}
	
	public static Object[] lineDataQuery(String query,int[] cols) throws SQLException
	{
		Object[] data = new Object[cols.length];
		Connection con = DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries", "foodsonfinger_atoz", "welcome@123");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(query);
		int i=0;
		if(rs.next())
		{
			for(int col:cols)
			{
				data[i] = rs.getObject(col);
				i++;
			}
		}
		return data;
	}

}
