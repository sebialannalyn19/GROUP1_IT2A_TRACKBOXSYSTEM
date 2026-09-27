public class Staff extends User {

    public Staff(String username, String password, String name) {
        super(username, password, name);
    }

    public void displayRole() {
        System.out.println("Role: Staff");
    }
}