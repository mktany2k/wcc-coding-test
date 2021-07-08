package mktany2k.wcc.dto;

public final class LocationDto {
    private final String postalCode;
    private final double latitude;
    private final double longitude;

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

}
