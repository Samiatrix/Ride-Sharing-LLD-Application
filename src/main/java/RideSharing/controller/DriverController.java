package RideSharing.controller;

import RideSharing.models.Driver;
import RideSharing.service.DriverService;

public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    public void createDriver(String id, String name){
        driverService.createDriver(new Driver(id, name));
    }
}
