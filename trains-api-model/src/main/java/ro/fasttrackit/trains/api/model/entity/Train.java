package ro.fasttrackit.trains.api.model.entity;

import lombok.Builder;

@Builder
public record Train(
        String id,
        String model,
        String carts,
        String location) {
}
