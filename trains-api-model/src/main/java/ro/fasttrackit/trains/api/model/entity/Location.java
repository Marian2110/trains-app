package ro.fasttrackit.trains.api.model.entity;

import lombok.Builder;

@Builder
public record Location(
        String id,
        String city) {
}
