package com.pcy.pronsite.dao.repo;

import com.pcy.pronsite.dao.entity.Category;
import com.pcy.pronsite.dao.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface VideoRepo extends JpaRepository<Video,Integer> {
    Page<Video> findAllByUploadUserAndPrivateVideoIsTrue(int userId, Pageable pageable);
    Page<Video> findAllByPrivateVideoIsFalse(Pageable pageable);
    Page<Video> findAllByPrivateVideoIsFalseAndUploadUser(Integer userId,Pageable pageable);
}
