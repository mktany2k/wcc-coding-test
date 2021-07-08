package mktany2k.wcc.service;

import mktany2k.wcc.dto.LocationDto;
import mktany2k.wcc.model.Location;

public class Mappers {
    public static LocationDto asDto(Location location) {
        return new LocationDto(
                location.getPostcode(),
                location.getLatitude(),
                location.getLongitude());
    }
}
