import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static TrackBoxSystem system = new TrackBoxSystem();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==========================================");
            System.out.println("                 TRACKBOX");
            System.out.println(" Subscription Delivery Management System");
            System.out.println("==========================================");

            System.out.println("1. Login");
            System.out.println("2. Exit");

            int choice = readInt("Choose: ");

            if (choice == 1) {

                login();

            } else if (choice == 2) {

                System.out.println("\nThank you for using TrackBox!");
                break;

            } else {

                System.out.println("Invalid choice.");
            }
        }
    }

    public static void login() {

        System.out.println("\n========== LOGIN ==========");

        String username = readString("Username: ");
        String password = readString("Password: ");


        if (username.equals("admin")
                && password.equals("1234")) {

            Admin admin =
                    new Admin(
                            "admin",
                            "1234",
                            "System Administrator"
                    );

            adminMenu(admin);
            return;
        }


        if (username.equals("staff")
                && password.equals("1234")) {

            Staff staff =
                    new Staff(
                            "staff",
                            "1234",
                            "TrackBox Staff"
                    );

            staffMenu(staff);
            return;
        }


        Customer customer =
                system.findCustomerByUsername(username);

        if (customer != null
                && customer.login(username, password)) {

            customerMenu(customer);

        } else {

            System.out.println(
                    "\nInvalid username or password."
            );
        }
    }
    public static void adminMenu(Admin admin) {

        while (true) {

            System.out.println("\n==========================================");
            System.out.println("              ADMIN MENU");
            System.out.println("==========================================");

            System.out.println("1. Dashboard");
            System.out.println("2. Customer Management");
            System.out.println("3. Subscription Management");
            System.out.println("4. Delivery Management");
            System.out.println("5. Container Management");
            System.out.println("6. Reports");
            System.out.println("7. Logout");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1:
                    dashboard();
                    break;

                case 2:
                    customerManagement();
                    break;

                case 3:
                    subscriptionManagement();
                    break;

                case 4:
                    deliveryManagement();
                    break;

                case 5:
                    containerManagement();
                    break;

                case 6:
                    reports();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    public static void customerManagement() {

        while (true) {

            System.out.println("\n========== CUSTOMER MANAGEMENT ==========");

            System.out.println("1. Create Customer");
            System.out.println("2. Read Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1:
                    createCustomer();
                    break;

                case 2:
                    readCustomers();
                    break;

                case 3:
                    updateCustomer();
                    break;

                case 4:
                    deleteCustomer();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void createCustomer() {

        System.out.println("\n========== CREATE CUSTOMER ==========");

        String username = readString("Username: ");
        String password = readString("Password: ");
        String name = readString("Full Name: ");
        String address = readString("Address: ");

        Customer customer =
                system.addCustomer(
                        username,
                        password,
                        name,
                        address
                );

        System.out.println(
                "\nCustomer created successfully!"
        );

        System.out.println(
                "Customer ID: "
                        + customer.getCustomerId()
        );
    }

    public static void readCustomers() {

        System.out.println("\n========== CUSTOMERS ==========");

        for (Customer customer :
                system.getCustomers()) {

            System.out.println("--------------------------------");

            customer.displayInfo();
        }
    }

    public static void updateCustomer() {

        readCustomers();

        String id =
                readString("\nCustomer ID: ");

        Customer customer =
                system.findCustomer(id);

        if (customer == null) {

            System.out.println("Customer not found.");
            return;
        }

        String name =
                readString("New Name: ");

        String address =
                readString("New Address: ");

        if (!name.isEmpty()) {
            customer.setName(name);
        }

        if (!address.isEmpty()) {
            customer.setAddress(address);
        }

        System.out.println(
                "\nCustomer updated successfully!"
        );
    }

    public static void deleteCustomer() {

        readCustomers();

        String id =
                readString("\nCustomer ID to delete: ");

        boolean deleted =
                system.deleteCustomer(id);

        if (deleted) {

            System.out.println(
                    "\nCustomer deleted successfully!"
            );

        } else {

            System.out.println(
                    "\nCustomer not found."
            );
        }
    }


    public static void subscriptionManagement() {

        while (true) {

            System.out.println(
                    "\n========== SUBSCRIPTION MANAGEMENT =========="
            );

            System.out.println("1. Create Subscription");
            System.out.println("2. Read Subscriptions");
            System.out.println("3. Update Subscription");
            System.out.println("4. Delete Subscription");
            System.out.println("5. Back");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1:
                    createSubscription();
                    break;

                case 2:
                    readSubscriptions();
                    break;

                case 3:
                    updateSubscription();
                    break;

                case 4:
                    deleteSubscription();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void createSubscription() {

        readCustomers();

        String customerId =
                readString("\nCustomer ID: ");

        Customer customer =
                system.findCustomer(customerId);

        if (customer == null) {

            System.out.println("Customer not found.");
            return;
        }

        String type =
                readString("Subscription Type: ");

        String schedule =
                readString("Delivery Schedule: ");

        int quantity =
                readInt("Quantity: ");

        Subscription subscription =
                system.addSubscription(
                        customer,
                        type,
                        schedule,
                        quantity
                );

        System.out.println(
                "\nSubscription created!"
        );

        System.out.println(
                "Subscription ID: "
                        + subscription.getSubscriptionId()
        );
    }

    public static void readSubscriptions() {

        System.out.println("\n========== SUBSCRIPTIONS ==========");

        for (Subscription subscription :
                system.getSubscriptions()) {

            System.out.println("--------------------------------");

            subscription.displayInfo();
        }
    }

    public static void updateSubscription() {

        readSubscriptions();

        String id =
                readString("\nSubscription ID: ");

        Subscription subscription =
                system.findSubscription(id);

        if (subscription == null) {

            System.out.println(
                    "Subscription not found."
            );

            return;
        }

        System.out.println("\n1. Change Type");
        System.out.println("2. Change Schedule");
        System.out.println("3. Change Quantity");
        System.out.println("4. Change Status");

        int choice = readInt("Choose: ");

        switch (choice) {

            case 1:
                subscription.setType(
                        readString("New Type: ")
                );
                break;

            case 2:
                subscription.setSchedule(
                        readString("New Schedule: ")
                );
                break;

            case 3:
                subscription.setQuantity(
                        readInt("New Quantity: ")
                );
                break;

            case 4:
                subscription.setStatus(
                        subscriptionStatus()
                );
                break;

            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println(
                "\nSubscription updated successfully!"
        );
    }

    public static void deleteSubscription() {

        readSubscriptions();

        String id =
                readString("\nSubscription ID: ");

        if (system.deleteSubscription(id)) {

            System.out.println(
                    "\nSubscription deleted successfully!"
            );

        } else {

            System.out.println(
                    "\nSubscription not found."
            );
        }
    }


    public static void deliveryManagement() {

        while (true) {

            System.out.println(
                    "\n========== DELIVERY MANAGEMENT =========="
            );

            System.out.println("1. Create Delivery");
            System.out.println("2. Read Deliveries");
            System.out.println("3. Update Delivery");
            System.out.println("4. Delete Delivery");
            System.out.println("5. Back");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1:
                    createDelivery();
                    break;

                case 2:
                    readDeliveries();
                    break;

                case 3:
                    updateDelivery();
                    break;

                case 4:
                    deleteDelivery();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void createDelivery() {

        readCustomers();

        String id =
                readString("\nCustomer ID: ");

        Customer customer =
                system.findCustomer(id);

        if (customer == null) {

            System.out.println("Customer not found.");
            return;
        }

        String date =
                readString("Delivery Date: ");

        Delivery delivery =
                system.addDelivery(
                        customer,
                        date
                );

        System.out.println(
                "\nDelivery created successfully!"
        );

        System.out.println(
                "Delivery ID: "
                        + delivery.getDeliveryId()
        );
    }

    public static void readDeliveries() {

        System.out.println("\n========== DELIVERIES ==========");

        for (Delivery delivery :
                system.getDeliveries()) {

            System.out.println("--------------------------------");

            delivery.displayInfo();
        }
    }

    public static void updateDelivery() {

        readDeliveries();

        String id =
                readString("\nDelivery ID: ");

        Delivery delivery =
                system.findDelivery(id);

        if (delivery == null) {

            System.out.println("Delivery not found.");
            return;
        }

        System.out.println("\n1. Change Date");
        System.out.println("2. Change Status");

        int choice = readInt("Choose: ");

        if (choice == 1) {

            delivery.setDeliveryDate(
                    readString("New Date: ")
            );

        } else if (choice == 2) {

            delivery.setStatus(
                    deliveryStatus()
            );

        } else {

            System.out.println("Invalid choice.");
            return;
        }

        System.out.println(
                "\nDelivery updated successfully!"
        );
    }

    public static void deleteDelivery() {

        readDeliveries();

        String id =
                readString("\nDelivery ID: ");

        if (system.deleteDelivery(id)) {

            System.out.println(
                    "\nDelivery deleted successfully!"
            );

        } else {

            System.out.println(
                    "\nDelivery not found."
            );
        }
    }


    public static void containerManagement() {

        while (true) {

            System.out.println(
                    "\n========== CONTAINER MANAGEMENT =========="
            );

            System.out.println("1. Create Container");
            System.out.println("2. Read Containers");
            System.out.println("3. Update Container");
            System.out.println("4. Delete Container");
            System.out.println("5. Back");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1:
                    createContainer();
                    break;

                case 2:
                    readContainers();
                    break;

                case 3:
                    updateContainer();
                    break;

                case 4:
                    deleteContainer();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void createContainer() {

        String type =
                readString("Type/Size: ");

        String location =
                readString("Current Location: ");

        String status =
                containerStatus();

        Container container =
                system.addContainer(
                        type,
                        location,
                        status
                );

        System.out.println(
                "\nContainer created successfully!"
        );

        System.out.println(
                "Container ID: "
                        + container.getContainerId()
        );
    }

    public static void readContainers() {

        System.out.println("\n========== CONTAINERS ==========");

        for (Container container :
                system.getContainers()) {

            System.out.println("--------------------------------");

            container.displayInfo();
        }
    }

    public static void updateContainer() {

        readContainers();

        String id =
                readString("\nContainer ID: ");

        Container container =
                system.findContainer(id);

        if (container == null) {

            System.out.println("Container not found.");
            return;
        }

        System.out.println("\n1. Change Type/Size");
        System.out.println("2. Change Location");
        System.out.println("3. Change Status");

        int choice = readInt("Choose: ");

        switch (choice) {

            case 1:
                container.setType(
                        readString("New Type/Size: ")
                );
                break;

            case 2:
                container.setCurrentLocation(
                        readString("New Location: ")
                );
                break;

            case 3:
                container.setStatus(
                        containerStatus()
                );
                break;

            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println(
                "\nContainer updated successfully!"
        );
    }

    public static void deleteContainer() {

        readContainers();

        String id =
                readString("\nContainer ID: ");

        if (system.deleteContainer(id)) {

            System.out.println(
                    "\nContainer deleted successfully!"
            );

        } else {

            System.out.println(
                    "\nContainer not found."
            );
        }
    }


    public static void staffMenu(Staff staff) {

        while (true) {

            System.out.println("\n==========================================");
            System.out.println("               STAFF MENU");
            System.out.println("==========================================");

            System.out.println("1. View Deliveries");
            System.out.println("2. Update Delivery Status");
            System.out.println("3. View Containers");
            System.out.println("4. Update Container Status");
            System.out.println("5. View Customer Information");
            System.out.println("6. Logout");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1:
                    readDeliveries();
                    break;

                case 2:
                    staffUpdateDelivery();
                    break;

                case 3:
                    readContainers();
                    break;

                case 4:
                    staffUpdateContainer();
                    break;

                case 5:
                    readCustomers();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void staffUpdateDelivery() {

        readDeliveries();

        String id =
                readString("\nDelivery ID: ");

        Delivery delivery =
                system.findDelivery(id);

        if (delivery == null) {

            System.out.println("Delivery not found.");
            return;
        }

        delivery.setStatus(
                deliveryStatus()
        );

        System.out.println(
                "\nDelivery status updated!"
        );
    }

    public static void staffUpdateContainer() {

        readContainers();

        String id =
                readString("\nContainer ID: ");

        Container container =
                system.findContainer(id);

        if (container == null) {

            System.out.println("Container not found.");
            return;
        }

        container.setStatus(
                containerStatus()
        );

        System.out.println(
                "\nContainer status updated!"
        );
    }


    public static void customerMenu(Customer customer) {

        while (true) {

            System.out.println("\n==========================================");
            System.out.println("             CUSTOMER MENU");
            System.out.println("==========================================");

            System.out.println("1. My Subscription");
            System.out.println("2. My Deliveries");
            System.out.println("3. My Containers");
            System.out.println("4. Logout");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1:
                    mySubscription(customer);
                    break;

                case 2:
                    myDeliveries(customer);
                    break;

                case 3:
                    myContainers(customer);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void mySubscription(
            Customer customer) {

        System.out.println(
                "\n========== MY SUBSCRIPTION =========="
        );

        boolean found = false;

        for (Subscription subscription :
                system.getSubscriptions()) {

            if (subscription.getCustomer() == customer) {

                subscription.displayInfo();

                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No subscription found."
            );
        }
    }

    public static void myDeliveries(
            Customer customer) {

        System.out.println(
                "\n========== MY DELIVERIES =========="
        );

        boolean found = false;

        for (Delivery delivery :
                system.getDeliveries()) {

            if (delivery.getCustomer() == customer) {

                delivery.displayInfo();

                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No deliveries found."
            );
        }
    }

    public static void myContainers(
            Customer customer) {

        System.out.println(
                "\n========== MY CONTAINERS =========="
        );

        if (customer.getContainers().isEmpty()) {

            System.out.println(
                    "No containers assigned."
            );

            return;
        }

        for (Container container :
                customer.getContainers()) {

            container.displayInfo();

            System.out.println("--------------------------------");
        }
    }

    public static void dashboard() {

        System.out.println(
                "\n========== DASHBOARD =========="
        );

        System.out.println(
                "Active Subscriptions      : "
                        + system.getActiveSubscriptions()
        );

        System.out.println(
                "Scheduled Deliveries      : "
                        + system.getScheduledDeliveries()
        );

        System.out.println(
                "Containers with Customers : "
                        + system.getContainersWithCustomers()
        );

        System.out.println(
                "Available Containers      : "
                        + system.getAvailableContainers()
        );
    }


    public static void reports() {

        System.out.println(
                "\n========== REPORTS =========="
        );

        System.out.println(
                "Completed Deliveries      : "
                        + system.getCompletedDeliveries()
        );

        System.out.println(
                "Active Subscriptions      : "
                        + system.getActiveSubscriptions()
        );

        System.out.println(
                "Containers with Customers : "
                        + system.getContainersWithCustomers()
        );

        System.out.println(
                "Returned Containers       : "
                        + system.getReturnedContainers()
        );
    }


    public static String subscriptionStatus() {

        System.out.println("\n1. Active");
        System.out.println("2. Paused");
        System.out.println("3. Cancelled");

        int choice = readInt("Choose: ");

        switch (choice) {

            case 1:
                return "Active";

            case 2:
                return "Paused";

            case 3:
                return "Cancelled";

            default:
                return "Active";
        }
    }

    public static String deliveryStatus() {

        System.out.println("\n1. Scheduled");
        System.out.println("2. Out for Delivery");
        System.out.println("3. Delivered");

        int choice = readInt("Choose: ");

        switch (choice) {

            case 1:
                return "Scheduled";

            case 2:
                return "Out for Delivery";

            case 3:
                return "Delivered";

            default:
                return "Scheduled";
        }
    }

    public static String containerStatus() {

        System.out.println("\n1. Available");
        System.out.println("2. With Customer");
        System.out.println("3. In Transit");
        System.out.println("4. Returned");

        int choice = readInt("Choose: ");

        switch (choice) {

            case 1:
                return "Available";

            case 2:
                return "With Customer";

            case 3:
                return "In Transit";

            case 4:
                return "Returned";

            default:
                return "Available";
        }
    }

    // =====================================================
    // INPUT METHODS
    // =====================================================

    public static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine();
    }

    public static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}