package RideSharing.controller;

import RideSharing.exception.*;
import RideSharing.models.Driver;
import RideSharing.models.Ride;
import RideSharing.models.RideStatus;
import RideSharing.models.Rider;
import RideSharing.service.DriverMatchingStrategy;
import RideSharing.service.DriverService;
import RideSharing.service.PricingService;
import RideSharing.service.RiderService;

import java.util.*;

public class RideController {
    private DriverService driverService;
    private RiderService riderService;
    private DriverMatchingStrategy driverMatchingStrategy;
    private PricingService pricingService;
    Map<String, List<Ride>> rides;

    public RideController(DriverService driverService, RiderService riderService, DriverMatchingStrategy driverMatchingStrategy, PricingService pricingService) {
        this.driverService = driverService;
        this.riderService = riderService;
        this.driverMatchingStrategy = driverMatchingStrategy;
        this.pricingService = pricingService;
        this.rides = new HashMap<>();
    }

    public String createRide(String riderId, int origin, int destination, int noOfSeats){
        if(origin > destination){
            throw new OriginIsGreaterThanDestinationException();
        }
        Rider rider = riderService.getRider(riderId);
        if(rider == null){
            throw new RiderNotExistsException();
        }
        List<Driver> drivers = driverService.getDrivers();

        Optional<Driver> bestDriver = driverMatchingStrategy.findDriver(rider, drivers, origin, destination);
        if(bestDriver.isEmpty()){
            throw new DriverNotFoundException();
        }
        double fare = pricingService.findPrice(origin, destination, noOfSeats);
        Driver driver = bestDriver.get();

        Ride ride = new Ride(driver, rider, origin, destination, noOfSeats, fare);
        if(!rides.containsKey(rider.getId())){
            rides.put(rider.getId(), new ArrayList<>());
        }
        rides.get(riderId).add(ride);
        driver.setCurrentRide(ride);
        return ride.getId();
    }

    public void updateRide(String rideId, int origin, int destination, int noOfSeats) {
        Optional<Ride> ride = getRideByRideId(rideId);
        if(!ride.isPresent()){
            throw new RideNotExistsException();
        }
        Ride requiredRide = ride.get();
        if(requiredRide.getStatus().equals(RideStatus.WITHDRAW) || requiredRide.getStatus().equals(RideStatus.COMPLETED)){
            throw new TripEndedException();
        }
        double fare = pricingService.findPrice(origin, destination, noOfSeats);
        requiredRide.updateRide(origin, destination, noOfSeats, fare);
    }

    private Optional<Ride> getRideByRideId(String rideId) {
        return rides.values().stream().flatMap(list->list.stream()).filter(t->t.getId().equalsIgnoreCase(rideId)).findFirst();
    }

    public void withdrawRide(String rideId) {
        Optional<Ride> optionalRide = getRideByRideId(rideId);
        if(!optionalRide.isPresent()){
            throw new RideNotExistsException();
        }
        Ride ride = optionalRide.get();
        if(ride.getStatus().equals(RideStatus.COMPLETED)){
            throw new TripEndedException();
        }
        Driver driver = ride.getDriver();
        driver.setCurrentRide(null);
        ride.withdrawRide();
    }

    public double closeRide(String rideId){
        Optional<Ride> optionalRide = getRideByRideId(rideId);
        if(!optionalRide.isPresent()){
            throw new RideNotExistsException();
        }
        Ride ride = optionalRide.get();
        if(ride.getStatus().equals(RideStatus.COMPLETED) || ride.getStatus().equals(RideStatus.WITHDRAW)){
            throw new TripEndedException();
        }
        Driver driver = ride.getDriver();
        driver.setCurrentRide(null);
        ride.endRide();
        return ride.getFare();
    }
}
