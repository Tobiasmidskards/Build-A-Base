import java.util.Scanner;
import java.io.*;

public class Menu {

	private DataController dataController;
	private Scanner scanner;
	private MenuState state;
	private LoginManager loginManager;

	public Menu() throws FileNotFoundException{
    this.dataController = new DataController();
		this.scanner = new Scanner(System.in);
		loginManager = new LoginManager();
		state = MenuState.MAINMENU;
	}

	public void displayMenu() {
		switch(state) {
			case MAINMENU:
				displayMainMenu();
				break;
			case LOGIN:
				displayLoginMenu();
				break;
			case SEARCH:
				displayMainMenu();
				break;
		}
	}

	public void displayMainMenu() {
		System.out.println("*** Build A Base ***");
		System.out.println("0 : Exit");
		System.out.println("1 : Login");
		System.out.println("2 : Search");

		System.out.print("\ninput: ");
	  String input = scanner.nextLine();
		switch(input) {
			case "1":
				displayLoginMenu();
				break;
			case "2":
				displayDatabaseLookupMenu();
				break;
			default:
				System.out.println("Try again please.");
				displayMainMenu();
				break;

		}

	}

	public void displayLoginMenu() {
		System.out.println("*** Build A Base ***");
		System.out.println("*\tLogin\t*");
		System.out.print("Username: ");
	 	String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		if (loginManager.login(username, password)) {
			System.out.println("");
			state = MenuState.SEARCH;
		} else {
			System.out.println("");
			state = MenuState.LOGIN;
		}

	}

	public void displayDatabaseLookupMenu() {
		// TODO - implement Menu.displayLoginPrompt
		throw new UnsupportedOperationException();
	}

}
