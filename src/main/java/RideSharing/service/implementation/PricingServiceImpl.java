package RideSharing.service.implementation;

import RideSharing.service.PricingService;

public class PricingServiceImpl implements PricingService {

    private double CHARGE_PER_KM = 20.0;

    @Override
    public double findPrice(int origin, int destination, int noOfSeats) {
        int distance = destination-origin;
        double priceChangeForSeats = 1;
        if(noOfSeats>=2)    priceChangeForSeats = noOfSeats * 0.75;
        return noOfSeats * distance * priceChangeForSeats * CHARGE_PER_KM;
    }
}
