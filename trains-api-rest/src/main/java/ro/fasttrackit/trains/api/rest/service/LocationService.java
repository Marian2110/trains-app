package ro.fasttrackit.trains.api.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.trains.api.rest.model.entity.LocationEntity;
import ro.fasttrackit.trains.api.rest.repository.LocationRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public Optional<LocationEntity> getLocationById(String id) {
        return locationRepository.findById(id);
    }

    public Optional<LocationEntity> getLocationByCity(String location) {
        return locationRepository.findByCity(location);
    }

    public LocationEntity createLocation(LocationEntity location) {
        return locationRepository.save(location.withId(null));
    }

    public void deleteLocation(String id) {
        locationRepository.deleteById(id);
    }
}
