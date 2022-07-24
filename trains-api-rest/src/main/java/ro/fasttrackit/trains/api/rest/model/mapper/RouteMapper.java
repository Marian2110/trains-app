package ro.fasttrackit.trains.api.rest.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.trains.api.model.entity.Location;
import ro.fasttrackit.trains.api.model.entity.Route;
import ro.fasttrackit.trains.api.model.entity.Train;
import ro.fasttrackit.trains.api.rest.exception.custom.ResourceNotFoundException;
import ro.fasttrackit.trains.api.rest.model.entity.LocationEntity;
import ro.fasttrackit.trains.api.rest.model.entity.RouteEntity;
import ro.fasttrackit.trains.api.rest.service.LocationService;
import ro.fasttrackit.trains.api.rest.service.TrainService;

@Component
@RequiredArgsConstructor
public class RouteMapper implements ModelMapper<Route, RouteEntity> {
    private final LocationService locationService;
    private final TrainService trainService;
    private final TrainMapper trainMapper;

    @Override
    public Route toApi(RouteEntity entity) {
        return Route.builder()
                .id(entity.id())
                .start(getLocationCity(entity.startLocationId()))
                .destination(getLocationCity(entity.destinationLocationId()))
                .train(getTrainById(entity.trainId()))
                .build();
    }

    private Train getTrainById(String trainId) {
        return trainService
                .getTrain(trainId)
                .map(trainMapper::toApi)
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Train.class, trainId));
    }

    private String getLocationCity(String startLocationId) {
        return locationService
                .getLocationById(startLocationId)
                .map(LocationEntity::city)
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Location.class, startLocationId));
    }

    @Override
    public RouteEntity toEntity(Route entity) {
        return null;
    }
}
