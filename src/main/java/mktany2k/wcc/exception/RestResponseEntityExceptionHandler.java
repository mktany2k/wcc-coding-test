package mktany2k.wcc.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(PostalCodeNotFoundException.class)
    public ResponseEntity<ApplicationError>
    handleLocationNotFoundException(
            PostalCodeNotFoundException ex
    ) {
        var error = new ApplicationError(List.of(ex.getMessage()));
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApplicationError>
    handleValidationException(
            ValidationException ex
    ) {
        var errors = new ApplicationError(ex.getErrors());
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
