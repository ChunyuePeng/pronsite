package com.pcy.pronsite.service.impl;

import com.pcy.pronsite.dao.entity.User;
import com.pcy.pronsite.dao.service.UserService;
import com.pcy.pronsite.service.UserManageService;
import com.pcy.pronsite.util.JacksonUtil;
import com.pcy.pronsite.util.ResponseUtil;
import com.pcy.pronsite.util.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/11 15:57
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object addUser(String data) {
        String name = JacksonUtil.parseString(data,"name");
        String password = JacksonUtil.parseString(data,"password");
        String email = JacksonUtil.parseString(data,"email");
        Result result = null;
        if ((result = validate(data, password, email)) != null) {
            return result;
        }
        User byNameAndEmail = userService.findByNameAndEmail(data, email);
        if (byNameAndEmail != null) {
            //return new Result(500, "用户名或邮箱已存在！");
            return ResponseUtil.fail(500,"用户名或邮箱已存在！");
        }
        User user = new User();
        user.setName(data);
        user.setPassword(password);
        user.setEmail(email);
        boolean add = userService.add(user);
        return add ? ResponseUtil.ok() : ResponseUtil.fail();
    }

    @Override
    public Object login(String data) {
        String password = JacksonUtil.parseString(data,"password");
        String email = JacksonUtil.parseString(data,"email");
        if (StringUtils.isEmpty(password)||StringUtils.isEmpty(email)){
            return ResponseUtil.badArgument();
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(email, password);
        try {
            subject.login(usernamePasswordToken);
            subject = SecurityUtils.getSubject();
            String token = (String) subject.getSession().getId();
            return ResponseUtil.ok(token);
        } catch (UnknownAccountException e) {
            return ResponseUtil.fail(1000,"用户名或密码错误！");
        } catch (AuthenticationException e) {
            return ResponseUtil.fail(1000,"用户名或密码错误！");
        } catch (AuthorizationException e) {
            return ResponseUtil.unauthz();
        }
    }

    private Result validate(String name, String password, String email) {
        return null;
    }

}
