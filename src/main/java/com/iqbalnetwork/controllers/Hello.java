package com.iqbalnetwork.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Hello {
    @GetMapping("/greet/{name}")
    public String hello(@PathVariable("name") String name) {
        return "Hello!"+name;
    }
    @GetMapping
    public String hello2(){
        return "Hello from other side";
    }

}
