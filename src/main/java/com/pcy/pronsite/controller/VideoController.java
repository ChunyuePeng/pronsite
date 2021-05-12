package com.pcy.pronsite.controller;

import com.pcy.pronsite.dao.entity.User;
import com.pcy.pronsite.service.VideoListService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Object getPrivateVideos(Integer limit,Integer page){
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        return videoListService.getPrivateVideos(principal,limit,page);
    }
    @GetMapping("getPlayPath")
    public Object getPlayPath(Integer videoId){
        return videoListService.getPlayUrl(videoId);
    }
}
