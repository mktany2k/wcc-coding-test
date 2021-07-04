package mktany2k.wcc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {
    @GetMapping("{from}/{to}")
    public Distance distance(
            @PathVariable("from") String from,
            @PathVariable("to") String to
    ) {
        Distance distance = new Distance();
        LocationDto fromLocation = new LocationDto();
        fromLocation.setPostalCode("from");
        fromLocation.setLongitute(0.0);
        fromLocation.setLatitude(0.0);
        LocationDto toLocation = new LocationDto();
        toLocation.setPostalCode("to");
        toLocation.setLongitute(1.1);
        toLocation.setLatitude(1.1);
        distance.setFrom(fromLocation);
        distance.setTo(toLocation);
        distance.setDistance(1D);

        return distance;
    }
}
