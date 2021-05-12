package com.pcy.pronsite.dao.repo;

import com.pcy.pronsite.dao.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VideoRepo extends JpaRepository<Video,Integer> {
    Page<Video> findAllByUploadUserAndPrivateVideoIsTrue(int userId, Pageable pageable);
    Page<Video> findAllByPrivateVideoIsFalse(Pageable pageable);
}
