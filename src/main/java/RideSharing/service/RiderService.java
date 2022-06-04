package RideSharing.service;

import RideSharing.models.Rider;

public interface RiderService {
    void createRider(Rider rider);

    Rider getRider(String riderId);
}
