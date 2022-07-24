package ro.fasttrackit.trains.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.trains.api.model.entity.Location;
import ro.fasttrackit.trains.api.rest.exception.custom.ResourceNotFoundException;
import ro.fasttrackit.trains.api.rest.model.entity.LocationEntity;
import ro.fasttrackit.trains.api.rest.model.mapper.LocationMapper;
import ro.fasttrackit.trains.api.rest.service.LocationService;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationMapper.toApi(locationService.createLocation(locationMapper.toEntity(location)));
    }

    @DeleteMapping("/{id}")
    public Location deleteLocation(@PathVariable String id) {
        LocationEntity location = locationService.getLocationById(id)
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Location.class, id));
        locationService.deleteLocation(location.id());
        return locationMapper.toApi(location);
    }
}
