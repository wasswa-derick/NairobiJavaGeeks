package com.rosen.wasswaderick.nairobijavageeks.service;

import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.utils.Keys;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Derick W on 21,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public interface GitHubUserAPI {

    @GET("search/users?q=language:java+location:nairobi&access_token=" + Keys.ACCESS_TOKEN)
    Call<List<JavaGeekGitHubUser>> getJavaDevsNairobi();

}
