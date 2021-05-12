package com.pcy.pronsite.dao.service;

import com.pcy.pronsite.dao.entity.Video;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VideoService {
    Page<Video> getVideos(Integer page, Integer limit);
    Page<Video> getPrivateVideos(int userId,int page,int limit);

    Video getVideo(Integer videoId);
}
