import java.io.*;
import java.util.*;
import java.util.Scanner;

public class LoginManager{

  private String[][] userList = new String[10][2];
  private File users;
  private Scanner scanner;
  private boolean isLoggedIn;

  public LoginManager() throws FileNotFoundException{
    users = new File("login.txt");
    scanner = new Scanner(users);
    scanner.useDelimiter(" ");
    if (users.canRead()) {
      insertIntoUserList();
    }
  }

  public void insertIntoUserList() {
    int iterator = 0;
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] lineSplit = line.split(" ");
      userList[iterator][0] = lineSplit[0];
      userList[iterator][1] = lineSplit[1];
      iterator++;
    }
  }

  public boolean getIsLoggedIn() {
    return this.isLoggedIn;
  }

  public boolean login(String username, String password) {



    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.contains(username) && line.contains(password)) {
        isLoggedIn = true;
        return true;
      }

    }
    return false;
  }
}
