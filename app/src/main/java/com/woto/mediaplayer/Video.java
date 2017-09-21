package com.woto.mediaplayer;

/**
 * Created by fangxubin on 2017/6/15.
 */

public class Video {
    //视频略缩图
    private int imageId;
    //视频名字
    private String name;
    //视频路径
    private String path;

    public Video(int imageId,String name,String path){
        this.imageId = imageId;
        this.name = name;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


