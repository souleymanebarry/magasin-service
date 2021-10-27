package com.barry.controllers.impl;

import com.barry.controllers.HomeController;
import org.springframework.stereotype.Controller;

@Controller
public class HomeControllerImpl implements HomeController {

    @Override
    public String redirectToSwaggerui() {
        return "redirect:/swagger-ui.html";
    }
}
