package com.pcy.pronsite.service.impl;

import com.pcy.pronsite.dao.entity.User;
import com.pcy.pronsite.dao.service.UserService;
import com.pcy.pronsite.service.UserManageService;
import com.pcy.pronsite.util.JacksonUtil;
import com.pcy.pronsite.util.Result;
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
    public Result addUser(String data) {
        String name = JacksonUtil.parseString(data,"name");
        String password = JacksonUtil.parseString(data,"password");
        String email = JacksonUtil.parseString(data,"email");
        Result result = null;
        if ((result = validate(data, password, email)) != null) {
            return result;
        }
        User byNameAndEmail = userService.findByNameAndEmail(data, email);
        if (byNameAndEmail != null) {
            return new Result(500, "用户名或邮箱已存在！");
        }
        User user = new User();
        user.setName(data);
        user.setPassword(password);
        user.setEmail(email);
        boolean add = userService.add(user);
        return add ? Result.ok("注册成功") : Result.fail("注册失败内部错误！");
    }

    private Result validate(String name, String password, String email) {
        return null;
    }
}
