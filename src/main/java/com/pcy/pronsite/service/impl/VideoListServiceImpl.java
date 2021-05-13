package com.pcy.pronsite.service.impl;

import com.pcy.pronsite.dao.entity.Category;
import com.pcy.pronsite.dao.entity.User;
import com.pcy.pronsite.dao.entity.Video;
import com.pcy.pronsite.dao.service.VideoService;
import com.pcy.pronsite.service.VideoListService;
import com.pcy.pronsite.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/12 15:45
 */
@Service
public class VideoListServiceImpl implements VideoListService {
    private String imgServer;
    private String videoServer;

    @Value("${static.img-server}")
    public void setImgServer(String imgServer) {
        this.imgServer = imgServer;
    }

    @Value("${static.video-server}")
    public void setVideoServer(String videoServer) {
        this.videoServer = videoServer;
    }

    VideoService videoService;

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    @Override
    public Object getPrivateVideos(User principal, Integer limit, Integer page) {
        Page<Video> videos = null;
        if (limit == null) {
            videos = videoService.getPrivateVideos(principal.getId(), 1, 5);
        } else {
            videos = videoService.getPrivateVideos(principal.getId(), page, limit);
        }
        List<Video> content = videos.getContent();
        for (Video video : content) {
            video.setCoverImgPath(imgServer + video.getCoverImgPath());
            video.setVideoPath(videoServer + video.getVideoPath());
        }
        return ResponseUtil.okList(videos);
    }

    @Override
    public Object getPlayUrl(Integer videoId) {
        Video video = videoService.getVideo(videoId);
        if (video == null) {
            return ResponseUtil.badArgumentValue();
        }
        video.setVideoPath(videoServer + video.getVideoPath());
        return ResponseUtil.ok(video);
    }

    @Override
    public Object getByUserIdAndCategoryId(Integer privateVideo, Integer userId, Integer categoryId, Integer page, Integer size) {
        if (page == null || size == null) {
            return ResponseUtil.badArgument();
        }
        Video video = new Video();
        if (privateVideo!=null){
            if (privateVideo==1){
                video.setPrivateVideo(privateVideo==1);
            }
        }
        if (userId != null) {
            video.setUploadUser(userId);
        }
        if (categoryId != null) {
            Category category = new Category();
            category.setId(categoryId);
            Set<Category> categories = new HashSet<>(1);
            categories.add(category);
            video.setCategories(categories);
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Example<Video> e = Example.of(video);
        Page<Video> videos = videoService.getVideos(e, pageable);
        List<Video> content = videos.getContent();
        for (Video v : content) {
            v.setCoverImgPath(imgServer + v.getCoverImgPath());
            v.setVideoPath(videoServer + v.getVideoPath());
        }
        return ResponseUtil.okList(videos);
    }

    @Override
    public Object getUserPublicVideos(Integer userId, Integer page, Integer size) {
        return videoService.getPublicVideosByUser(userId,page,size);
    }

    @Override
    public Object getVideosByCategory(Integer categoryId, Integer page, Integer size) {
        return videoService.getVideosByCategory(categoryId,page,size);
    }
}
