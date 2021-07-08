package mktany2k.wcc.controller.exception;

import mktany2k.wcc.model.ApplicationError;
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
}
