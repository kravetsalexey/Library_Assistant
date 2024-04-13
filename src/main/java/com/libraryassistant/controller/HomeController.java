package com.libraryassistant.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {

    @RequestMapping("/")
    @Hidden
    public String home(){
        return "Hello World!";
    }
}