package com.pcy.pronsite.dao.service.impl;

import com.pcy.pronsite.dao.entity.Video;
import com.pcy.pronsite.dao.repo.VideoRepo;
import com.pcy.pronsite.dao.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/12 16:18
 */
@Service
public class VideoServiceImpl implements VideoService {
    VideoRepo repo;

    @Autowired
    public void setRepo(VideoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Page<Video> getVideos(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page-1,limit);
        return repo.findAllByPrivateVideoIsFalse(pageable);
    }

    @Override
    public Page<Video> getPrivateVideos(int userId, int page, int limit) {
        Pageable pageable = PageRequest.of(page-1,limit);
        return repo.findAllByUploadUserAndPrivateVideoIsTrue(userId,pageable);
    }

    @Override
    public Video getVideo(Integer videoId) {
        Optional<Video> byId = repo.findById(videoId);
        return byId.isPresent()?byId.get():null;
    }
}
