package com.isc.masrour.crudSample.sample.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = CustomNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundException(ChangeSetPersister.NotFoundException exception) {
        logger.error("NotFoundException Error: " + exception.getMessage());
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                exception.getMessage(), "Not Found");
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage classNotFoundException(ClassNotFoundException ex) {
        logger.error("InternalServerError : " + ex.getMessage());
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), "Class Not Found");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage accessDeniedException(AccessDeniedException ex) {
        logger.error("Forbidden Error :" + ex.getMessage());
        return new ErrorMessage(HttpStatus.FORBIDDEN.value(),
                ex.getMessage(), "Url is Forbidden ");
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage badCredentialsException(BadCredentialsException ex) {
        logger.error("BadCredentialsException Error:" + ex.getMessage());
        return new ErrorMessage(HttpStatus.FORBIDDEN.value(),
                ex.getMessage(), "Credential Is forbidden");
    }


}
