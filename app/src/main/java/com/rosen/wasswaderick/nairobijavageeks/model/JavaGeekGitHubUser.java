package com.rosen.wasswaderick.nairobijavageeks.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "github_user")
public class JavaGeekGitHubUser implements Parcelable{

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    @Expose
    private String image;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    @SerializedName("login")
    @Expose
    private String username;

    @ColumnInfo(name = "company")
    @SerializedName("company")
    @Expose
    private String company;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    private String url;

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    @Ignore
    private User details;

    @Ignore
    public JavaGeekGitHubUser(){}

    @Ignore
    public JavaGeekGitHubUser(String image, String username, String company, String url, String htmlUrl, User details) {
        this.image = image;
        this.username = username;
        this.company = company;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.details = details;
    }

    public JavaGeekGitHubUser(String image, String username, String company, String url, String htmlUrl) {
        this.image = image;
        this.username = username;
        this.company = company;
        this.url = url;
        this.htmlUrl = htmlUrl;
    }

    @Ignore
    protected JavaGeekGitHubUser(Parcel in) {
        image = in.readString();
        username = in.readString();
        company = in.readString();
        url = in.readString();
        htmlUrl = in.readString();
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public User getDetails() {
        return details;
    }

    public void setDetails(User details) {
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(username);
        dest.writeString(company);
        dest.writeString(url);
        dest.writeString(htmlUrl);
    }

}
