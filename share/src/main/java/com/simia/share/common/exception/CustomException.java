package com.simia.share.common.exception;

import com.simia.share.common.exception.messages.ExceptionMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Getter
@Setter
public class CustomException extends RuntimeException {

    private int errorCode;

    private int httpCode;

    private String message;

    private String originalPath;

    public CustomException(ExceptionMessage exceptionMessage, String originalPath) {
        super(exceptionMessage.getMessage());
        this.errorCode = exceptionMessage.getErrorCode();
        this.httpCode = exceptionMessage.getHttpCode();
        this.message = exceptionMessage.getMessage();
        this.originalPath = originalPath;
    }

    public CustomException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.errorCode = exceptionMessage.getErrorCode();
        this.httpCode = exceptionMessage.getHttpCode();
        this.message = exceptionMessage.getMessage();
    }

    public CustomException(ExceptionMessage exceptionMessage, Throwable cause) {
        super(exceptionMessage.getMessage(), cause);
        this.errorCode = exceptionMessage.getErrorCode();
        this.httpCode = exceptionMessage.getHttpCode();
        this.message = exceptionMessage.getMessage();
    }

    public CustomException(String message, int errorCode, int httpCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.message = message;
    }

    public CustomException(String message, int errorCode, int httpCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.message = message;
    }

    public CustomException(String message, int httpCode) {
        super(message);
        this.httpCode = httpCode;
        this.message = message;
    }
}
