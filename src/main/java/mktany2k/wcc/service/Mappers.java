package mktany2k.wcc.service;

import mktany2k.wcc.dto.LocationDto;
import mktany2k.wcc.model.Location;

public class Mappers {
    public static LocationDto from(Location location) {
        var dto = new LocationDto();
        dto.setPostalCode(location.getPostcode());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());
        return dto;
    }
}
