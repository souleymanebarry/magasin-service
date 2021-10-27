package com.barry.helper;

import com.barry.OpenlabMagasinApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = OpenlabMagasinApplication.class)
class I18nHelperTest {


    @Autowired
    I18nHelper i18nHelper;

    @Test
    void getValue_shouldReturnValue_fromKey() {
        assertEquals("This is the value", i18nHelper.getValue("test.key"));
    }

    @Test
    void getValue_shouldReturnValueWithParams_fromKey() {
        assertEquals("This is the value with params test_param", i18nHelper.getValue("test.key.with.params", "test_param"));
    }
}
