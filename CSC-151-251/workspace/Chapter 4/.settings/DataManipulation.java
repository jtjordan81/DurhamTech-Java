import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Jason Jordan
 * 
 * CSC 251
 * 
 * Data Manipulation
 */

public class DataManipulation {

	public static void main(String[] args) {
		
		final String DB_URL = "jdbc:derby:DTCC;create=true";
		
		try
		{
			// Create connection to the database.
			Connection conn = DriverManager.getConnection(DB_URL);
			System.out.println("Connection successful");
			
			// If the DB already exists, drop the tables.
			dropTables(conn);

			// Build the DTCC table and insert records.
			buildDTCCTable(conn);
			
			// view data
			viewDTCCTable(conn);
						
			// modify record
			modDTCCTable(conn);
						
			// view modified data
			viewDTCCTable(conn);

			// Close the connection.
			conn.close();
			System.out.println("Connection closed");
			
			//System.out.println("DB creation success!");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}

	} // end main

	
	public static void dropTables(Connection conn)
	{
		System.out.println("Checking for existing table.");

		try
		{
			// Get a Statement object.
			Statement stmt  = conn.createStatement();;

			try
			{
				// Drop the Coffee table.
				stmt.execute("DROP TABLE DTCC");
				System.out.println("DTCC deleted.");
			}
			catch(SQLException ex)
			{
				
			}
		}
		catch(SQLException ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}
	} // end dropTables
	
	public static void buildDTCCTable(Connection conn)
	{
		try
		{
			// Get a Statement object.
			Statement stmt = conn.createStatement();

			// Create the table.
			stmt.execute("CREATE TABLE DTCC (Student_ID CHAR(10) NOT NULL PRIMARY KEY, LastName CHAR(20), FirstName CHAR(15), PlanOfStudy CHAR(20), GPA DOUBLE)");
			
			System.out.println("DTCC table created");

			// Insert row #1. 
			stmt.execute("INSERT INTO DTCC VALUES ('899090111' , 'Rothlisberger' , 'Ben' , 'CIT' , 3.7)");
				

			// Insert row #2. 
			stmt.execute("INSERT INTO DTCC VALUES ('129020202' , 'Manning' , 'Peyton' , 'Computer Programming' , 3.8)");
					

			// Insert row #3.
			stmt.execute("INSERT INTO DTCC VALUES ('890101030' , 'Brady' , 'Tom' , 'Accounting' , 3.4)");

			// Insert row #4.
			stmt.execute("INSERT INTO DTCC VALUES ('980191919' , 'Rodgers' , 'Aaron' , 'Networking' , 3.2)");
						
			// Insert row #5.
			stmt.execute("INSERT INTO DTCC VALUES ('807223230' , 'Manning' , 'Eli' , 'Securities' , 3.7)");

			System.out.println("DTCC table filled.");
		}
		catch (SQLException ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	} // end buildDTCCTable

	public static void viewDTCCTable(Connection conn)
	{
		//create var for result set
		ResultSet resultset = null;

		try
		{
			// Get a Statement object.
			Statement stmt = conn.createStatement();

			// View the table.
			resultset = stmt.executeQuery("SELECT * FROM DTCC");

			// process results
			ResultSetMetaData metaData = resultset.getMetaData();
			
			System.out.println("Data from DTCC Table:");

			int numberOfColumns = metaData.getColumnCount();
			// for loop to field names
			for (int i = 1; i <= numberOfColumns; i++){
				System.out.printf("%s\t", metaData.getColumnName(i));
			}
			System.out.println();
			
			// while loop to display data
			while (resultset.next()){
				for (int i = 1; i <= numberOfColumns; i++){
					System.out.printf("%s\t", resultset.getObject(i));
				}
				System.out.println();
			}

		}
		catch (SQLException ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	} // end viewDTCCTable

	
	public static void modDTCCTable(Connection conn)
	{
		//create var for result set
		ResultSet resultset = null;

		try
		{
			// Get a Statement object.
			Statement stmt = conn.createStatement();

			// update record
			stmt.executeUpdate("UPDATE DTCC SET PlanOfStudy = 'Web Technologies' WHERE Student_ID = '890101030'");
			stmt.executeUpdate("UPDATE DTCC SET FirstName = 'Ellis', GPA = 4.0 WHERE Student_ID = '807223230'");
			stmt.executeUpdate("DELETE FROM DTCC WHERE STUDENT_ID = '899090111'");
			System.out.println("Record updated");

		}
		catch (SQLException ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	} // end modDTCCTable
	
	
} // end class