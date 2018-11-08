import java.util.Scanner;

public class Menu {

	private DataController dataController;
	private Scanner scanner;

	public Menu() {
      this.dataController = new DataController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu() {
		System.out.println("*** Build A Base ***");
		System.out.println("0 : Exit")
		System.out.println("1 : Login");
		System.out.println("2 : Database lookup");

		while (scanner.hasNextInt())
		{
			int input = scanner.nextInt();
			if (input == 0)
			{
				return false;
			}
			else if (input == 1)
			{
				displayLoginMenu();
			}
			else if (intput == 2)
			{
				displayDatabaseLookupMenu();
			}
		}

		return true;
	}

	public void displayLoginMenu() {
		// TODO - implement Menu.displayStaffMenu
		throw new UnsupportedOperationException();
	}

	public void displayDatabaseLookupMenu() {
		// TODO - implement Menu.displayLoginPrompt
		throw new UnsupportedOperationException();
	}

}