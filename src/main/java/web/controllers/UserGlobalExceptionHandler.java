package web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import web.util.UserErrorResponse;
import web.util.UserNotFoundException;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handlerException(Exception e) {
        UserErrorResponse data = new UserErrorResponse();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handlerException(UserNotFoundException e) {
        UserErrorResponse data = new UserErrorResponse("User with this id wasn't found",
                System.currentTimeMillis());
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
