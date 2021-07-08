package mktany2k.wcc.controller;

import mktany2k.wcc.dto.Distance;
import mktany2k.wcc.service.DistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/distance")
public class DistanceController {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final DistanceService service;

    public DistanceController(DistanceService service) {
        this.service = service;
    }

    @GetMapping("{from}/{to}")
    public ResponseEntity<Distance> distance(
            @PathVariable("from") String from,
            @PathVariable("to") String to
    ) {
        logger.debug("[request] {from: '{}', to: '{}'}", from, to);
        var distance = service.calculateDistance(from, to);
        return ResponseEntity.ok(distance);
    }
}
