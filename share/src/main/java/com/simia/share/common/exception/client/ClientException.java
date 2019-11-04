package com.simia.share.common.exception.client;

import com.simia.share.common.exception.CustomException;
import com.simia.share.common.exception.messages.ExceptionMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Getter
@Setter
public class ClientException extends CustomException {

    public ClientException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage);
    }

    public ClientException(ExceptionMessage exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public ClientException(String message, int errorCode, int httpCode) {
        super(message, errorCode, httpCode);
    }

    public ClientException(String message, int errorCode, int httpCode, Throwable cause) {
        super(message, errorCode, httpCode, cause);
    }
}
