package com.rosen.wasswaderick.nairobijavageeks.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rosen.wasswaderick.nairobijavageeks.utils.Keys;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGitHubService {

    Retrofit retrofitService = null;


    /**
     * Retrofit Instance initialized with a converter
     *
     * @return The GitHubUserAPI interface
     */
    public GitHubUserAPI getGitHubUserAPI() {
        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofitService == null) {
            retrofitService = new Retrofit
                    .Builder()
                    .baseUrl(Keys.gitHubAPIBaseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofitService.create(GitHubUserAPI.class);
    }
}
