package mktany2k.wcc.dto;

public class Distance {

    private LocationDto from;
    private LocationDto to;
    private double distance;

    public LocationDto getFrom() {
        return from;
    }

    public void setFrom(LocationDto from) {
        this.from = from;
    }

    public LocationDto getTo() {
        return to;
    }

    public void setTo(LocationDto to) {
        this.to = to;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getUnit() {
        return "KM";
    }
}
