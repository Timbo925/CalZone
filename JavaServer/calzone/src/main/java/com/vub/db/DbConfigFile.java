package com.vub.db;

import java.io.*;

import javax.servlet.ServletContext;

public class DbConfigFile {

	private static String user = null;
	private static String password = null;
	private static String name = null;
	private static String url = null;
	
	public DbConfigFile(String fileName) {
		
		//ServletContext context;
		File file = new File(fileName);//context.getRealPath(fileName);
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			user = reader.readLine();
			password = reader.readLine();
			name = reader.readLine();
			url = reader.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}


	public static String getUser() {
		return user;
	}


	public static String getPassword() {
		return password;
	}


	public static String getName() {
		return name;
	}


	public static String getUrl() {
		return url;
	}
	
	
	public static void main(String[] args){
	
	}

	@Override
	public String toString() {
		return "dbConfigFile [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
