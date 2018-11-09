package menus;

import java.util.Scanner;
import java.io.*;
import database.*;
import datacontroller.*;

public class Menu {

	private DataController dataController;
	private Scanner scanner;
	private MenuState state;
	private UI ui;

	public Menu() throws FileNotFoundException{
    this.dataController = new DataController();
		this.scanner = new Scanner(System.in);
		this.state = MenuState.MAINMENU;
		this.ui = new UI();
	}

	public boolean displayMenu() {
		ui.clear();
		switch(state) {
			case MAINMENU:
				displayMainMenu();
				break;
			case LOGIN:
				displayLoginMenu();
				break;
			case SEARCH:
				displayAdminSearch();
				break;
			case EXIT:
			  ui.clear();
				ui.exit();
				return false;
		}
		return true;
	}

	public void displayLogoutMenu() {
		dataController.logOut();
		ui.logout();
		promptEnterMessage();
	}

	public void displayMainMenu() {
		ui.printTop();
		ui.printMainMenu(dataController.getIsLoggedIn());
		ui.printBot();

		System.out.print("\n-  ");
	  String input = scanner.nextLine();
		switch(input) {
			case "1":
				if (dataController.getIsLoggedIn()) {
					displayLogoutMenu();
				} else {
					displayLoginMenu();
				}

				break;
			case "2":
				ui.clear();
				displayAdminSearch();
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

	public void displayLoginMenu() {
		ui.login();
		System.out.print("Username: ");
	 	String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		if (dataController.login(username, password)) {
			promptEnterMessage();
			state = MenuState.SEARCH;
		}
		else {
			ui.failedLogin();
			String input = scanner.nextLine();

			if (input.equals("y")) {
				state = MenuState.LOGIN;
			} else {
				state = MenuState.MAINMENU;
			}

		}
	}

	public void displayAdminSearch() {
		if (dataController.getIsLoggedIn()){
			displayDatabaseLookupMenuAdmin();
		} else {
			displayDatabaseLookupMenu();
		}
	}

	public void displayDatabaseLookupMenu() {
		ui.printTop();
		ui.printSearchMenu();
		ui.printBot();
		System.out.print("\n- ");
		String input = scanner.nextLine();
		switch(input) {
			case "1":
			  ui.clear();
			  displayAdminSearch();
				break;
			case "2":
				ui.clear();
        System.out.println(dataController.readLine(3, "resources/namebasics.tsv"));
				break;
			case "3":
				state = MenuState.MAINMENU;
				break;
			default:
				ui.clear();
				System.out.println("Try again please.");
				displayAdminSearch();
				break;
		}
	}

	public void displayDatabaseLookupMenuAdmin() {
			ui.printTop();
			ui.printSearchMenuAdmin();
			ui.printBot();
			System.out.print("\n- ");
			String input = scanner.nextLine();
			switch(input) {
				case "1":
					ui.clear();
					displayAdminSearch();
					break;
				case "2":
					ui.clear();
					displayAdminSearch();
					break;
				case "3":
					ui.clear();
					displayAdminSearch();
					break;
				case "4":
					state = MenuState.MAINMENU;
					break;
				default:
					ui.clear();
					System.out.println("Try again please.");
					displayAdminSearch();
					break;
			}

	}

	private void promptEnterMessage()
	{
		  ui.printBot();
			System.out.print("\nEnter anything to continue..");
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();

	}

}
