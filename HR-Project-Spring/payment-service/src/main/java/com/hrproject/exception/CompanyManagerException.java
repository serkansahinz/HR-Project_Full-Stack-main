package com.hrproject.exception;

import lombok.Getter;

@Getter
public class CompanyManagerException extends RuntimeException {

    private final ErrorType errorType;

    public CompanyManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CompanyManagerException(ErrorType errorType,String customMessage){
        super(customMessage);
        this.errorType=errorType;
    }

}
