package pl.softr.cms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PostAdvice {

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    String postNotFundHandler(PostNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(IncompletePostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    String incompletePostHandler(IncompletePostException ex) {
        return ex.getMessage();
    }
}
