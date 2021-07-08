package mktany2k.wcc.dto;

import mktany2k.wcc.service.Haversine;

public final class Distance {

    private final LocationDto from;
    private final LocationDto to;

    public Distance(LocationDto from, LocationDto to) {
        this.from = from;
        this.to = to;
    }

    public LocationDto getFrom() {
        return from;
    }

    public LocationDto getTo() {
        return to;
    }

    public double getDistance() {
        return Haversine.distance(from.getLatitude(), from.getLongitude(), to.getLatitude(), to.getLongitude());
    }

    public String getUnit() {
        return "KM";
    }
}
