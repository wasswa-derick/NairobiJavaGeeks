package com.rosen.wasswaderick.nairobijavageeks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Derick W on 25,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public class GitHubUsers {

    @SerializedName("total_count")
    @Expose
    private String developers;

    @SerializedName("items")
    @Expose
    private ArrayList<JavaGeekGitHubUser> users;

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public ArrayList<JavaGeekGitHubUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<JavaGeekGitHubUser> users) {
        this.users = users;
    }
}
