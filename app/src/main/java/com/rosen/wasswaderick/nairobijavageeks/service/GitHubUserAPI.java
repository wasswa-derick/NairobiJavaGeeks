package com.rosen.wasswaderick.nairobijavageeks.service;

import com.rosen.wasswaderick.nairobijavageeks.model.GitHubUsers;
import com.rosen.wasswaderick.nairobijavageeks.model.User;
import com.rosen.wasswaderick.nairobijavageeks.utils.Keys;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Derick W on 21,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public interface GitHubUserAPI {

    @GET("search/users?q=language:java+location:nairobi&access_token=" + Keys.ACCESS_TOKEN)
    Call<GitHubUsers> getJavaDevsNairobi();

    @GET("users/{user}?access_token=" + Keys.ACCESS_TOKEN)
    Call<User> getUserDetail(@Path("user") String user);

}
