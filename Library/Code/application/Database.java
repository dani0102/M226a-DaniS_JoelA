package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import add.AddNewLoan;
import add.AddNewReturn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import tables.TableAllBooks;
import tables.TableAllDVD;
import tables.TableAllFree;
import tables.TableAllLoaned;
import tables.TableAllMedias;

//Alle verbindungen mit Datenbank sowie queries

public class Database {

static Connection con = null;
static String db = "jdbc:mysql://127.0.0.1/library";
static String dbuname = "root";
static String dbpw = "";
	
	//kontrollieren ob login stimmt
	public static boolean checkLogin(String username, String password) {
		
		boolean result = true;
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			String sqlQuery = "Select * from User where Username = '" + username + "' and Password = '" + password + "';";
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			if (!rs.isBeforeFirst()) {
				result = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return result;
		
	}
	
	//neuen user erstellen
	static void inserNewUser(String username, String password) {

		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			//checking if user with that name already exists
			String userQuery = "Select Username from user where Username = '" + username + "';";
			ResultSet rs = stmt.executeQuery(userQuery);
			
			if (!rs.isBeforeFirst()) {
				
				String sqlQuery = "Insert into user(Username, Password) values('" + username + "', '" + password + "');";
				stmt.executeUpdate(sqlQuery);
	    		Login.login();   
				
			} else {
				AlertBox.alertBox("This username is already taken");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//Tabelle
	public static void selectAllMedias() {
	
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			//Abfrage für die Anzahl Rows
			String rowQuery = "select count('Title')\r\n" + 
					"from (\r\n" + 
					"SELECT count(m.Titel) as 'Title'\r\n" + 
					"FROM medien m\r\n" + 
					"left join loans l\r\n" + 
					"on l.FK_Medien = m.ID_Medien\r\n" + 
					"join sort s\r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"left join user u\r\n" + 
					"on l.FK_User = u.ID_User\r\n" + 
					"group by m.Titel\r\n" + 
					"order by l.lendDate\r\n" + 
					") MyTable\r\n" + 
					"group by 'Title';";
			
			ResultSet rsRows = stmt.executeQuery(rowQuery);
			rsRows.next();
			int rowCount = rsRows.getInt(1);
			
			String sqlQuery = "SELECT s.Name as 'Sort', m.Titel as 'Title',\r\n" + 
					"	case\r\n" + 
					"	when u.Username is null\r\n" + 
					"		then '-'\r\n" + 
					"	when m.available = 0\r\n" + 
					"		then u.Username\r\n" + 
					"	when m.available = 1\r\n" + 
					"		then '-'\r\n" + 
					"	end as 'Username',\r\n" + 
					"	case \r\n" + 
					"	when l.lendDate is null\r\n" + 
					"		then '-'\r\n" + 
					"	when m.available = 0\r\n" + 
					"		then l.lendDate\r\n" + 
					"	when m.available = 1\r\n" + 
					"		then '-'\r\n" + 
					"	end as 'Lend Date',\r\n" + 
					"	case \r\n" + 
					"	when m.available = 0\r\n" + 
					"		then 'loaned'\r\n" + 
					"	when m.available = 1\r\n" + 
					"		then 'free'\r\n" + 
					"	end as 'Available'\r\n" + 
					"FROM medien m\r\n" + 
					"left join loans l\r\n" + 
					"on l.FK_Medien = m.ID_Medien\r\n" + 
					"join sort s\r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"left join user u\r\n" + 
					"on l.FK_User = u.ID_User\r\n" + 
					"group by m.Titel\r\n" + 
					"order by m.ID_Medien;";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			//2d String für alle Resultate
			String[][] data = new String[rowCount][5];
			int i = 0;
			
			while(rs.next()) {
				
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				data[i][3] = rs.getString(4);
				data[i][4] = rs.getString(5);
				
				i++;
				
			}
			
			//mit Funktion erhält sowie jede Spalte eine Nummer zugewiesen
			//nötig um dann die Daten vom data[][] einzuteilen
			tableCells(TableAllMedias.sort, 0);
			tableCells(TableAllMedias.title, 1);
			tableCells(TableAllMedias.username, 2);
			tableCells(TableAllMedias.lendDate, 3);
			tableCells(TableAllMedias.available, 4);
	        
			//Tabelle leeren
			TableAllMedias.table.getItems().clear();
			//data[][] der Tabelle hinzufügen
	        TableAllMedias.table.getItems().addAll(Arrays.asList(data));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Tabelle
	public static void selectAllLoaned() {
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			//Abfrage für die Anzahl Rows
			String rowQuery = "select count('titles')\r\n" + 
					"from\r\n" + 
					"(\r\n" + 
					"select count(m.Titel) as 'titles'\r\n" + 
					"FROM sort s\r\n" + 
					"join medien m \r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"join loans l\r\n" + 
					"on l.FK_Medien = m.ID_Medien\r\n" + 
					"join user u\r\n" + 
					"on u.ID_User = l.FK_User\r\n" + 
					"where m.available = 0\r\n" + 
					"group by m.Titel\r\n" + 
					") MyTable;";
			
			ResultSet rsRows = stmt.executeQuery(rowQuery);
			rsRows.next();
			int rowCount = rsRows.getInt(1);
			
			String sqlQuery = "SELECT s.Name as 'Sort', m.Titel as 'Title' , m.Author, u.Username, l.lendDate as 'Lend date'\r\n" + 
					"FROM sort s\r\n" + 
					"join medien m\r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"join loans l\r\n" + 
					"on l.FK_Medien = m.ID_Medien\r\n" + 
					"join user u\r\n" + 
					"on u.ID_User = l.FK_User\r\n" +
					"where m.available = 0 "
					+ "group by m.Titel;";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			//2d String für alle Resultate
			String[][] data = new String[rowCount][5];
			int i = 0;
			
			while(rs.next()) {

				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				data[i][3] = rs.getString(4);
				data[i][4] = rs.getString(5);
				
				i++;
				
			}
			
			//mit Funktion erhält sowie jede Spalte eine Nummer zugewiesen
			//nötig um dann die Daten vom data[][] einzuteilen
			tableCells(TableAllLoaned.sort, 0);
			tableCells(TableAllLoaned.title, 1);
			tableCells(TableAllLoaned.author, 2);
			tableCells(TableAllLoaned.user, 3);
			tableCells(TableAllLoaned.lendDate, 4);
			
			//Tabelle leeren
			TableAllLoaned.table.getItems().clear();
			//data[][] der Tabelle hinzufügen
			TableAllLoaned.table.getItems().addAll(Arrays.asList(data));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	//Tabelle
	public static void selectAllFree() {
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			//Abfrage für die Anzahl Rows
			String rowQuery = "SELECT count(*) as 'rowcount'\r\n" + 
					"FROM sort s\r\n" + 
					"join medien m\r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"where m.available = 1;";
			
			ResultSet rowRs = stmt.executeQuery(rowQuery);
			rowRs.next();
			int rowCount = rowRs.getInt(1);
			
			String sqlQuery = "SELECT s.Name as 'Sort', m.Titel as 'Title' , m.Author\r\n" + 
					"FROM sort s\r\n" + 
					"join medien m\r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"where m.available = 1;";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			//2d String für alle Resultate
			String[][] data = new String[rowCount][3];
			int i = 0;
			
			while(rs.next()) {
				
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				
				i++;
			}
			
			//mit Funktion erhält sowie jede Spalte eine Nummer zugewiesen
			//nötig um dann die Daten vom data[][] einzuteilen
			tableCells(TableAllFree.sort, 0);
			tableCells(TableAllFree.title, 1);
			tableCells(TableAllFree.author, 2);
			
			//Tabelle leeren
			TableAllFree.table.getItems().clear();
			//data[][] der Tabelle hinzufügen
			TableAllFree.table.getItems().addAll(Arrays.asList(data));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//Tabelle
	public static void selectAllDVD() {
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			//Abfrage für die Anzahl Rows
			String rowQuery = "select count('titles')\r\n" + 
					"from\r\n" + 
					"(\r\n" + 
					"	select count(m.Titel) as 'titles'\r\n" + 
					"	FROM sort s\r\n" + 
					"	join medien m\r\n" + 
					"	on m.FK_Sort = s.ID_Sort\r\n" + 
					"	left join loans l\r\n" + 
					"	on l.FK_Medien = m.ID_Medien\r\n" + 
					"	where s.Name = 'DVD'\r\n" + 
					"	group by m.Titel\r\n" + 
					") MyTable\r\n" + 
					"group by 'titles';";
			
			ResultSet rowRs = stmt.executeQuery(rowQuery);
			rowRs.next();
			int rowCount = rowRs.getInt(1);
			
			String sqlQuery = "SELECT m.Titel as 'Title' , m.Author, \r\n" + 
					"		case \r\n" + 
					"		when m.available = 0\r\n" + 
					"			then 'loaned'\r\n" + 
					"		when m.available = 1\r\n" + 
					"			then 'free'\r\n" + 
					"		end as 'Available', \r\n" + 
					"        case\r\n" + 
					"		when l.lendDate is null\r\n" + 
					"			then '-'\r\n" + 
					"		else \r\n" + 
					"			l.lendDate\r\n" + 
					"		end as 'Lend Date',\r\n" + 
					"        case\r\n" + 
					"		when l.returnDate is null\r\n" + 
					"			then '-'\r\n" + 
					"		else \r\n" + 
					"			l.returnDate\r\n" + 
					"		end as 'Return Date'\r\n" + 
					"FROM sort s\r\n" + 
					"join medien m\r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"left join loans l\r\n" + 
					"on l.FK_Medien = m.ID_Medien\r\n" + 
					"where s.Name = 'DVD'"
					+ "group by m.Titel;";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			//2d String für alle Resultate
			String[][] data = new String[rowCount][5];
			int i = 0;
			
			while(rs.next()) {
				
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				data[i][3] = rs.getString(4);
				data[i][4] = rs.getString(5);
				
				i++;
			}
			
			//mit Funktion erhält sowie jede Spalte eine Nummer zugewiesen
			//nötig um dann die Daten vom data[][] einzuteilen
			tableCells(TableAllDVD.title, 0);
			tableCells(TableAllDVD.author, 1);
			tableCells(TableAllDVD.available, 2);
			tableCells(TableAllDVD.lendDate, 3);
			tableCells(TableAllDVD.returnDate, 4);
			
			//Tabelle leeren
			TableAllDVD.table.getItems().clear();
			//data[][] der Tabelle hinzufügen
			TableAllDVD.table.getItems().addAll(Arrays.asList(data));
									
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//Tabelle
	public static void selectAllBooks() {
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			//Abfrage für die Anzahl Rows
			String rowQuery = "select count('titles')\r\n" + 
					"from\r\n" + 
					"(\r\n" + 
					"	select count(m.Titel) as 'titles'\r\n" + 
					"	FROM sort s\r\n" + 
					"	join medien m\r\n" + 
					"	on m.FK_Sort = s.ID_Sort\r\n" + 
					"	left join loans l\r\n" + 
					"	on l.FK_Medien = m.ID_Medien\r\n" + 
					"	where s.Name = 'Book'\r\n" + 
					"	group by m.Titel\r\n" + 
					") MyTable\r\n" + 
					"group by 'titles';";
			
			ResultSet rowRs = stmt.executeQuery(rowQuery);
			rowRs.next();
			int rowCount = rowRs.getInt(1);
			
			String sqlQuery = "SELECT m.Titel as 'Title' , m.Author, \r\n" + 
					"		case \r\n" + 
					"		when m.available = 0\r\n" + 
					"			then 'loaned'\r\n" + 
					"		when m.available = 1\r\n" + 
					"			then 'free'\r\n" + 
					"		end as 'Available', \r\n" + 
					"        case\r\n" + 
					"		when l.lendDate is null\r\n" + 
					"			then '-'\r\n" + 
					"		else \r\n" + 
					"			l.lendDate\r\n" + 
					"		end as 'Lend Date',\r\n" + 
					"        case\r\n" + 
					"		when l.returnDate is null\r\n" + 
					"			then '-'\r\n" + 
					"		else \r\n" + 
					"			l.returnDate\r\n" + 
					"		end as 'Return Date'\r\n" + 
					"FROM sort s\r\n" + 
					"join medien m\r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"left join loans l\r\n" + 
					"on l.FK_Medien = m.ID_Medien\r\n" + 
					"where s.Name = 'Book'" + 
					"group by m.Titel;";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			//2d String für alle Resultate
			String[][] data = new String[rowCount][5];
			int i = 0;
			
			while(rs.next()) {
				
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				data[i][3] = rs.getString(4);
				data[i][4] = rs.getString(5);
				
				i++;
			}
			
			//mit Funktion erhält sowie jede Spalte eine Nummer zugewiesen
			//nötig um dann die Daten vom data[][] einzuteilen
			tableCells(TableAllBooks.title, 0);
			tableCells(TableAllBooks.author, 1);
			tableCells(TableAllBooks.available, 2);
			tableCells(TableAllBooks.lendDate, 3);
			tableCells(TableAllBooks.returnDate, 4);
			
			//Tabelle leeren
			TableAllBooks.table.getItems().clear();
			//data[][] der Tabelle hinzufügen
			TableAllBooks.table.getItems().addAll(Arrays.asList(data));
									
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//Für ComboBox alle freien/verfügbaren
	public static void selectAllLoanable(String media) {
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			String sqlQuery = "Select m.Titel\r\n" + 
					"from medien m\r\n" + 
					"join sort s\r\n" + 
					"on m.FK_Sort = s.ID_Sort\r\n" + 
					"where m.available = 1\r\n" + 
					"and s.Name = '" + media + "';";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			while(rs.next()) {

				AddNewLoan.titleoptions.add(rs.getString(1));
				
			}
									
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//ID_User anhand von username erhalten
	public static int userID(String username) {
		
		int result = 0;
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			String sqlQuery = "Select ID_User \r\n" + 
					"from user\r\n" + 
					"where Username = '" + username + "';";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			rs.next();
			result = rs.getInt(1);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	//ID_Medien anhand von Titel erhalten
	public static int mediaID(String media) {
		
		int result = 0;
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			String sqlQuery = "SELECT ID_Medien\r\n" + 
					"FROM library.medien\r\n" + 
					"where Titel = '" + media + "';";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			rs.next();
			
			result = rs.getInt(1);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	//ID_Sort anhand von Sorte erhalten
	public static int sortID(String sort) {
		
		int result = 0;
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			String sqlQuery = "SELECT ID_Sort\r\n" + 
					"FROM library.sort\r\n" + 
					"where Name = '" + sort + "';";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			rs.next();
			
			result = rs.getInt(1);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	//Medium ausleihen
	public static boolean addLoan(int userID, int mediaID) {
		
		boolean result = true;
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			String sqlQuery = "insert into loans(lendDate, FK_Medien, FK_User) values(NOW(), " + mediaID + ", " + userID + ");";
			stmt.executeUpdate(sqlQuery);
			
			String sqlQuery2 = "update medien \r\n" + 
					"set available = 0\r\n" + 
					"where ID_Medien = " + mediaID + ";";
			
			stmt.executeUpdate(sqlQuery2);
						
		} catch (SQLException e) {
			result = false;
		}
		
		return result;
		
	}
	
	//Alle ausgeliehenen Medien von eingelogter User erhalten
	public static void selectAllLoanedByUser(int userID) {
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			String sqlQuery = "select m.Titel\r\n" + 
					"from medien m\r\n" + 
					"join loans l\r\n" + 
					"on l.FK_Medien = m.ID_Medien\r\n" + 
					"where m.available = 0\r\n" + 
					"and l.FK_User = " + userID + ";";
			
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			while(rs.next()) {

				//Resultate für ComboBox zurückgeben
				AddNewReturn.titleoptions.add(rs.getString(1));
				
			}
									
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Medium zurückbringen
	public static boolean addReturn(int userID, int mediaID) {
		
		boolean result = true;
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			String sqlQuery = "update loans \r\n" + 
					"set returnDate = NOW()\r\n" + 
					"where FK_Medien = " + mediaID + "\r\n" + 
					"and FK_User = " + userID + ";";
			stmt.executeUpdate(sqlQuery);
			
			String sqlQuery2 = "update medien\r\n" + 
					"set available = 1\r\n" + 
					"where ID_Medien = " + mediaID + ";";
			
			stmt.executeUpdate(sqlQuery2);
						
		} catch (SQLException e) {
			result = false;
		}
		
		return result;
			
	}
	
	//Medium hinzufügen
	public static boolean addMedia(String title, String author, int sortID) {
		
		boolean result = true;
		
		try {
			
			con = DriverManager.getConnection(db, dbuname, dbpw);
			Statement stmt = con.createStatement();
			
			String sqlQuery = "insert into medien(Titel, available, Author, FK_Sort) \r\n" + 
					"values('" + title + "', 1, '" + author + "', " + sortID + ");";
			
			stmt.executeUpdate(sqlQuery);
			
		} catch (SQLException e) {
			result = false;
		}
		
		return result;
		
	}
	
	//Bekommenen Daten in Tabellenzellen hinzufügen
	private static void tableCells(TableColumn column, int number) {
		
		column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>(){
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p){
				String[] x = p.getValue();
				if (x != null && x.length > number) {
					return new SimpleStringProperty(x[number]);
				} else {
					return new SimpleStringProperty("-");
				}
			}
			
		});
		
	}
	
}
