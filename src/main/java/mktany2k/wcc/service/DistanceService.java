package mktany2k.wcc.service;

import mktany2k.wcc.controller.exception.PostalCodeNotFoundException;
import mktany2k.wcc.dto.Distance;
import mktany2k.wcc.dto.LocationDto;
import mktany2k.wcc.model.Location;
import mktany2k.wcc.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    private final LocationRepository repository;

    public DistanceService(LocationRepository repository) {
        this.repository = repository;
    }

    public Distance calculate(String fromPostCode, String toPostCode) {
        Location fromLoc = repository.findOneByPostcode(fromPostCode)
                .orElseThrow(() -> new PostalCodeNotFoundException(fromPostCode));
        Location toLoc = repository.findOneByPostcode(toPostCode)
                .orElseThrow(() -> new PostalCodeNotFoundException(toPostCode));
        
        Distance distance = new Distance();
        distance.setFrom(Mappers.from(fromLoc));
        distance.setTo(Mappers.from(toLoc));
        distance.setDistance(Haversine.distance(fromLoc.getLongitude(), fromLoc.getLatitude(), toLoc.getLongitude(), toLoc.getLatitude()));
        return distance;
    }
}
