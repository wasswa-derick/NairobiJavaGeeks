package com.rosen.wasswaderick.nairobijavageeks.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JavaGeekGitHubUser implements Parcelable{

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


    protected JavaGeekGitHubUser(Parcel in) {
        image = in.readString();
        username = in.readString();
        developEmail = in.readString();
        followers = in.readString();
        following = in.readString();
    }

    public static final Parcelable.Creator<JavaGeekGitHubUser> CREATOR = new Creator<JavaGeekGitHubUser>() {
        @Override
        public JavaGeekGitHubUser createFromParcel(Parcel in) {
            return new JavaGeekGitHubUser(in);
        }

        @Override
        public JavaGeekGitHubUser[] newArray(int size) {
            return new JavaGeekGitHubUser[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(username);
        dest.writeString(developEmail);
        dest.writeString(followers);
        dest.writeString(following);
    }
}
