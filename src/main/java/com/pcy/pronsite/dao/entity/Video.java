package com.pcy.pronsite.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video {
    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", coverImgPath='" + coverImgPath + '\'' +
                ", uploadUser=" + uploadUser +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private String videoPath;
    private String coverImgPath;
    private int uploadUser;
    private boolean privateVideo;

    public boolean isPrivateVideo() {
        return privateVideo;
    }

    public void setPrivateVideo(boolean privateVideo) {
        this.privateVideo = privateVideo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getCoverImgPath() {
        return coverImgPath;
    }

    public void setCoverImgPath(String coverImgPath) {
        this.coverImgPath = coverImgPath;
    }

    public int getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(int uploadUser) {
        this.uploadUser = uploadUser;
    }
}
