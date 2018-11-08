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

     	//fileScanner.useDelimiter("\t");
	}

   public void addTable(String tableName)
      throws IOException
   {
      File table = new File(tableName);
      if (!table.exists())
      {
         table.createNewFile();
      }
   }

   public void removeTable(String tableName)
      throws IOException
   {
      if (!isLoggedIn)
      {
         System.out.println("You must be logged in to perform this task.");
      }
      else
      {
         File table = new File(tableName);
         if (table.exists())
         {
            table.delete();
         }
      }
   }

	/**
	 *
	 * @param index
	 */
	public void addLine(String line, String tableName) {

	}

	/**
	 *
	 * @param index
	 */
	public String readLine(int index, String tableName)
      throws FileNotFoundException
   {
     	String entry = "";
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
		// TODO - implement DataController.removeEntry
		throw new UnsupportedOperationException();
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
	 * @param date
	 */
	public List<Movie> showMovieList(LocalDateTime date) {
		// TODO - implement DataController.showMovieList
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		// TODO - implement DataController.login
		throw new UnsupportedOperationException();
	}

}
