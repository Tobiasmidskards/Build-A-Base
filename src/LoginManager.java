import java.io.*;
import java.util.*;
import java.util.Scanner;

public class LoginManager{

  private String[][] userList = new String[10][2];
  private File users;
  private Scanner scanner;
  private boolean isLoggedIn;

  public LoginManager(){
    try {
      users = new File("login.txt");
      scanner = new Scanner(users);
      scanner.useDelimiter(" ");
      if (users.canRead()) {
        insertIntoUserList();
      }
    } catch (FileNotFoundException e) {
      System.out.println(e);
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

  public boolean login(String username, String password){

    for (int i = 0; i < userList.length; i++) {
      if (username.equals(userList[i][0]) && password.equals(userList[i][1])) {
       isLoggedIn = true;
       return true;
      }
    }
    return false;
  }
}
