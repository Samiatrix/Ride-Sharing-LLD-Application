package RideSharing.service.implementation;

import RideSharing.models.Driver;
import RideSharing.models.Rider;
import RideSharing.service.DriverMatchingStrategy;

import java.util.List;
import java.util.Optional;

public class DriverMatchingStrategyImpl implements DriverMatchingStrategy {
    @Override
    public Optional<Driver> findDriver(Rider rider, List<Driver> drivers, int origin, int destination){
        return drivers.stream().findAny();
    }
}
