package ro.fasttrackit.trains.api.rest.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.trains.api.model.entity.Location;
import ro.fasttrackit.trains.api.model.entity.Train;
import ro.fasttrackit.trains.api.rest.exception.custom.ResourceNotFoundException;
import ro.fasttrackit.trains.api.rest.model.entity.LocationEntity;
import ro.fasttrackit.trains.api.rest.model.entity.TrainEntity;
import ro.fasttrackit.trains.api.rest.service.LocationService;

@Component
@RequiredArgsConstructor
public class TrainMapper implements ModelMapper<Train, TrainEntity> {
    private LocationService locationService;

    @Override
    public Train toApi(TrainEntity entity) {
        return Train.builder()
                .id(entity.id())
                .model(entity.model())
                .carts(entity.carts())
                .location(getLocationCity(entity))
                .build();
    }

    @Override
    public TrainEntity toEntity(Train api) {
        return TrainEntity.builder()
                .id(api.id())
                .model(api.model())
                .carts(api.carts())
                .locationId(getLocationId(api))
                .build();
    }

    private String getLocationCity(TrainEntity entity) {
        return locationService
                .getLocationById(entity.locationId())
                .map(LocationEntity::city)
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Location.class, entity.locationId()));
    }

    private String getLocationId(Train api) {
        return locationService
                .getLocationByCity(api.location())
                .map(LocationEntity::id)
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Location.class, "city", api.location()));
    }

}
