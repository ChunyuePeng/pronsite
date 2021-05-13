package com.pcy.pronsite.controller;

import com.pcy.pronsite.dao.entity.User;
import com.pcy.pronsite.service.VideoListService;
import com.pcy.pronsite.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/12 15:42
 */
@RestController
@RequestMapping("video")
public class VideoController {
    VideoListService videoListService;

    @Autowired
    public void setVideoListService(VideoListService videoListService) {
        this.videoListService = videoListService;
    }

    @RequiresAuthentication
    @GetMapping("private")
    public Object getPrivateVideos(Integer limit, Integer page) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        return videoListService.getPrivateVideos(principal, limit, page);
    }

    @GetMapping("getVideoInfo")
    public Object getPlayPath(Integer videoId) {
        return videoListService.getPlayUrl(videoId);
    }

    @RequiresAuthentication
    @GetMapping("getVideos")
    public Object getVideos(Integer userId, Integer categoryId,
                            Integer page,
                            Integer size, Integer pv) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        //查询private video
        if (pv == 1){
            return videoListService.getPrivateVideos(principal,size,page);
        }
        //查询该用户的公开视频
        if (userId !=null){
            return videoListService.getUserPublicVideos(userId,page,size);
        }
        //查询分类视频
        if (categoryId !=null){
            return videoListService.getVideosByCategory(categoryId,page,size);
        }
        return ResponseUtil.badArgumentValue();
    }
}
