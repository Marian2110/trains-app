package ro.fasttrackit.trains.api.rest.model.entity;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@With
@Builder
@Document(collection = "trains")
public record TrainEntity(
        @Id
        String id,
        String model,
        String carts,
        String locationId,
        @CreatedDate
        LocalDateTime createdOn,
        @LastModifiedDate
        LocalDateTime updatedOn) {
}
