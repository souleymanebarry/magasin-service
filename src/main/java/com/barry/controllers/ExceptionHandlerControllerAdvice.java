package com.barry.controllers;


import com.barry.exceptionshandler.CustomCodeMessageException;
import com.barry.helper.I18nHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  Custom Exception Handler
 */

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {


    private final I18nHelper i18nHelper;

    public ExceptionHandlerControllerAdvice(I18nHelper i18nHelper) {
        this.i18nHelper = i18nHelper;
    }

    @ExceptionHandler(CustomCodeMessageException.class)
    public ResponseEntity<CustomCodeMessageException> handleCustomCodeMessageException(
            CustomCodeMessageException exceptionCustom) {
        if (CollectionUtils.isEmpty(exceptionCustom.getMessageParams())) {
            return new ResponseEntity<>(new CustomCodeMessageException(i18nHelper.getValue(exceptionCustom.getCode()),
                    i18nHelper.getValue(exceptionCustom.getMessage()), exceptionCustom.getStatus()),
                    exceptionCustom.getStatus());
        } else {
            return new ResponseEntity<>(new CustomCodeMessageException(i18nHelper.getValue(exceptionCustom.getCode()),
                    i18nHelper.getValue(exceptionCustom.getMessage(), exceptionCustom.getMessageParams()),
                    exceptionCustom.getStatus()),
                    exceptionCustom.getStatus());
        }
    }
}
