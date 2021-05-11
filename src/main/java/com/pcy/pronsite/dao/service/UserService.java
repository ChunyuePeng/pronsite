package com.pcy.pronsite.dao.service;

import com.pcy.pronsite.dao.entity.User;

public interface UserService {
    boolean add(User user);
    User findByNameAndEmail(String name,String email);
}
