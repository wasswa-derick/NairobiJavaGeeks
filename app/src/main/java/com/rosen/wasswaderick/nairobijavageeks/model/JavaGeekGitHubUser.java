package com.rosen.wasswaderick.nairobijavageeks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JavaGeekGitHubUser {

    @SerializedName("avatar_url")
    @Expose
    private String image;

    @SerializedName("login")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String developEmail;

    @SerializedName("followers_url")
    @Expose
    private String followers;

    @SerializedName("following_url")
    @Expose
    private String following;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDevelopEmail() {
        return developEmail;
    }

    public void setDevelopEmail(String developEmail) {
        this.developEmail = developEmail;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
}
