package com.barry.controllers;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface HomeController {

    /**
     * Redirect to Swagger UI
     * @return "redirect:/swagger-ui.html"
     */
    @GetMapping(value ="${swagger-ui.redirect:}")
    @ApiOperation(value = "Redirect to swagger-ui.html")
    String redirectToSwaggerui();
}
