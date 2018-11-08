import java.util.Scanner;
import java.io.*;

public class Menu {

	private DataController dataController;
	private Scanner scanner;
	private MenuState state;
	private LoginManager loginManager;
	private UI ui = new UI();

	public Menu() throws FileNotFoundException{
    this.dataController = new DataController();
		this.scanner = new Scanner(System.in);
		loginManager = new LoginManager();
		state = MenuState.MAINMENU;
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

		if (loginManager.login(username, password)) {
			System.out.println("You have succesfully logged in!");
			state = MenuState.SEARCH;
		} else {
			ui.wrongLogin();
			String input = scanner.nextLine();

			if (input.equals("y")) {
				state = MenuState.LOGIN;
			} else {
				state = MenuState.MAINMENU;
			}

		}
	}

	public void displayDatabaseLookupMenu() {
		ui.clear();
		ui.printTop();
		ui.printSearchMenu();
		ui.printBot();
		System.out.print("\n- ");
		String input = scanner.nextLine();
	}

}
