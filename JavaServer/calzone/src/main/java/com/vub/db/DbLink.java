package com.vub.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class DbLink {
	
	private static DbConfigFile dbconfig = new DbConfigFile("Wilmadbconfig.txt");
	
	private static String db_user = "se2_1314";//dbconfig.getUser();
	private static String db_password = "Bean59Cabal";//dbconfig.getPassword();
	private static String db_name = "se2_1314";//dbconfig.getName();
	private static String url = "jdbc:mysql://wilma.vub.ac.be";//dbconfig.getUrl();
	
	private static Connection db_connection;
	
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public static ResultSetMetaData rsmd = null;
	
	public static ResultSet executeSqlQuery(String sql) {
		try {
			//System.out.println("JAAAAAAAAAA 1");
			//db_connection = DriverManager.getConnection(url,db_user,db_password);
        	//db_connection.setCatalog(db_name);
			//stmt = db_connection.createStatement();
			System.out.println("JAAAAAAAAAA 2");
			return stmt.executeQuery(sql);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
            return null;
		}
	}
	
	public static boolean executeSql(String sql) {
		try {
			return stmt.execute(sql);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
            return false;
		}
	}
	
	public static void closeConnection() {
		try {
			//rs.close();
			//stmt.close();
			db_connection.close();
			System.out.println("\n!- Connection closed. -!");
		} catch (SQLException ex) {
			// handle the error
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
		}
	}
	
	public static void openConnection() {
        try {
        	
        	System.out.println("++++ OPENING CONNECTION ++++");
        	
        	db_connection = DriverManager.getConnection(url,db_user,db_password);
        	db_connection.setCatalog(db_name);
        	stmt = db_connection.createStatement();
        	
        	System.out.println("++++ CONNECTION OPENED ++++");	
        	
        	// rs = executeSqlQuery("SELECT * FROM Persons;");
        	// test ( bij elk nieuw gebruik van stmt wordt rs geclosed !! )
        	// while(rs.next()){
        	//  	System.out.println(rs.getString(2)+" "+rs.getString(3));
        	// }
        	// rs.beforeFirst();
        	// rsmd = rs.getMetaData();
        	// int nrOfCol = rsmd.getColumnCount();
        	// System.out.println("Column count of table: "+nrOfCol);

        	
        } catch (Exception ex) {
            // handle the error
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
    }
	
	public static void main(String[] args) {
		
	}
}