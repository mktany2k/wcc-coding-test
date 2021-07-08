package mktany2k.wcc.service;

import mktany2k.wcc.controller.exception.PostalCodeNotFoundException;
import mktany2k.wcc.distance.Haversine;
import mktany2k.wcc.dto.Distance;
import mktany2k.wcc.dto.LocationDto;
import mktany2k.wcc.model.Location;
import mktany2k.wcc.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }


}
