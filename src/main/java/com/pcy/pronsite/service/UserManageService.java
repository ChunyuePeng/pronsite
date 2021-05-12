package com.pcy.pronsite.service;

import com.pcy.pronsite.util.Result;

/**
 * 管理用户
 * @author chunyue_peng
 */
public interface UserManageService {
    /**
     * 添加用户
     *
     * @param data
     * @return
     */
    Object addUser(String data);

    Object login(String body);
}
