import java.util.Scanner;

public class Menu {

	private DataController dataController;
	private Scanner scanner;

	public Menu() {
      this.dataController = new DataController();
		this.scanner = new Scanner(System.in);
	}

	public boolean displayMenu() {
		return true;
	}

	public void displayStaffMenu() {
		// TODO - implement Menu.displayStaffMenu
		throw new UnsupportedOperationException();
	}

	public void displayLoginPrompt() {
		// TODO - implement Menu.displayLoginPrompt
		throw new UnsupportedOperationException();
	}

}