package mktany2k.wcc.dto;

public class LocationDto {
    private String postalCode;
    private double longitude;
    private double latitude;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitute) {
        this.longitude = longitute;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
