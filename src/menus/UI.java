package menus;

public class UI{
  public UI(){

  }

  public void clear() {
    System.out.print("\033[H\033[2J");
  }

  public void printTop() {
    System.out.print("\n░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
    System.out.print("░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓ BUILD A BASE ▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
  }

  public void printBot() {
    System.out.print("\n░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ v. 1.0 ▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
  }

  public void printMainMenu() {
    System.out.println("\n1. Login");
    System.out.println("2. Search");
    System.out.println("3. Exit");
  }

  public void printSearchMenu() {
    System.out.println("\nWhich type do you want to search for?\n");
    System.out.println("1. Author");
    System.out.println("2. Title");
    System.out.println("3. Back");
  }

  public void login() {
    clear();
    System.out.print("\n░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
    System.out.print("░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓    LOGIN     ▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n\n");
  }

  public void failedLogin() {
    System.out.print("\nDo you want to try again? (y/n)\n");
    printBot();
    System.out.print("\n- ");
  }

  public void exit() {
    printTop();
    System.out.println("\nThank you for using Build a Base.");
    System.out.println("Bye!");
    printBot();
    System.out.println("");
  }
}
