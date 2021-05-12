package com.pcy.pronsite.service;

import com.pcy.pronsite.dao.entity.User;


public interface VideoListService {
    Object getPrivateVideos(User principal, Integer limit, Integer page);
    Object getPlayUrl(Integer videoId);
}
