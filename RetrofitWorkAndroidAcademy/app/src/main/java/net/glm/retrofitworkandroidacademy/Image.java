package net.glm.retrofitworkandroidacademy;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class Image {

    @SerializedName("webformatURL")
    public String webformatURL;
    @SerializedName("likes")
    public Integer likes;
    @SerializedName("views")
    public Integer views;
    @SerializedName("downloads")
    public Integer downloads;
    @SerializedName("user")
    public String user;

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

    @BindingAdapter({"image"})
    public static void image(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }


}
