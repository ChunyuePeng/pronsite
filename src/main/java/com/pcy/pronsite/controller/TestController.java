package com.pcy.pronsite.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("test")
    public Object test(@RequestBody String body){
        System.out.println(body);
        return "ok";
    }
}
