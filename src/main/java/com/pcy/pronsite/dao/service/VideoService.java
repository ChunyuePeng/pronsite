package com.pcy.pronsite.dao.service;

import com.pcy.pronsite.dao.entity.Category;
import com.pcy.pronsite.dao.entity.Video;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface VideoService {
    Page<Video> getVideos(Integer page, Integer limit);
    Page<Video> getPrivateVideos(int userId,int page,int limit);
    Video getVideo(Integer videoId);
    Page<Video> getVideos(Example example, Pageable pageable);
    Page<Video> getVideosByCategory(Integer categoryId,Integer page, Integer limit);
    Page<Video> getPublicVideosByUser(Integer userId,Integer page, Integer limit);
}
