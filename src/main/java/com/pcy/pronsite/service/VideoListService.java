package com.pcy.pronsite.service;

import com.pcy.pronsite.dao.entity.User;


public interface VideoListService {
    Object getPrivateVideos(User principal, Integer limit, Integer page);
    Object getPlayUrl(Integer videoId);

    Object getByUserIdAndCategoryId(Integer privateVideo, Integer userId, Integer categoryId, Integer page, Integer size);

    Object getUserPublicVideos(Integer userId, Integer page, Integer size);

    Object getVideosByCategory(Integer categoryId, Integer page, Integer size);
}
