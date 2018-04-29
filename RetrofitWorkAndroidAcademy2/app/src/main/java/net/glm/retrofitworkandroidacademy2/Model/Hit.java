package net.glm.retrofitworkandroidacademy2.Model;

import com.google.gson.annotations.SerializedName;

public class Hit {

    @SerializedName("webformatURL")
    private String webformatURL;
    @SerializedName("likes")
    private Integer likes;
    @SerializedName("views")
    private Integer views;
    @SerializedName("downloads")
    private Integer downloads;
    @SerializedName("user")
    private String user;

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }


    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }


    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
