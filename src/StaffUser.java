public class StaffUser {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String staffTitle;

   public StaffUser()
   {
      this.id = -1;
      this.firstName = "";
      this.lastName = "";
      this.email = "";
      this.username = "";
      this.password = "";
      this.staffTitle = "";
   }

	public StaffUser(int id, String firstName, String lastName, String email, String username, String password, String staffTitle) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.username = username;
      this.password = password;
      this.staffTitle = staffTitle;
	}
   
   public int getId() {
      return this.id;
   }

}