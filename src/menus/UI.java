package menus;

import datacontroller.*;

public class UI{

  public UI(){
  }

  public void clear() {
    System.out.print("\033[H\033[2J");
  }

  public void Top() {
    System.out.print("\n░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
    System.out.print("░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓ BUILD A BASE ▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
  }

  public void Bot() {
    System.out.print("\n░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ v. 1.0 ▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
  }

  public void MainMenu(boolean loggedIn) {
    Top();
    if(!loggedIn){
      System.out.println("\n1. Login");
    } else {
      System.out.println("\n1. Logout");
    }
    System.out.println("2. Search");
    System.out.println("3. Exit");
    Bot();
    input();
  }

  public void SearchMenu(boolean loggedIn) {
    Top();

    System.out.println("\nWhich type do you want to search for?\n");
    System.out.println("1. Person");
    System.out.println("2. Title");

    if (loggedIn) {
      System.out.println("3. Create/Read/Update/Delete");
      System.out.println("4. Eventlog");
      System.out.println("5. Back");
    } else {
      System.out.println("3. Back");
    }

    Bot();
    input();
  }

  public void EventLogMenu()
  {
    Top();

    System.out.println("\nEvent Menu\n");
    System.out.println("1. All events");
    System.out.println("2. Specific eventType");
    System.out.println("3. Specific id");
    System.out.println("4. Back");

    Bot();
    input();
  }

  public void login() {
    clear();
    System.out.print("\n░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
    System.out.print("░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓    LOGIN     ▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n\n");
  }

  public void logout() {
    clear();
    Top();
    System.out.println("\nYou are now logged out.");
    Bot();
  }

  public void failedLogin() {
    System.out.print("\nDo you want to try again? (y/n)\n");
    Bot();
    input();
  }

  public void exit() {
    Top();
    System.out.println("\nThank you for using Build a Base.");
    System.out.println("Bye!");
    Bot();
    System.out.println("");
  }

  public void input(){
    System.out.print("\n-  ");
  }
}
