package ro.fasttrackit.trains.api.rest.model.mapper;

import org.springframework.stereotype.Component;
import ro.fasttrackit.trains.api.model.entity.Location;
import ro.fasttrackit.trains.api.rest.model.entity.LocationEntity;

@Component
public class LocationMapper implements ModelMapper<Location, LocationEntity> {

    @Override
    public Location toApi(LocationEntity entity) {
        return Location.builder()
                .id(entity.id())
                .city(entity.city())
                .build();
    }

    @Override
    public LocationEntity toEntity(Location entity) {
        return LocationEntity.builder()
                .id(entity.id())
                .city(entity.city())
                .build();
    }
}
