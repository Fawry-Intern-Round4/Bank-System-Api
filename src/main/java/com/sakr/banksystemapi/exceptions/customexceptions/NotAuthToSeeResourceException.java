package com.sakr.banksystemapi.exceptions.customexceptions;

public class NotAuthToSeeResourceException extends RuntimeException{

    public NotAuthToSeeResourceException(String message) {
        super(message);
    }

    public NotAuthToSeeResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthToSeeResourceException(Throwable cause) {
        super(cause);
    }

}
