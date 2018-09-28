package com.rosen.wasswaderick.nairobijavageeks.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;


public class JavaGeekGitHubUserResponse {

    @SerializedName("users")
    List<JavaGeekGitHubUser> users;

}
