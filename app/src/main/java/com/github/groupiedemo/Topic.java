package com.github.groupiedemo;

/**
 * @author nightkyb created at 2019/6/19 15:50
 */
public class Topic {
    private int topicId;
    private String username;
    private String content;
    // private String images;
    private boolean hasImage;
    private String time;

  /*  public Topic(int topicId, String username, String content, String images, String time) {
        this.topicId = topicId;
        this.username = username;
        this.content = content;
        this.images = images;
        this.time = time;
    }*/

    public Topic(int topicId, String username, String content, boolean hasImage, String time) {
        this.topicId = topicId;
        this.username = username;
        this.content = content;
        this.hasImage = hasImage;
        this.time = time;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }
}
