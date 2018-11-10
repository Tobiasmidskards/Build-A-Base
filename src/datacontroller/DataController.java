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
	private List<String> filePaths;
	private List<Movie> searchResults;
	private EventLog eventLogger;
	private StaffUser staff;
	private boolean isLoggedIn;

	public DataController() throws FileNotFoundException {
	 	  this.filePaths = new ArrayList<>();
     	this.searchResults = new ArrayList<>();
     	this.eventLogger = new EventLog();
     	this.staff = new StaffUser();
			this.isLoggedIn = false;
	}

   public void addTable(String tableName)
   {
   		try
   		{
   			File table = new File(tableName);
      		if (!table.exists())
      		{
         		table.createNewFile();
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
      		File table = new File(tableName);
         	if (table.exists())
         	{
            	table.delete();
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
	public void addLine(String line, String tableName) {
		if (staff.getId() > 0)
		{

			eventLogger.addEvent(new Event(LocalDateTime.now(), tableName, EventType.CREATE, staff.getId()));
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
	public String readLine(int index, String tableName)
	{
		String entry = "";

   		try
   		{
			File table = new File(tableName);

    		if (!table.canRead())
    		{
    			System.out.println("Cannot read from table: '" + tableName + "'");
    		}
    		else
    		{
    			fileScanner = new Scanner(table, "UTF-8");

    			for (int i = 0; i < index; i++)
    			{
            		if (fileScanner.hasNextLine())
            		{
               			entry = fileScanner.nextLine();
            		}
            		else
            		{
               			System.out.println("Line does not exist. Lines read before stopping: " + i);
               			entry = "";
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
	public void updateLine(int index, String updatedEntry, String tableName) {
		// TODO - implement DataController.updateEntry
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param index
	 */
	public void removeLine(int index) {

	}

	/**
	 *
	 * @param author
	 */
	public List<Movie> showMovieList(String author) {
		// TODO - implement DataController.showMovieList
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param year
	 */
	public List<Movie> showMovieList(LocalDateTime year) {
		// TODO - implement DataController.showMovieList
		throw new UnsupportedOperationException();
	}

	public String getTitle(String tconst) {
		String[] entry;
		String result = "";
		String[] proffesions;
		String[] movies;
		boolean match = false;

		try
		{
			File table = new File("resources/titlebasics.tsv");

			if (!table.canRead())
			{
				System.out.println("Cannot read from table: 'resources/titlebasics.tsv'");
			}
			else
			{
				fileScanner = new Scanner(table, "UTF-8");
				while(!match){
					if (fileScanner.hasNextLine())
					{
								entry = fileScanner.nextLine().split("\t");
								if (tconst.equals(entry[0])) {
									result = "("+ entry[5] + ") " + entry[2]; // ID
									match = true;
								}
					}
					else
					{
								System.out.println("Line does not exist. Lines read before stopping");
								match = true;
					}
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

		return result;
	}

	public String[] searchPerson(String name) {
		// find person in namebasics.txt
		// lookup titles from tconst.
		// Find titles in titlebasics.txt
		// show line[2] for primarytitle
		  String[] entry;
			String[] result = new String[10];
			String[] proffesions;
			String[] movies;
		  boolean match = false;
			String proffesion;
			String movie;
			int i;

   		try
   		{
			File table = new File("resources/namebasics.tsv");

    		if (!table.canRead())
    		{
    			System.out.println("Cannot read from table: 'resources/namebasics.tsv'");
    		}
    		else
    		{
    			fileScanner = new Scanner(table, "UTF-8");
					while(!match){
        		if (fileScanner.hasNextLine())
        		{
             			entry = fileScanner.nextLine().split("\t");
									if (name.equals(entry[1])) {
										result[0] = entry[0]; // ID
										result[1] = entry[1]; // PrimaryName
										result[2] = entry[2]; // Birth
										result[3] = entry[3]; // Death

										proffesions = entry[4].split(",");
										proffesion = proffesions[0];
										for(i = 1; i<proffesions.length; i++) {
											proffesion = (proffesion + ", " + proffesions[i]);
										}
										result[4] = proffesion; // Proffession

										movies = entry[5].split(",");
										movie = "- " + getTitle(movies[0]);

										for (i = 1; i<movies.length; i++) {
											movie = (movie + "\n- " + getTitle(movies[i]));
										}

										result[5] = movie; // Known for

										match = true;
									}
        		}
        		else
        		{
									match = true;
        		}
					}
    		}
    	}

    	catch (IOException e)
    	{
    		System.out.println(e);
    	}

			return result;
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
							isLoggedIn = true;

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
		return isLoggedIn;
	}

	public void logOut() {
		isLoggedIn = false;
		staff = new StaffUser();
	}

}
