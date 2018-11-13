package datacontroller;

import login.StaffUser;
import eventlog.*;
import database.Movie;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.*;

public class DataController {

	private Scanner fileScanner;
	private EventLog eventLogger;
	private StaffUser staff;

	public DataController() throws FileNotFoundException {
     	this.eventLogger = new EventLog();
     	this.staff = new StaffUser();
	}

   public void addTable(String tableName, String[] columns)
   {
   		try
   		{
   			File table = new File("resources/" + tableName + ".tsv");
      		if (!table.exists())
      		{
         		table.createNewFile();

         		String firstLine = columns[0]; //write the firstline as a row
         		for (int i = 1; i < columns.length; i++)
         		{
         			firstLine += "\t" + columns[i];
         		}

         		PrintWriter printWriter = new PrintWriter(table, "UTF-8");
         		printWriter.println(firstLine);
         		printWriter.flush();
         		printWriter.close();

         		System.out.printf("Added new table: '%s'\n", tableName);
         		eventLogger.addEvent(new Event(LocalDateTime.now(), tableName, EventType.CREATETABLE, staff.getId()));
      		}
   		}
   		catch (IOException e)
   		{
   			System.out.println(e);
   		}
   }

   public void removeTable(String tableName)
   {
   		if (staff.getId() > 0) // -1 means not logged in, a boolean would be redundant
      	{
      		File table = new File("resources/" + tableName + ".tsv");
         	if (table.exists())
         	{
            	table.delete();
            	System.out.printf("Table: '%s' has been deleted.\n", tableName);
            	eventLogger.addEvent(new Event(LocalDateTime.now(), tableName, EventType.DELETETABLE, staff.getId()));
         	}
      	}
      	else
      	{
         	System.out.println("You must be logged in to perform this task.");
      	}
   }


	/**
	 *
	 * @param index
	 */
	public void addRow(String[] columns, String tableName, boolean runCheck) {
		if (staff.getId() > 0)
		{
			if (runCheck) // running check is not needed when calling this method from updateRow
			{
				if (readTableColumns(tableName).length != columns.length)
				{
					System.out.println("\nColumn structure does not match the selected table.");
					return;
				}

				if (primaryKeyExists(columns[0], tableName))
				{
					System.out.println("\nPrimarykey already exists. Update the existing row instead.");
					return;
				}
			}

			try
			{
				File table = new File("resources/" + tableName + ".tsv");
				if (table.canWrite())
				{
					FileWriter fileWriter = new FileWriter(table, true);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      				PrintWriter printWriter = new PrintWriter(bufferedWriter);

      				printWriter.println();
      				printWriter.print(columns[0]);
      				for (int i = 1; i < columns.length; i++)
      				{
      					printWriter.print("\t" + columns[i]);
      				}

      				printWriter.flush();
      				printWriter.close();
      				bufferedWriter.close();
      				fileWriter.close();

      				System.out.printf("Row added to table: '%s'\n", tableName);
					eventLogger.addEvent(new Event(LocalDateTime.now(), tableName, EventType.CREATE, staff.getId()));
				}
				else
				{
					System.out.printf("Cannot write to table: '%s'\n", tableName);
				}
			}
			catch (IOException e)
			{
				System.out.println(e);
			}
		}
		else
		{
			System.out.println("Login is required to perform this task.");
		}
	}

	/**
	 *
	 * @param index
	 */
	public String[] readTableColumns(String tableName) //reads first row to get structure of columns
	{
		String[] columns = null;

   		try
   		{
			File table = getTable(tableName);

    		if (table != null)
    		{
    			fileScanner = new Scanner(table, "UTF-8");

    			if (fileScanner.hasNextLine())
            	{
               		columns = fileScanner.nextLine().split("\t");
            	}
    		}
    	}
    	catch (IOException e)
    	{
    		System.out.println(e);
    	}

    	return columns;
	}

	public String[] readRow(String primaryKey, String tableName) //read specific row with primary key
	{
		String[] entry = null;

   		try
   		{
			File table = getTable(tableName);

    		if (table != null)
    		{
    			fileScanner = new Scanner(table, "UTF-8");

    			boolean searchFinished = false;
    			while (!searchFinished)
    			{
    				if (fileScanner.hasNextLine())
    				{
    					entry = fileScanner.nextLine().split("\t");
    					if (primaryKey.equals(entry[0]))
    					{
    						searchFinished = true;
    					}
    				}
    				else
    				{
    					searchFinished = true;
    				}
    			}
    		}
    	}
    	catch (IOException e)
    	{
    		System.out.println(e);
    	}

    	return entry;
	}

