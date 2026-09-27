public class Delivery {

    private String deliveryId;
    private Customer customer;
    private String deliveryDate;
    private String address;
    private String status;

    public Delivery(
            String deliveryId,
            Customer customer,
            String deliveryDate,
            String address,
            String status) {

        this.deliveryId = deliveryId;
        this.customer = customer;
        this.deliveryDate = deliveryDate;
        this.address = address;
        this.status = status;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayInfo() {

        System.out.println("Delivery ID : " + deliveryId);
        System.out.println("Customer    : "
                + customer.getName());
        System.out.println("Date        : " + deliveryDate);
        System.out.println("Address     : " + address);
        System.out.println("Status      : " + status);
    }
}
