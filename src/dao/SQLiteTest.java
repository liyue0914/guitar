package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class SQLiteTest {
	public String fileName;
	private static Connection conn=null;
	public static Connection getConnection(){
	  try {
	   String fileName = "D:/Program Files (x86)/sqlite/sqlite-tools-win32-x86-3130000/guitarshop.db";
	   Connection conn = DriverManager.getConnection("jdbc:sqlite:"+fileName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}

