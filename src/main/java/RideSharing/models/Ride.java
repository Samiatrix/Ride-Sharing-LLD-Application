package RideSharing.models;

import RideSharing.service.DriverService;

import java.util.UUID;

import static RideSharing.models.RideStatus.*;

public class Ride {
    private String id;
    private Driver driver;
    private Rider rider;
    private int origin;
    private int destination;
    private int noOfSeats;
    private Double fare;
    private RideStatus status;

    public Ride(Driver driver, Rider rider, int origin, int destination, int noOfSeats, Double fare) {
        this.id = UUID.randomUUID().toString();
        this.driver = driver;
        this.rider = rider;
        this.origin = origin;
        this.destination = destination;
        this.noOfSeats = noOfSeats;
        this.fare = fare;
        this.status = IN_PROGRESS;
    }

    public String getId() {
        return id;
    }

    public RideStatus getStatus() {
        return status;
    }

    public Driver getDriver() {
        return driver;
    }

    public Double getFare() {
        return fare;
    }

    public void withdrawRide(){
        this.status = WITHDRAW;
    }

    public void endRide(){
        this.status = COMPLETED;
    }

    public void updateRide(int origin, int destination, int noOfSeats, double fare){
        this.origin = origin;
        this.destination = destination;
        this.noOfSeats = noOfSeats;
        this.fare = fare;
    }
}
