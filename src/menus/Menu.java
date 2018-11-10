package menus;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import datacontroller.DataController;
import eventlog.Event;

public class Menu {

	private DataController dataController;
	private Scanner scanner;
	private MenuState state;
	private UI ui;
	private boolean loginStatus;
	private String input;

	public Menu() throws FileNotFoundException{
    this.dataController = new DataController();
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

				break;
			case "4":
				state = MenuState.MAINMENU;
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
				case "1":
					
					break;

				case "2":

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
					break;

				case "2":
					// test
	        System.out.println(dataController.readLine(3, "resources/namebasics.tsv"));
					promptEnterMessage();
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
