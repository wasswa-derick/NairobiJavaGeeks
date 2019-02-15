package com.rosen.wasswaderick.nairobijavageeks.service;

import com.rosen.wasswaderick.nairobijavageeks.model.GitHubUsers;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Derick W on 21,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public interface GitHubUserAPI {

    @GET("search/users?q=language:java+location:nairobi")
    Call<GitHubUsers> getJavaDevsNairobi();

    @GET("users/{user}")
    Call<User> getUserDetail(@Path("user") String user);

    List<JavaGeekGitHubUser> getLocalStorageUsers();

}
