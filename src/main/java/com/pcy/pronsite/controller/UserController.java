package com.pcy.pronsite.controller;

import com.pcy.pronsite.service.UserManageService;
import com.pcy.pronsite.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/11 15:47
 */
@RestController
@RequestMapping("user")
public class UserController {
    UserManageService userManageService;

    @Autowired
    public void setUserManageService(UserManageService userManageService) {
        this.userManageService = userManageService;
    }

    @PostMapping("register")
    public Object register(@RequestBody String body){

        return userManageService.addUser(body);
    }
}
