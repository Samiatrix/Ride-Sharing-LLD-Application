package RideSharing;

import RideSharing.controller.DriverController;
import RideSharing.controller.RideController;
import RideSharing.controller.RiderController;
import RideSharing.models.Ride;
import RideSharing.service.DriverMatchingStrategy;
import RideSharing.service.DriverService;
import RideSharing.service.PricingService;
import RideSharing.service.RiderService;
import RideSharing.service.implementation.DriverMatchingStrategyImpl;
import RideSharing.service.implementation.DriverServiceImpl;
import RideSharing.service.implementation.PricingServiceImpl;
import RideSharing.service.implementation.RiderServiceImpl;

public class RideSharingDriver {

    public static void main(String[] args) {
        DriverService driverService = new DriverServiceImpl();
        RiderService riderService = new RiderServiceImpl();
        PricingService pricingService = new PricingServiceImpl();
        DriverMatchingStrategy driverMatchingStrategy = new DriverMatchingStrategyImpl();
        DriverController driverController = new DriverController(driverService);
        RiderController riderController = new RiderController(riderService);
        RideController rideController = new RideController(driverService, riderService, driverMatchingStrategy, pricingService);

        driverController.createDriver("driverId1", "driverName1");
        driverController.createDriver("driverId2", "driverName2");
        driverController.createDriver("driverId3", "driverName3");
        driverController.createDriver("driverId4", "driverName4");
        driverController.createDriver("driverId5", "driverName5");

        riderController.createRider("riderId1", "riderName1");
        riderController.createRider("riderId2", "riderName2");
        riderController.createRider("riderId3", "riderName3");
        riderController.createRider("riderId4", "riderName4");
        riderController.createRider("riderId5", "riderName5");

        String rideId1 = rideController.createRide("riderId1", 0, 10, 2);
        String rideId2 = rideController.createRide("riderId2", 10, 20, 1);
        String rideId3 = rideController.createRide("riderId3", 2, 8, 3);
        String rideId4 = rideController.createRide("riderId4", 6, 9, 1);
        String rideId5 = rideController.createRide("riderId5", 5, 16, 2);

        rideController.updateRide(rideId1, 2, 12, 1);
        rideController.updateRide(rideId2, 4, 8, 2);

        rideController.withdrawRide(rideId3);

        System.out.println("Ride Amount Charged: " + rideController.closeRide(rideId4));
        System.out.println("Ride Amount Charged: " + rideController.closeRide(rideId5));





    }
}
