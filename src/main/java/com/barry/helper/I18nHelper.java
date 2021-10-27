package com.barry.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Helper for i18n. Use Message
 */
@Component
public class I18nHelper {

    private MessageSource messageSource;

    @Autowired
    public I18nHelper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getValue(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    public String getValue(String key, Object... params) {
        return messageSource.getMessage(key, params, Locale.getDefault());
    }
}
