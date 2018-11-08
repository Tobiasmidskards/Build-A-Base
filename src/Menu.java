import java.util.Scanner;
import java.io.*;

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
				displayDatabaseLookupMenu();;
				break;
			case EXIT:
			  	ui.clear();
				ui.exit();
				return false;
		}
		return true;
	}

	public void displayMainMenu() {
		ui.printTop();
		ui.printMainMenu();
		ui.printBot();

		System.out.print("\n-  ");
	  String input = scanner.nextLine();
		switch(input) {
			case "1":
				displayLoginMenu();
				break;
			case "2":
				ui.clear();
				displayDatabaseLookupMenu();
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

	public void displayDatabaseLookupMenu() {
		ui.printTop();
		ui.printSearchMenu();
		ui.printBot();
		System.out.print("\n- ");
		String input = scanner.nextLine();
		switch(input) {
			case "1":
			  ui.clear();
			  displayDatabaseLookupMenu();
				break;
			case "2":
				ui.clear();
        displayDatabaseLookupMenu();
				break;
			case "3":
				state = MenuState.MAINMENU;
				break;
			default:
				ui.clear();
				System.out.println("Try again please.");
				displayDatabaseLookupMenu();
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
