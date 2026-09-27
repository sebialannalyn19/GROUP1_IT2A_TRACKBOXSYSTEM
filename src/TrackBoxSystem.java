import java.util.ArrayList;

public class TrackBoxSystem {

    private ArrayList<Customer> customers;
    private ArrayList<Subscription> subscriptions;
    private ArrayList<Delivery> deliveries;
    private ArrayList<Container> containers;

    private int customerNumber = 1;
    private int subscriptionNumber = 1;
    private int deliveryNumber = 1;
    private int containerNumber = 1;

    public TrackBoxSystem() {

        customers = new ArrayList<>();
        subscriptions = new ArrayList<>();
        deliveries = new ArrayList<>();
        containers = new ArrayList<>();

        createSampleData();
    }


    public Customer addCustomer(
            String username,
            String password,
            String name,
            String address) {

        String id = String.format(
                "C%03d",
                customerNumber++
        );

        Customer customer = new Customer(
                id,
                username,
                password,
                name,
                address
        );

        customers.add(customer);

        return customer;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public Customer findCustomer(String id) {

        for (Customer customer : customers) {

            if (customer.getCustomerId()
                    .equalsIgnoreCase(id)) {

                return customer;
            }
        }

        return null;
    }

    public Customer findCustomerByUsername(String username) {

        for (Customer customer : customers) {

            if (customer.getUsername()
                    .equalsIgnoreCase(username)) {

                return customer;
            }
        }

        return null;
    }

    public boolean deleteCustomer(String id) {

        Customer customer = findCustomer(id);

        if (customer != null) {

            customers.remove(customer);

            subscriptions.removeIf(
                    s -> s.getCustomer() == customer
            );

            deliveries.removeIf(
                    d -> d.getCustomer() == customer
            );

            return true;
        }

        return false;
    }


    public Subscription addSubscription(
            Customer customer,
            String type,
            String schedule,
            int quantity) {

        String id = String.format(
                "S%03d",
                subscriptionNumber++
        );

        Subscription subscription =
                new Subscription(
                        id,
                        customer,
                        type,
                        schedule,
                        quantity,
                        "Active"
                );

        subscriptions.add(subscription);

        return subscription;
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public Subscription findSubscription(String id) {

        for (Subscription subscription : subscriptions) {

            if (subscription.getSubscriptionId()
                    .equalsIgnoreCase(id)) {

                return subscription;
            }
        }

        return null;
    }

    public boolean deleteSubscription(String id) {

        Subscription subscription =
                findSubscription(id);

        if (subscription != null) {

            subscriptions.remove(subscription);

            return true;
        }

        return false;
    }


    public Delivery addDelivery(
            Customer customer,
            String date) {

        String id = String.format(
                "D%03d",
                deliveryNumber++
        );

        Delivery delivery = new Delivery(
                id,
                customer,
                date,
                customer.getAddress(),
                "Scheduled"
        );

        deliveries.add(delivery);

        return delivery;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public Delivery findDelivery(String id) {

        for (Delivery delivery : deliveries) {

            if (delivery.getDeliveryId()
                    .equalsIgnoreCase(id)) {

                return delivery;
            }
        }

        return null;
    }

    public boolean deleteDelivery(String id) {

        Delivery delivery = findDelivery(id);

        if (delivery != null) {

            deliveries.remove(delivery);

            return true;
        }

        return false;
    }


    public Container addContainer(
            String type,
            String location,
            String status) {

        String id = String.format(
                "TB-%03d",
                containerNumber++
        );

        Container container = new Container(
                id,
                type,
                location,
                status
        );

        containers.add(container);

        return container;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public Container findContainer(String id) {

        for (Container container : containers) {

            if (container.getContainerId()
                    .equalsIgnoreCase(id)) {

                return container;
            }
        }

        return null;
    }

    public boolean deleteContainer(String id) {

        Container container = findContainer(id);

        if (container != null) {

            containers.remove(container);

            return true;
        }

        return false;
    }


    public int getActiveSubscriptions() {

        int count = 0;

        for (Subscription subscription : subscriptions) {

            if (subscription.getStatus()
                    .equals("Active")) {

                count++;
            }
        }

        return count;
    }

    public int getScheduledDeliveries() {

        int count = 0;

        for (Delivery delivery : deliveries) {

            if (delivery.getStatus()
                    .equals("Scheduled")) {

                count++;
            }
        }

        return count;
    }

    public int getContainersWithCustomers() {

        int count = 0;

        for (Container container : containers) {

            if (container.getStatus()
                    .equals("With Customer")) {

                count++;
            }
        }

        return count;
    }

    public int getAvailableContainers() {

        int count = 0;

        for (Container container : containers) {

            if (container.getStatus()
                    .equals("Available")) {

                count++;
            }
        }

        return count;
    }


    public int getCompletedDeliveries() {

        int count = 0;

        for (Delivery delivery : deliveries) {

            if (delivery.getStatus()
                    .equals("Delivered")) {

                count++;
            }
        }

        return count;
    }

    public int getReturnedContainers() {

        int count = 0;

        for (Container container : containers) {

            if (container.getStatus()
                    .equals("Returned")) {

                count++;
            }
        }

        return count;
    }


    private void createSampleData() {

        Customer carlo = addCustomer(
                "carlo",
                "1234",
                "Carlo Tañedo",
                "Talisay City, Cebu"
        );

        Customer juan = addCustomer(
                "juan",
                "1234",
                "Juan Dela Cruz",
                "Minglanilla, Cebu"
        );

        addSubscription(
                carlo,
                "Weekly Container",
                "Every Sunday",
                2
        );

        addSubscription(
                juan,
                "Monthly Container",
                "Every 30 Days",
                4
        );

        addDelivery(
                carlo,
                "September 27, 2026"
        );

        addDelivery(
                juan,
                "September 28, 2026"
        );

        Container c1 = addContainer(
                "20L",
                "Carlo's House",
                "With Customer"
        );

        addContainer(
                "20L",
                "TrackBox Warehouse",
                "Available"
        );

        addContainer(
                "10L",
                "Delivery Vehicle",
                "In Transit"
        );

        carlo.addContainer(c1);
    }
}