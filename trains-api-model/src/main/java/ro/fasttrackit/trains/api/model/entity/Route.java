package ro.fasttrackit.trains.api.model.entity;

import lombok.Builder;

@Builder
public record Route(
        String id,
        String start,
        String destination,
        String distance,
        Train train) {
}
