package ro.fasttrackit.trains.api.rest.model.filter;

import org.springframework.data.domain.Pageable;

public record TrainCriteria(
        String model,
        String maxCartsNumber,
        String minCartsNumber,
        String location,
        Pageable pageable) {
}
