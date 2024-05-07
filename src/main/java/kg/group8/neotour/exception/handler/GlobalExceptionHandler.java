package kg.group8.neotour.exception.handler;

import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleEmptyFieldException(EmptyFieldException ex) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getClass().getName(), ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleProductNotFoundException(ProductNotFoundException ex) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(), ex.getMessage());
    }
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleIOException(IOException ex) {
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getClass().getName(), ex.getMessage());
    }
}
