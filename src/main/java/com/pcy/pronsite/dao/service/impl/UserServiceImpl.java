package com.pcy.pronsite.dao.service.impl;

import com.pcy.pronsite.dao.entity.User;
import com.pcy.pronsite.dao.repo.UserRepo;
import com.pcy.pronsite.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/11 16:29
 */
@Service
public class UserServiceImpl implements UserService {
    UserRepo repo;

    @Autowired
    public void setRepo(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public boolean add(User user) {
        User save = repo.save(user);
        return save.getId() != 0;
    }

    @Override
    public User findByNameAndEmail(String name, String email) {
        return repo.findTopByNameOrEmail(name,email);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findTopByEmail(email);
    }
}
