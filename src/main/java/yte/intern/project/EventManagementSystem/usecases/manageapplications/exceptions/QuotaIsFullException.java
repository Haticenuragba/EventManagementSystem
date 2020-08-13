package yte.intern.project.EventManagementSystem.usecases.manageapplications.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Bu etkinliğin kontenjanı dolu.")
public class QuotaIsFullException extends RuntimeException{
    public QuotaIsFullException(String msg){
        super(msg);
    }
}
