package nvm.microservices.productservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<?> handle(ErrorException error) {
        return ResponseEntity
                .status(error.getStatus())
                .body(error.getErrors());
    }

}
