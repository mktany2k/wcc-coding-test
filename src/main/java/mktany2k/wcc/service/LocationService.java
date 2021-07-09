package mktany2k.wcc.service;

import mktany2k.wcc.dto.LocationDto;
import mktany2k.wcc.exception.PostalCodeNotFoundException;
import mktany2k.wcc.model.Location;
import mktany2k.wcc.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Service
public class LocationService {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public void update(LocationDto locationDto) {
        logger.debug("updating location {}", locationDto);
        Location location = repository.findOneByPostcode(locationDto.getPostalCode())
                .orElseThrow(() -> new PostalCodeNotFoundException(locationDto.getPostalCode()));
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLatitude());
        repository.saveAndFlush(location);
    }
}
