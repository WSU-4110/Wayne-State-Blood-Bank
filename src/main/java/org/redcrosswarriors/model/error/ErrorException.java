package org.redcrosswarriors.model.error;

import org.springframework.http.HttpStatus;

public class ErrorException extends Exception {
    HttpStatus status;

    public ErrorException(String message, HttpStatus status){
        super(message);
        this.status =status;
    }

    public void setStatus(HttpStatus status){
        this.status = status;
    }

    public HttpStatus getStatus(){
        return this.status;
    }
}
