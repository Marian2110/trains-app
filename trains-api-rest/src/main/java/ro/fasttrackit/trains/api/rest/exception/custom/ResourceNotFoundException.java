package ro.fasttrackit.trains.api.rest.exception.custom;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class ResourceNotFoundException extends RuntimeException {
    private final String id;
    private final String field;
    private final String entityName;

    public ResourceNotFoundException(String id, String entityName) {
        super(entityName + " with id " + id + " not found");
        this.field = "id";
        this.entityName = entityName;
        this.id = id;
    }

    public ResourceNotFoundException(String field, String value, String entityName) {
        super(entityName + " with " + field + " " + value + " not found");
        this.entityName = entityName;
        this.field = field;
        this.id = value;
    }

    public static <T> ResourceNotFoundException getInstance(Class<T> entity, String id) {
        String entityName = entity.getSimpleName();
        log.error("Could not find " + entityName + " with id " + id);
        return ResourceNotFoundException.builder()
                .entityName(entityName)
                .id(id)
                .build();
    }

    public static <T> ResourceNotFoundException getInstance(Class<T> entity, String field, String value) {
        String entityName = entity.getSimpleName();
        log.error("Could not find " + entityName + " with " + field + "=" + value);
        return ResourceNotFoundException.builder()
                .entityName(entityName)
                .field(field)
                .id(value)
                .build();
    }
}
