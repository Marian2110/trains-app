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
@Document(collection = "locations")
public record LocationEntity(
        @Id
        String id,
        String city,
        @CreatedDate
        LocalDateTime createdOn,
        @LastModifiedDate
        LocalDateTime updatedOn) {
}
