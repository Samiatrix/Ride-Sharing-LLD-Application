package RideSharing.service.implementation;

import RideSharing.exception.DriverAlreadyExistsException;
import RideSharing.models.Driver;
import RideSharing.service.DriverService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverServiceImpl implements DriverService {
    Map<String, Driver> drivers;

    public DriverServiceImpl() {
        this.drivers = new HashMap<>();
    }

    @Override
    public void createDriver(Driver driver) {
        if(drivers.containsKey(driver.getId())){
            throw new DriverAlreadyExistsException();
        }
        drivers.put(driver.getId(), driver);
    }

    @Override
    public List<Driver> getDrivers() {
        return drivers.values().stream().filter(Driver::isAvailable).collect(Collectors.toList());
    }
}
