package ro.fasttrackit.trains.api.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.trains.api.rest.model.entity.TrainEntity;

public interface TrainRepository extends MongoRepository<TrainEntity, String> {
}
