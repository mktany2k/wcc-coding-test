package mktany2k.wcc.controller;

import mktany2k.wcc.dto.LocationDto;
import mktany2k.wcc.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody LocationDto location) {
        location.validate();
        service.update(location);
        return ResponseEntity.accepted().build();
    }
}
