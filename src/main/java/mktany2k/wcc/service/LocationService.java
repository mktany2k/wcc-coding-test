package mktany2k.wcc.service;

import mktany2k.wcc.repository.LocationRepository;

public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }
}
