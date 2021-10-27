package com.barry.exceptionshandler;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * Permet d'avoir ce format d'exception
 * { "code": xxx, "message": yyy}
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomCodeMessageException extends RuntimeException{

    private final String code;
    private final String message;
    private final HttpStatus status;
    @JsonIgnore
    private final List<String> messageParams;

    /**
     *
     * @param code
     * @param message
     * @param status
     */
    public CustomCodeMessageException(String code, String message, HttpStatus status) {
        this(code, message, null, status);
    }

    /**
     * @param code
     * @param message
     * @param messageParams
     * @param status
     */
    public CustomCodeMessageException(String code, String message, List<String> messageParams, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.messageParams = messageParams;
        this.status = status;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String getLocalizedMessage() {
        return null;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getMessageParams() {
        return messageParams;
    }

}
