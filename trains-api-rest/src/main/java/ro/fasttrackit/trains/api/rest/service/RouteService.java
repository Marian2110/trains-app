package ro.fasttrackit.trains.api.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.trains.api.rest.exception.custom.InvalidRouteException;
import ro.fasttrackit.trains.api.rest.model.entity.RouteEntity;
import ro.fasttrackit.trains.api.rest.repository.RouteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final LocationService locationService;
    private final TrainService trainService;

    public RouteEntity createRoute(RouteEntity toEntity) {
        return routeRepository.save(verifyRoute(toEntity));
    }

    private RouteEntity verifyRoute(RouteEntity route) {
        return Optional.of(route)
                .map(this::validateStartLocation)
                .map(this::validateEndLocation)
                .map(this::verifyTrainLocation)
                .orElseThrow(() -> new InvalidRouteException("Invalid route"));
    }

    private RouteEntity verifyTrainLocation(RouteEntity route) {
        return trainService.getTrain(route.trainId())
                .filter(train -> train.locationId().equals(route.startLocationId()))
                .map(train -> route)
                .orElseThrow(() -> new InvalidRouteException("Train location does not match start location"));
    }

    private RouteEntity validateEndLocation(RouteEntity route) {
        return locationService.getLocationById(route.destinationLocationId())
                .map(r -> route)
                .orElseThrow(() -> new InvalidRouteException("End location does not exist"));
    }

    private RouteEntity validateStartLocation(RouteEntity route) {
        return locationService.getLocationById(route.destinationLocationId())
                .map(location -> route)
                .orElseThrow(() -> new InvalidRouteException("Start location does not exist"));
    }
}
