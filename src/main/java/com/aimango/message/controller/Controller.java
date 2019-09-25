package com.aimango.message.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/")
@RestController
public class Controller {

    @GetMapping(path = "test")
    public String test(){
        return "test";
    }
}
