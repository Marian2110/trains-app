package ro.fasttrackit.trains.api.rest.model.response;

import lombok.Builder;

@Builder
public record PageInfo(
        int page,
        int size,
        int totalPages,
        int totalElements) {
}
