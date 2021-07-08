package mktany2k.wcc.service;

import mktany2k.wcc.controller.exception.PostalCodeNotFoundException;
import mktany2k.wcc.dto.Distance;
import mktany2k.wcc.model.Location;
import mktany2k.wcc.repository.LocationRepository;
import org.springframework.stereotype.Service;

import static mktany2k.wcc.service.Mappers.asDto;

@Service
public class DistanceService {

    private final LocationRepository repository;

    public DistanceService(LocationRepository repository) {
        this.repository = repository;
    }

    public Distance calculateDistance(String fromPostCode, String toPostCode) {
        return distanceBetween(locationIn(fromPostCode), locationIn(toPostCode));
    }

    private Distance distanceBetween(Location from, Location to) {
        return new Distance(asDto(from), asDto(to));
    }

    private Location locationIn(String postCode) {
        return repository.findOneByPostcode(postCode)
                .orElseThrow(() -> new PostalCodeNotFoundException(postCode));
    }
}
