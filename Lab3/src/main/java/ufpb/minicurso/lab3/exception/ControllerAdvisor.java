package ufpb.minicurso.lab3.exception;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    ResponseEntity<JSONObject> handleServletException(ServletException e){
        JSONObject error = new JSONObject();
        error.put("stacktrace", e.getStackTrace());
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserPasswordNotValid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<JSONObject> handleUserPasswordNotValid(UserPasswordNotValid e){
        JSONObject error = new JSONObject();
        error.put("stacktrace", e.getStackTrace());
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<JSONObject> handleIllegalArgumentException(IllegalArgumentException e){
        JSONObject error = new JSONObject();
        error.put("stacktrace", e.getStackTrace());
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
