package com.vub.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTranslate {
	
	private static DbLink DbLink;
	private static ResultSet rs = null;
	
	public static void openConnection(){
		DbLink.openConnection();
	}
	
	public static void showPersons(){
		
		rs = DbLink.executeSqlQuery("SELECT * FROM TestPersons");

		try {
			System.out.println("JAJAJA");
			while(rs.next()){
	          	System.out.println(rs.getInt(1) + " " +rs.getString(2)+" "+rs.getString(3));
	        }
			System.out.println("\n");
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public static void selectUser(int PersonID){
		String Users = "TestUsers";
		rs = DbLink.executeSqlQuery("SELECT * FROM " + Users + " WHERE PersonID = "+ PersonID  +";");
		
		System.out.println("\nSelecting user with PersonID = "+PersonID);
		
		try {
			// test : System.out.println("Test rs.isBeforeFirst() = "+ rs.isBeforeFirst());
			if (!rs.isBeforeFirst()){
				System.out.println("-> ! This person is not a registered user !");
			}
			else {
				while(rs.next()){
		          	System.out.println("-> "+rs.getString(2));
		        }
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public static void addActivationKey(String KeyString, int NotRegisteredUserID){
		String ActivationKeys = "TestActivationKeys";
		DbLink.executeSql("INSERT INTO  " + ActivationKeys + " (`KeyString`, `CreatedOn`, `NotRegisteredUserID`) VALUES ('" + KeyString + "', NOW() , '" + NotRegisteredUserID + "');");
	}
	
	public static void addNotRegisteredUser(String Username, String Password, int PersonID){
		String NotRegisteredUsers = "TestNotRegisteredUsers";
		DbLink.executeSql("INSERT INTO  " + NotRegisteredUsers + " (`Username`, `Password`, `PersonID`) VALUES ('" + Username + "',  '" + Password + "',  '" + PersonID + "');");
	}
	
	public static void addUser(String Username, String Password, int PersonID){
		String Users = "TestUsers";
		DbLink.executeSql("INSERT INTO  " + Users + " (`Username`, `Password`, `PersonID`) VALUES ('" + Username + "',  '" + Password + "',  '" + PersonID + "');");
	}
	
	public static void addPerson(String LastName, String FirstName, String Email, String BirthDate){
		String Persons = "TestPersons";
		DbLink.executeSql("INSERT INTO  " + Persons + " (`LastName`, `FirstName`, `Email`, `BirthDate`) VALUES ('" + LastName + "',  '" + FirstName + "',  '" + Email + "',  '" + BirthDate + "');");
	}
	
	public static void addUserType(String UserTypeName, int Permission){
		String UserTypes = "TestUserTypes";
		DbLink.executeSql("INSERT INTO  " + UserTypes + " (`UserTypeName` ,`Permission`) VALUES ('" + UserTypeName + "',  '" + Permission + "');");
	}
	
	public static void fillPersons(){
		addPerson("Suarez Groen", "Fernando", "Fernando.Suarez.Groen@vub.ac.be", "1993-01-01");
		addPerson("Coppens", "Youri", "Youri.Coppens@vub.ac.be", "1993-01-01");
		addPerson("Carraggi", "Nicolas", "Nicolas.Carraggi@vub.ac.be", "1993-01-01");
		addPerson("Witters", "Tim", "Tim.Witters@vub.ac.be", "1993-01-01");
		addPerson("Meiresone", "Pieter", "Pieter.Meiresone@vub.ac.be", "1993-01-01");
		addPerson("Van den Vonder", "Sam", "Sam.Van.den.Vonder@vub.ac.be", "1993-01-01");
		addPerson("Gaethofs", "Christophe", "Christophe.Gaethofs@vub.ac.be", "1993-01-01");
		// addPerson("Test","Test","Nicolas.Carraggi@vub.ac.be", "test");
	}
	
	public static void fillUserTypes(){
		addUserType("Extern", 0);
		addUserType("Student", 1);
		addUserType("Assistent", 2);
		addUserType("Professor", 3);
		addUserType("Admin", 4);
	}
	
	public static void createDb(){
		String tableTestPersons = "CREATE TABLE TestPersons ("+ 
								  " PersonID int NOT NULL AUTO_INCREMENT,"+
								  " LastName varchar(255) NOT NULL,"+
								  " FirstName varchar(255) NOT NULL,"+
								  " Email varchar(255) NOT NULL UNIQUE,"+
								  " BirthDate varchar(255) NOT NULL,"+
								  " PRIMARY KEY (PersonID));";
		String tableTestUsers = "CREATE TABLE TestUsers ("+
								" UserID int NOT NULL AUTO_INCREMENT,"+
								" Username varchar(255) NOT NULL UNIQUE,"+
								" Password varchar(255) NOT NULL,"+
								" Language varchar(255) NOT NULL DEFAULT 'English',"+
								" UserTypeID int NOT NULL DEFAULT 1,"+
								" PersonID int NOT NULL UNIQUE,"+
								" PRIMARY KEY (UserID),"+
								" FOREIGN KEY (PersonID) REFERENCES TestPersons(PersonID),"+
								" FOREIGN KEY (UserTypeID) REFERENCES TestUserTypes(UserTypeID));";
		String tableTestNotRegisteredUsers = "CREATE TABLE TestNotRegisteredUsers ("+
											 " NotRegisteredUserID int NOT NULL AUTO_INCREMENT,"+
											 " Username varchar(255) NOT NULL UNIQUE,"+
											 " Password varchar(255) NOT NULL,"+
											 " PersonID int NOT NULL UNIQUE,"+
											 " PRIMARY KEY (NotRegisteredUserID),"+
											 " FOREIGN KEY (PersonID) REFERENCES TestPersons(PersonID));";
		String tableTestActivationKeys = "CREATE TABLE TestActivationKeys ("+
										 " ActivationKeyID int NOT NULL AUTO_INCREMENT,"+
										 " KeyString varchar(255) NOT NULL UNIQUE,"+
										 " CreatedOn datetime NOT NULL,"+
										 " NotRegisteredUserID int NOT NULL,"+
										 " PRIMARY KEY (ActivationKeyID),"+
										 " FOREIGN KEY (NotRegisteredUserID) REFERENCES TestNotRegisteredUsers(NotRegisteredUserID));";
		String tableTestUserTypes = "CREATE TABLE TestUserTypes ("+
				 					" UserTypeID int NOT NULL AUTO_INCREMENT,"+
				 					" UserTypeName varchar(255) NOT NULL UNIQUE,"+
				 					" Permission int NOT NULL," +
				 					" PRIMARY KEY (UserTypeID));";
		DbLink.executeSql(tableTestUserTypes);
		DbLink.executeSql(tableTestPersons);
		DbLink.executeSql(tableTestUsers);
		DbLink.executeSql(tableTestNotRegisteredUsers);
		DbLink.executeSql(tableTestActivationKeys);
	}
	
	public static void eraseDb(){	
		DbLink.executeSql("DROP TABLE TestUsers, TestActivationKeys, TestNotRegisteredUsers, TestPersons, TestUserTypes");
	}
	
	
	public static void main(String[] args) {
		DbLink.openConnection();
		
		System.out.println("\\\\ CreateDb() // \n");
		createDb();
		System.out.println("\\\\ fillUserTypes() // \n");
		fillUserTypes();
		System.out.println("\\\\ fillPersons() // \n");
		fillPersons();
		System.out.println("\\\\ showPersons() // \n");
		showPersons();
		System.out.println("\\\\ addNotRegisteredUser() // \n");
		addNotRegisteredUser("timbo", "test", 4);
		System.out.println("\\\\ addActivationKey() // \n");
		addActivationKey("key123", 1);
		System.out.println("\\\\ addUser() // \n");
		addUser("ncarragg","test",3);
		
		selectUser(3);
		selectUser(4);
		
		DbLink.closeConnection();
				
//		eraseDb();

	}
}
