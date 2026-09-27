import java.util.ArrayList;

public class Customer extends User {

    private String customerId;
    private String address;
    private ArrayList<Container> containers;

    public Customer(
            String customerId,
            String username,
            String password,
            String name,
            String address) {

        super(username, password, name);

        this.customerId = customerId;
        this.address = address;
        this.containers = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    public void removeContainer(Container container) {
        containers.remove(container);
    }

    public void displayInfo() {

        System.out.println("Customer ID : " + customerId);
        System.out.println("Name        : " + name);
        System.out.println("Username    : " + username);
        System.out.println("Address     : " + address);
    }
}
