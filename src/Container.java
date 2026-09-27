public class Container {

    private String containerId;
    private String type;
    private String currentLocation;
    private String status;

    public Container(
            String containerId,
            String type,
            String currentLocation,
            String status) {

        this.containerId = containerId;
        this.type = type;
        this.currentLocation = currentLocation;
        this.status = status;
    }

    public String getContainerId() {
        return containerId;
    }

    public String getType() {
        return type;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayInfo() {

        System.out.println("Container ID     : " + containerId);
        System.out.println("Type/Size        : " + type);
        System.out.println("Current Location : "
                + currentLocation);
        System.out.println("Status           : " + status);
    }
}