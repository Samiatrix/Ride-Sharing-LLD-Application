package RideSharing.models;

public class Driver {
    private String id;
    private String name;
    private Ride currentRide;
    private boolean isAvailable;
    public Driver(String id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }

    public void setCurrentRide(Ride currentRide) {
        this.currentRide = currentRide;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable() {
        return this.isAvailable && this.currentRide == null;
    }

    public String getId() {
        return id;
    }
}
