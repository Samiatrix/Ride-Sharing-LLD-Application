package RideSharing.service;

import RideSharing.models.Driver;

import java.util.List;

public interface DriverService {
    void createDriver(Driver driver);

    List<Driver> getDrivers();
}
