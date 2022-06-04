package RideSharing.service;

public interface PricingService {
    double findPrice(int origin, int destination, int noOfSeats);
}
