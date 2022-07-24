package ro.fasttrackit.trains.api.rest.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.trains.api.model.entity.Location;
import ro.fasttrackit.trains.api.rest.exception.custom.ResourceNotFoundException;
import ro.fasttrackit.trains.api.rest.model.entity.LocationEntity;
import ro.fasttrackit.trains.api.rest.model.entity.TrainEntity;
import ro.fasttrackit.trains.api.rest.model.filter.TrainCriteria;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.data.support.PageableExecutionUtils.getPage;

@Repository
@RequiredArgsConstructor
public class TrainDAO {
    private final MongoTemplate mongoTemplate;
    private final LocationRepository locationRepository;

    public Page<TrainEntity> find(TrainCriteria filter) {
        Criteria criteriaQuery = new Criteria();
        ofNullable(filter.model()).ifPresent(model -> criteriaQuery.and("model").is(model));
        ofNullable(filter.location()).ifPresent(location -> criteriaQuery.and("locationId").is(getLocationId(location)));
        ofNullable(filter.minCartsNumber()).ifPresent(minCartsNumber -> criteriaQuery.and("minCartsNumber").gt(minCartsNumber));
        ofNullable(filter.maxCartsNumber()).ifPresent(maxCartsNumber -> criteriaQuery.and("maxCartsNumber").lt(maxCartsNumber));

        Query query = new Query(criteriaQuery).with(filter.pageable());

        List<TrainEntity> trains = mongoTemplate.find(query, TrainEntity.class);

        return getPage(trains, filter.pageable(), () -> mongoTemplate.count(query, TrainEntity.class));
    }

    private String getLocationId(String location) {
        return locationRepository
                .findByCity(location)
                .map(LocationEntity::id)
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Location.class, "city", location));
    }

}
