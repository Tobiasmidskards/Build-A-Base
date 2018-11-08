import java.util.Scanner;

public class Menu {

	private DataController dataController;
	private Scanner scanner;
	private MenuState state;

	public Menu() {
    this.dataController = new DataController();
		this.scanner = new Scanner(System.in);
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
				displayLoginMenu();
				break;
		}
	}

	public void displayMainMenu() {
		System.out.println("*** Build A Base ***");
		System.out.println("0 : Exit");
		System.out.println("1 : Login");
		System.out.println("2 : Database lookup");

		// while (scanner.hasNextInt())
		// {
		// 	int input = scanner.nextInt();
		// 	if (input == 0)
		// 	{
		// 		return false;
		// 	}
		// 	else if (input == 1)
		// 	{
		// 		displayLoginMenu();
		// 	}
		// 	else if (intput == 2)
		// 	{
		// 		displayDatabaseLookupMenu();
		// 	}
		// }

		//return true;
	}

	public void displayLoginMenu() {
		System.out.println("*** Build A Base ***");
		System.out.print("Username: ");
	 	String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		// if (login(username, password)) {
		// 	state.SEARCH;
		// } else {
		// 	state.LOGIN;
		// }

	}

	public void displayDatabaseLookupMenu() {
		// TODO - implement Menu.displayLoginPrompt
		throw new UnsupportedOperationException();
	}

}
