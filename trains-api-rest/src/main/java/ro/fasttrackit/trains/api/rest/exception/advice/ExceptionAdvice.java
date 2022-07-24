package ro.fasttrackit.trains.api.rest.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.fasttrackit.trains.api.rest.exception.custom.InvalidRouteException;
import ro.fasttrackit.trains.api.rest.exception.custom.InvalidTrainException;
import ro.fasttrackit.trains.api.rest.exception.custom.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ExceptionResponse handleResourceNotFound(ResourceNotFoundException ex) {
        return ExceptionResponse.builder()
                .internalCode("RNF01")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidTrainException.class)
    @ResponseStatus(BAD_REQUEST)
    ExceptionResponse handleResourceNotFound(InvalidTrainException ex) {
        return ExceptionResponse.builder()
                .internalCode("ITE01")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidRouteException.class)
    @ResponseStatus(BAD_REQUEST)
    ExceptionResponse handleResourceNotFound(InvalidRouteException ex) {
        return ExceptionResponse.builder()
                .internalCode("IRE01")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    ExceptionResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return ExceptionResponse.builder()
                .internalCode("IAE01")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    ExceptionResponse handleAllExceptions(Exception ex) {
        log.error("Generic error", ex);
        return ExceptionResponse.builder()
                .internalCode("GEN01")
                .message("Internal server error")
                .build();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    ExceptionResponse handleBindException(BindException ex) {
        List<FieldError> fieldErrors = new ArrayList<>();
        ex.getFieldErrors().forEach(fieldError ->
                fieldErrors.add(FieldError
                        .builder()
                        .field(fieldError.getField())
                        .errorMessage(fieldError.getDefaultMessage())
                        .build())
        );
        return ExceptionResponse.builder()
                .internalCode("BDE01")
                .message("Bad request for the following reasons:")
                .fieldErrors(fieldErrors)
                .build();
    }
}