	/**
	 *
	 * @param index
	 */
	public void updateRow(String primaryKey, String[] updatedRow, String tableName) {
		if (staff.getId() > 0)
		{
			if (readTableColumns(tableName).length != updatedRow.length)
			{
				System.out.println("Column structure does not match the selected table.");
				return;
			}

			if (!primaryKeyExists(updatedRow[0], tableName))
			{
				System.out.println("Primary key does not exist. Add new row instead.");
				return;
			}

			try
			{
				removeRow(primaryKey, tableName); // run remove to flag existing row
				addRow(updatedRow, tableName, false); // add new row as replacement

				System.out.printf("Updated row for table: '%s' with primary key: '%s'\n", tableName, primaryKey);
				eventLogger.addEvent(new Event(LocalDateTime.now(), tableName, EventType.UPDATE, staff.getId()));
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		else
		{
			System.out.println("Login is required to perform this task.");
		}
	}

	/**
	 *
	 * @param index
	 */
	public void removeRow(String primaryKey, String tableName) {
		try
		{
			File table = new File("resources/" + tableName + ".tsv");

			if (table.canWrite())
			{
				boolean rowDeleted = false;
				RandomAccessFile raFile = new RandomAccessFile(table, "rw"); // rw = read/write
				String lineRead = "";
				String[] row;
				long fileOffset = 0;

				raFile.seek(fileOffset);
				lineRead = raFile.readLine();
				while (lineRead != null && !rowDeleted)
				{
					row = lineRead.split("\t");
					if (primaryKey.equals(row[0]))
					{
						raFile.seek(fileOffset);
						raFile.writeBytes("\\N\t"); // \N to represent removed
						raFile.close(); //important in order to flush the stream

						rowDeleted = true;

						System.out.printf("\nFlagged row as removed with primary key: '%s' in table: '%s'\n", primaryKey, tableName);
						eventLogger.addEvent(new Event(LocalDateTime.now(), tableName, EventType.DELETE, staff.getId()));
					}
					else
					{
						fileOffset = raFile.getFilePointer(); // save previous offset to point back to start of line we just read
						lineRead = raFile.readLine();
					}
				}
			}
			else
			{
				System.out.printf("Cannot write to table: '%s'\n", tableName);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public boolean primaryKeyExists(String primaryKey, String tableName)
	{
		try
   		{
   			String[] row;
			File table = getTable(tableName);

    		if (table != null)
    		{
    			fileScanner = new Scanner(table, "UTF-8");

    			while (fileScanner.hasNextLine())
    			{
    				row = fileScanner.nextLine().split("\t");
    				if (primaryKey.equals(row[0]))
    				{
    					return true;
    				}
    			}
    		}
    	}
    	catch (IOException e)
    	{
    		System.out.println(e);
    	}

    	return false;
	}

	public File getTable(String tableName)
	{
		File table = null;

		table = new File("resources/" + tableName + ".tsv");

    	if (!table.canRead())
    	{
    		System.out.printf("Cannot read from table: '%s'\n", tableName);
    		table = null;
    	}

    	return table;
	}


	public EventLog getEventLogger() {
		return eventLogger;
	}
	/**
	 *
	 * @param username
	 * @param password
	 */
	public boolean login(String username, String password) {
		try
		{
			File users = new File("datacontroller/login.txt");
			if (users.canRead())
			{
				fileScanner = new Scanner(users);

				Scanner inputScanner;
				String lineRead = "";

				while (fileScanner.hasNextLine())
				{
					lineRead = fileScanner.nextLine();
					String[] userInfo = lineRead.split("\t");

					if (userInfo[4].equals(username) && userInfo[5].equals(password))
					{
						try
						{
							int id = Integer.parseInt(userInfo[0]);
							staff = new StaffUser(id, userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5], userInfo[6]);

							System.out.println("\nSuccesfully logged in!");

							return true;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Login failed. File is corrupted.");
						}
					}
				}

				System.out.println("\nLogin failed. Wrong username/password entered.");
			}
			else
			{
				System.out.println("Login failed. File cannot be read.");
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

		return false;
	}

	public boolean getIsLoggedIn() {
		return (staff.getId() > 0);
	}

	public void logOut() {
		staff = new StaffUser();
	}

}
