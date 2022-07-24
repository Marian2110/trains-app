package ro.fasttrackit.trains.api.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.trains.api.rest.model.entity.LocationEntity;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<LocationEntity, String> {
    Optional<LocationEntity> findByCity(String locationName);
}
