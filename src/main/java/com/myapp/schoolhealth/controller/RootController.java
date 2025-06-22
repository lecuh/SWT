// src/main/java/com/myapp/schoolhealth/controller/RootController.java
package com.myapp.schoolhealth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

    @GetMapping
    public String hello() {
        return "Hello World!";
    }
}