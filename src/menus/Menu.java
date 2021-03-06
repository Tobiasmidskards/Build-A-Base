package menus;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import datacontroller.*;
import eventlog.*;
import search.SearchController;
import database.TitleInfo;
import database.PersonInfo;

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
			case EVENTTYPE:
				displayEventTypeSelectionMenu();
				break;
			case MANAGEMENT:
				displayManagementMenu();
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
		ui.eventLogMenu();

		input = scanner.nextLine();

		switch (input)
		{
			case "1":
				displayElementsInEventList(dataController.getEventLogger().listAllEvents());
				promptEnterMessage();
				break;
			case "2":
				state = MenuState.EVENTTYPE;
				break;
			case "3":
				System.out.println("\nID to search for\n");
				ui.input();

				displayElementsInEventList(dataController.getEventLogger().listEvents(scanner.nextInt()));
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

	public void displayEventTypeSelectionMenu() {
		ui.specificEventTypeMenu();

		input = scanner.nextLine();

		switch (input)
		{
			case "1":
				displayElementsInEventList(dataController.getEventLogger().listEvents(EventType.CREATETABLE));
				break;
			case "2":
				displayElementsInEventList(dataController.getEventLogger().listEvents(EventType.DELETETABLE));
				break;
			case "3":
				displayElementsInEventList(dataController.getEventLogger().listEvents(EventType.CREATE));
				break;
			case "4":
				displayElementsInEventList(dataController.getEventLogger().listEvents(EventType.UPDATE));
				break;
			case "5":
				displayElementsInEventList(dataController.getEventLogger().listEvents(EventType.DELETE));
				break;
			case "6":
				state = MenuState.EVENTLOG;
				break;
			default:
				System.out.println("Try again please.");
				break;
		}

		promptEnterMessage();
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
				case "0":
					searchForPerson();
					break;
				case "1":
					searchForTitle(false);
					break;
				case "2":
					searchForTitle(true);
					break;

				case "3":
					state = MenuState.MANAGEMENT;
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
				case "0":
					searchForPerson();
					break;
				case "1":
					searchForTitle(false);
					break;
				case "2":
					searchForTitle(true);
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

	private void displayManagementMenu(){
		ui.ManagementMenu();

		String[] command = scanner.nextLine().split(" ");

		switch (command[0])
		{
			case "1": //create table
				if (command.length == 3) {
					dataController.addTable(command[1], command[2].split("-"));
				} else {
					ui.invalidParameter();
				}
				break;
			case "2": //delete table
				if (command.length == 2) {
					dataController.removeTable(command[1]);
				} else {
					ui.invalidParameter();
				}
				break;
			case "3": //add row
				if (command.length == 3) {
					dataController.addRow(command[1].split("-"), command[2], true);
				} else {
					ui.invalidParameter();
				}
				break;
			case "4": //read row
				if (command.length == 3) {
					String[] row = dataController.readRow(command[1], command[2]);
					if (row != null) {
						ui.printRowRead(row);
					}
				} else {
					ui.invalidParameter();
				}
				break;
			case "5": //update row
				if (command.length == 4) {
					dataController.updateRow(command[1], command[2].split("-"), command[3]);
				} else {
					ui.invalidParameter();
				}
				break;
			case "6": //delete row
				if (command.length == 3) {
					dataController.removeRow(command[1], command[2]);
				} else {
					ui.invalidParameter();
				}
				break;
			case "7": //get table structure
				if (command.length == 2) {
					String[] rowStructure = dataController.readTableColumns(command[1]);
					if (rowStructure != null) {
						ui.printRowRead(rowStructure);
					}
				} else {
					ui.invalidParameter();
				}
				break;
			case "8":
				searchController.createIndexTable("movie", 1, "titlebasics", 2);
				break;
			case "9":
				state = MenuState.SEARCH;
				break;
			default:
				System.out.println("Try again please.");
				break;

		}

		if (!command[0].equals("9")) {
			promptEnterMessage();
		}
	}

	private void searchForPerson() {
		System.out.println("\nPlease type in the name you want to search for.");
		ui.input();

		input = scanner.nextLine();
		System.out.println("\nReading from database.. This might take a while...");

		List<PersonInfo> persons = searchController.searchPerson(input);

		System.out.println("\nYou have searched for: " + input);

		if (persons.size() == 0)
		{
			System.out.println("No person were found.");
		}
		else
		{
			System.out.println(persons.size() + " person(s) found.");

			for (PersonInfo p : persons)
			{
				System.out.printf("\nnconst:\t\t\t%s\nPerson:\t\t\t%s\nBirth:\t\t\t%s\nDeath:\t\t\t%s\nProfession:\t\t%s\n\nKnown for titles:\n%s\n",
												 p.getNconst(), p.getName(), p.getBirth(), p.getDeath(), p.getProfession(), p.getTitles());
			}
		}


		promptEnterMessage();
	}

	private void searchForTitle(boolean useIndexTable) {
		System.out.println("\nPlease type in the title you want to search for.");
		ui.input();

		input = scanner.nextLine();

		System.out.println("\nReading from database.. This might take a while...");

		List<TitleInfo> movies = searchController.searchTitle(input, useIndexTable);

		System.out.println("\nYou have searched for: " + input);

		if (movies.size() == 0) {
			System.out.println("No titles were found.");
		}
		else
		{
			System.out.println(movies.size() + " title(s) found.");

			for (TitleInfo m : movies)
			{
				System.out.printf("\ntconst:\t\t\t%s\nTitle type:\t\t\t%s\nPrimary Title:\t\t\t%s\nOriginal Title:\t\t\t%s\nIs adult:\t\t\t%s\nYear:\t\t\t\t%s\nRuntime:\t\t\t%s\nGenres:\t\t\t\t%s\nRating:\t\t\t\t%s\nVotes:\t\t\t\t%s\n",
												m.getTconst(), m.getTitleType(), m.getPrimaryTitle(), m.getOriginalTitle(), m.getIsAdult(), m.getStartYear(), m.getRuntime(), m.getGenres(), m.getRating(), m.getVotes());
			}
		}


		promptEnterMessage();
	}

	private void displayElementsInEventList(List<Event> events)
	{
		if (events.size() > 0)
		{
		System.out.println("");
			for (Event event : events)
			{
				System.out.println(event);
			}
		}
		else
		{
			System.out.println("\nNo events to display.");
		}
	}

	private void promptEnterMessage()
	{
			scanner = new Scanner(System.in);
			System.out.print("\nEnter anything to continue..");
			scanner.nextLine();
	}
}
