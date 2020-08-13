package yte.intern.project.EventManagementSystem.common.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }
}
