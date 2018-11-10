package menus;

import java.util.Scanner;
import java.io.*;
import datacontroller.DataController;

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
				displayLoginMenu();
				break;

			case "2":
				ui.clear();
				displayDBLookupMenu();
				break;

			case "3":
			 	state = MenuState.EXIT;
				break;

			default:
			  	ui.clear();
				System.out.println("Try again please.");
				displayMainMenu();
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
					displayDBLookupMenu();
					break;

				case "2":
					displayDBLookupMenu();
					break;

				case "3":
					displayDBLookupMenu();
					break;

				case "4":
					state = MenuState.MAINMENU;
					break;

				default:
					System.out.println("Try again please.");
					displayDBLookupMenu();
					break;
			}
		}
		else
		{
			// if not logged in.
			switch(input)
			{
				case "1":
				  displayDBLookupMenu();
					break;

				case "2":
					// test
	        System.out.println(dataController.readLine(3, "resources/namebasics.tsv"));
					promptEnterMessage();
					displayDBLookupMenu();
					break;

				case "3":
					state = MenuState.MAINMENU;
					break;
					
				default:
					System.out.println("Try again please.");
					displayDBLookupMenu();
					break;
			}
		}
	}

	private void promptEnterMessage()
	{
			System.out.print("\nEnter anything to continue..");
			scanner.nextLine();
	}
}
