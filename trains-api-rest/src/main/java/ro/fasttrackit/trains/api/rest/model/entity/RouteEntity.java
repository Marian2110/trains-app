package ro.fasttrackit.trains.api.rest.model.entity;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Document(collection = "routes")
public record RouteEntity(
        @Id
        String id,
        String startLocationId,
        String destinationLocationId,
        String trainId,
        String length,
        @CreatedDate
        LocalDateTime createdOn,
        @LastModifiedDate
        LocalDateTime updatedOn) {
}
