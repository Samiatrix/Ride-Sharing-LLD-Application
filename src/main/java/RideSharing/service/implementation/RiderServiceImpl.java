package RideSharing.service.implementation;

import RideSharing.exception.RiderAlreadyExistsException;
import RideSharing.exception.RiderNotExistsException;
import RideSharing.models.Rider;
import RideSharing.service.RiderService;

import java.util.HashMap;
import java.util.Map;

public class RiderServiceImpl implements RiderService {
    Map<String, Rider> riders;

    public RiderServiceImpl() {
        this.riders = new HashMap<>();
    }

    @Override
    public void createRider(Rider rider) {
        if(riders.containsKey(rider.getId())){
            throw new RiderAlreadyExistsException();
        }
        riders.put(rider.getId(), rider);
    }

    @Override
    public Rider getRider(String riderId) {
        if(!riders.containsKey(riderId)){
            throw new RiderNotExistsException();
        }
        return riders.get(riderId);
    }
}
