package com.rosen.wasswaderick.nairobijavageeks.presenter;

import android.util.Log;

import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.service.RetrofitGitHubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Derick W on 21,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public class GitHubUserPresenter {

    private RetrofitGitHubService retrofitGitHubService;

    public GitHubUserPresenter() {
        if (this.retrofitGitHubService == null) {
            this.retrofitGitHubService = new RetrofitGitHubService();
        }
    }

    public void fetchNairobiJavaGitHubUsers() {
        retrofitGitHubService
                .getGitHubUserAPI()
                .getJavaDevsNairobi()
                .enqueue(new Callback<List<JavaGeekGitHubUser>>() {
                    @Override
                    public void onResponse(Call<List<JavaGeekGitHubUser>> call, Response<List<JavaGeekGitHubUser>> response) {

                        if (response.isSuccessful()) {
                            for (int i = 0; i < response.body().size(); i++) {
                                JavaGeekGitHubUser javaGeekGitHubUser = response.body().get(i);
                                Log.d("users", javaGeekGitHubUser.getDevelopEmail());
                            }
                        } else {
                            Log.d("status", response.errorBody().toString());
                            Log.d("status", response.headers().toString());
                            Log.d("status", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<JavaGeekGitHubUser>> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
