package com.pcy.pronsite.service.impl;

import com.pcy.pronsite.dao.entity.User;
import com.pcy.pronsite.dao.entity.Video;
import com.pcy.pronsite.dao.service.VideoService;
import com.pcy.pronsite.service.VideoListService;
import com.pcy.pronsite.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return ResponseUtil.ok(videos);
    }

    @Override
    public Object getPlayUrl(Integer videoId) {
        Video video = videoService.getVideo(videoId);
        if (video == null) {
            return ResponseUtil.badArgumentValue();
        }
        return ResponseUtil.ok(videoServer + video.getVideoPath());
    }
}
