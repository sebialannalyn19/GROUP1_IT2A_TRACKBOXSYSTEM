public class Subscription {

    private String subscriptionId;
    private Customer customer;
    private String type;
    private String schedule;
    private int quantity;
    private String status;

    public Subscription(
            String subscriptionId,
            Customer customer,
            String type,
            String schedule,
            int quantity,
            String status) {

        this.subscriptionId = subscriptionId;
        this.customer = customer;
        this.type = type;
        this.schedule = schedule;
        this.quantity = quantity;
        this.status = status;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getType() {
        return type;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayInfo() {

        System.out.println("Subscription ID : " + subscriptionId);
        System.out.println("Customer        : "
                + customer.getName());
        System.out.println("Type            : " + type);
        System.out.println("Schedule        : " + schedule);
        System.out.println("Quantity        : " + quantity);
        System.out.println("Status          : " + status);
    }
}
