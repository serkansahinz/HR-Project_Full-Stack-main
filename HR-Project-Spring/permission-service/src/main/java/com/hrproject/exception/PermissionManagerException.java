package com.hrproject.exception;

import lombok.Getter;

@Getter
public class PermissionManagerException extends RuntimeException {

    private final ErrorType errorType;

    public PermissionManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public PermissionManagerException(ErrorType errorType,String customMessage){
        super(customMessage);
        this.errorType=errorType;
    }

}
