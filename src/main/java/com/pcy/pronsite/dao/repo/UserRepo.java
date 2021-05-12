package com.pcy.pronsite.dao.repo;

import com.pcy.pronsite.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findTopByNameOrEmail(String name,String email);
    User findTopByEmail(String email);
}
