package com.pcy.pronsite.controller;

import com.pcy.pronsite.service.UserManageService;
import com.pcy.pronsite.util.JacksonUtil;
import com.pcy.pronsite.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
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
    @PostMapping("login")
    public Object login(@RequestBody String body){
        return userManageService.login(body);
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Object logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return Result.ok();
    }
}
