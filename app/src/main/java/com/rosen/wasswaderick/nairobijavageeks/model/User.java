package com.rosen.wasswaderick.nairobijavageeks.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Derick W on 26,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public class User implements Parcelable{

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("blog")
    @Expose
    private String blog;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("bio")
    @Expose
    private String bio;

    @SerializedName("followers")
    @Expose
    private String followers;

    @SerializedName("following")
    @Expose
    private String following;

    @SerializedName("public_repos")
    @Expose
    private String publicRepos;

    public User(){}

    public User(String name, String company, String blog, String location, String bio, String followers, String following, String publicRepos) {
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.bio = bio;
        this.followers = followers;
        this.following = following;
        this.publicRepos = publicRepos;
    }

    protected User(Parcel in) {
        name = in.readString();
        company = in.readString();
        blog = in.readString();
        location = in.readString();
        bio = in.readString();
        followers = in.readString();
        following = in.readString();
        publicRepos = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public String getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(String publicRepos) {
        this.publicRepos = publicRepos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(blog);
        dest.writeString(location);
        dest.writeString(bio);
        dest.writeString(followers);
        dest.writeString(following);
        dest.writeString(publicRepos);
    }
}
