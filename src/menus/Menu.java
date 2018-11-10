package menus;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import datacontroller.DataController;
import eventlog.Event;
import search.SearchController;
import database.Movie;

public class Menu {

	private DataController dataController;
	private SearchController searchController;
	private Scanner scanner;
	private MenuState state;
	private UI ui;
	private boolean loginStatus;
	private String input;

	public Menu() throws FileNotFoundException{
    	this.dataController = new DataController();
    	this.searchController = new SearchController();
		this.scanner = new Scanner(System.in);
		this.ui = new UI();
		this.state = MenuState.MAINMENU;
	}

	public boolean displayMenu()
	{
		ui.clear();
		loginStatus = dataController.getIsLoggedIn();

		switch(state)
		{
			case MAINMENU:
				displayMainMenu();
				break;
			case LOGIN:
				displayLoginMenu();
				break;
			case SEARCH:
				displayDBLookupMenu();
				break;
			case EVENTLOG:
				displayEventLogMenu();
				break;
			case EXIT:
			  ui.clear();
				ui.exit();
				return false;
		}
		return true;
	}

	public void displayMainMenu()
	{
		ui.MainMenu(loginStatus);
	  	input = scanner.nextLine();
		switch(input)
		{
			case "1":
				state = MenuState.LOGIN;
				break;

			case "2":
				state = MenuState.SEARCH;
				break;

			case "3":
			 	state = MenuState.EXIT;
				break;

			default:
				System.out.println("Try again please.");
				break;
		}

	}

	public void displayLoginMenu()
	{
		if (loginStatus)
		{
			dataController.logOut();
			ui.logout();
			promptEnterMessage();
			state = MenuState.MAINMENU;
		}
		else
		{
			ui.login();
			System.out.print("Username: ");
		 	String username = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();

			if (dataController.login(username, password)) {
				ui.Bot();
				promptEnterMessage();
				state = MenuState.SEARCH;
			}
			else
			{
				ui.failedLogin();
				input = scanner.nextLine();

				if (input.equals("y"))
				{
					state = MenuState.LOGIN;
				}
				else
				{
					state = MenuState.MAINMENU;
				}

			}
		}
	}

	public void displayEventLogMenu() {
		ui.EventLogMenu();

		input = scanner.nextLine();

		switch (input)
		{
			case "1":
				displayElementsInEventList(dataController.getEventLogger().listAllEvents());
				promptEnterMessage();
				break;
			case "2":

				break;
			case "3":
				System.out.println("ID to search for");
				ui.input();

				while (scanner.hasNextInt())
				{
					displayElementsInEventList(dataController.getEventLogger().listEvents(scanner.nextInt()));
					break;
				}
				promptEnterMessage();
				break;
			case "4":
				state = MenuState.SEARCH;
				break;
			default:
				System.out.println("Try again please.");
				break;
		}

	}

	public void displayDBLookupMenu() {
		ui.clear();
		ui.SearchMenu(loginStatus);

		input = scanner.nextLine();

		if (loginStatus)
		{
			// if logged in.
			switch(input)
			{
				case "1":
					searchForPerson();
					break;

				case "2":
					searchForTitle();
					break;

				case "3":
					break;

				case "4":
					state = MenuState.EVENTLOG;
					break;

				case "5":
					state = MenuState.MAINMENU;
					break;

				default:
					System.out.println("Try again please.");
					break;
			}
		}
		else
		{
			// if not logged in.
			switch(input)
			{
				case "1":
					searchForPerson();
					break;
				case "2":
					searchForTitle();
					break;

				case "3":
					state = MenuState.MAINMENU;
					break;

				default:
					System.out.println("Try again please.");
					break;
			}
		}
	}

	private void searchForPerson() {
		System.out.println("\nPlease type in the name you want to search for.");
		ui.input();
		input = scanner.nextLine();
		System.out.println("\nReading from database.. This might take a while...");
		String[] lines = searchController.searchPerson(input);
		System.out.println("\nYou have searched for: " + input);

		if (lines[0] == null) {
			System.out.println("Person was not found.");
		}
		else
		{
			System.out.printf("\nPerson:\t\t\t%s\nBirth:\t\t\t%s\nDeath:\t\t\t%s\nProffession:\t%s\n\nKnown for titles:\n%s\n",
												lines[1], lines[2], lines[3], lines[4], lines[5]);
		}


		promptEnterMessage();
	}

	private void searchForTitle() {
		System.out.println("\nPlease type in the title you want to search for.");
		ui.input();
		input = scanner.nextLine();
		System.out.println("\nReading from database.. This might take a while...");
		List<Movie> movies = searchController.searchTitle(input);
		System.out.println("\nYou have searched for: " + input);

		if (movies.size() == 0) {
			System.out.println("No titles were found.");
		}
		else
		{
			System.out.println(movies.size() + " title(s) found.");

			for (Movie m : movies)
			{
				System.out.printf("\ntconst:\t\t\t\t%s\nTitle type:\t\t\t%s\nPrimary Title:\t\t%s\nOriginal Title:\t%s\nIs adult:\t\t\t%s\nStart year:\t\t\t%s\nEnd year:\t\t\t%s\nRuntime:\t\t\t\t%s minutes\nGenres:\t\t\t\t%s\nRating:\t\t\t\t%s\nVotes:\t\t\t\t%s\n",
												m.getTconst(), m.getTitleType(), m.getPrimaryTitle(), m.getOriginalTitle(), m.getIsAdult(), m.getStartYear(), m.getEndYear(), m.getRuntime(), m.getGenres(), m.getRating(), m.getVotes());
			}
		}


		promptEnterMessage();
	}

	private void displayElementsInEventList(List<Event> events)
	{
		if (events.size() > 0)
		{
			for (Event event : events)
			{
				System.out.println(event);
			}
		}
		else
		{
			System.out.println("No events to display.");
		}
	}

	private void promptEnterMessage()
	{
			System.out.print("\nEnter anything to continue..");
			scanner.nextLine();
	}
}
