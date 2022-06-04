package RideSharing.controller;

import RideSharing.models.Rider;
import RideSharing.service.RiderService;

public class RiderController {
    private RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    public void createRider(String id, String name){
        riderService.createRider(new Rider(id, name));
    }
}
