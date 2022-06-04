package RideSharing.service;

import RideSharing.models.Driver;
import RideSharing.models.Rider;

import java.util.List;
import java.util.Optional;

public interface DriverMatchingStrategy {
    Optional<Driver> findDriver(Rider rider, List<Driver> drivers, int origin, int destination);
}
