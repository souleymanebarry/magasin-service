package com.barry.controller;


import com.barry.controllers.impl.HomeControllerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HomeControllerImplTest {

    @InjectMocks
    HomeControllerImpl homeController;

    @Test
    void redirectToSwaggerUi_shouldReturnARedirectStringToSwaggerUi() {
        assertEquals("redirect:/swagger-ui.html", homeController.redirectToSwaggerui());
    }
}
