package com.example.spring.Security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //http 8080 통신 예제 2.2
    @GetMapping("/hello")
    public String hello(){
        return "hello ex1";
    }

    //OpenSSL 발급 후 http 8443 통신 예제 2.2
    @GetMapping("/hello/SSL")
    public String helloSSL(){
        return "hello ex1_https";
    }



}
