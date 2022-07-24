package ro.fasttrackit.trains.api.rest.exception.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FieldError(String field, String errorMessage, String suggestion) {
}
