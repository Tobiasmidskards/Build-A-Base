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
    System.out.println("\n3. Exit");
    Bot();
    input();
  }

  public void SearchMenu(boolean loggedIn) {
    Top();

    System.out.println("\nWhich type do you want to search for?\n");
    System.out.println("0. Person");
    System.out.println("1. Title");
    System.out.println("2. Title (with index enabled)");

    if (loggedIn) {
      System.out.println("\nAs admin you can manage\n");
      System.out.println("3. Database");
      System.out.println("4. Eventlogs");
      System.out.println("5. Back");
    } else {
      System.out.println("3. Back");
    }

    Bot();
    input();
  }

  public void ManagementMenu()
  {
    Top();
    System.out.println("\nDatabase management Menu\n");
    System.out.println("Type number and parameters to run command.");
    System.out.println("Example: '1 newtable primarykey-foreignkey-data1-data2'\n");
    System.out.println("1. Create table [table name] [column1-column2-column3..]");
    System.out.println("2. Delete table [table name]");
    System.out.println("3. Create row [column1-column2-column3..] [table name]");
    System.out.println("4. Read row [primary key] [table name]");
    System.out.println("5. Update row [primary key] [column1-column2-column3..] [table name]");
    System.out.println("6. Delete row [primary key] [table name]");
    System.out.println("7. Get table structure [table name]");
    System.out.println("8. Create index table [filter] [column number] [table name] [column to index]");
    System.out.println("9. Back");
    Bot();
    input();
  }

  public void printRowRead(String[] row)
  {
    System.out.println("");
    for (int i = 0; i < row.length; i++)
    {
      System.out.print("[" + row[i] +"] ");
    }
    System.out.print("\n");
  }

  public void invalidParameter()
  {
    System.out.println("\nInvalid input...");
  }

  public void eventLogMenu()
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
