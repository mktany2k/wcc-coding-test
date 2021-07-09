package mktany2k.wcc.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import mktany2k.wcc.exception.ValidationException;

import java.util.ArrayList;

public final class LocationDto {

    private final String postalCode;

    private final Double latitude;

    private final Double longitude;

    @JsonCreator
    public LocationDto(String postalCode, double latitude, double longitude) {
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void validate() {
        var errors = new ArrayList<String>();
        if (postalCode == null || postalCode.isBlank()) {
            errors.add("postalCode cannot be blank");
        }
        if (latitude < -90.0 || latitude > 90.0) {
            errors.add("latitude must between -90.0 and 90.0 degree");
        }
        if (longitude < -180.0 || longitude > 180.0) {
            errors.add("longitude must between -180.0 and 180.0 degree");
        }

        if (errors.isEmpty()) {
            return;
        }
        throw new ValidationException(errors);
    }
}
