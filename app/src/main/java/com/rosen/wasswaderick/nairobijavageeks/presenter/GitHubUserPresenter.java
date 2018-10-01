package com.rosen.wasswaderick.nairobijavageeks.presenter;

import android.util.Log;

import com.rosen.wasswaderick.nairobijavageeks.model.GitHubUsers;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.model.User;
import com.rosen.wasswaderick.nairobijavageeks.service.RetrofitGitHubService;
import com.rosen.wasswaderick.nairobijavageeks.view.GitHubUserView;
import com.rosen.wasswaderick.nairobijavageeks.view.PresenterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Derick W on 21,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public class GitHubUserPresenter implements PresenterView{

    private RetrofitGitHubService retrofitGitHubService;
    private GitHubUserView gitHubUserView;

    public GitHubUserPresenter(GitHubUserView gitHubUserView) {
        this.gitHubUserView = gitHubUserView;
        if (this.retrofitGitHubService == null) {
            this.retrofitGitHubService = new RetrofitGitHubService();
        }
    }

    @Override
    public void fetchNairobiJavaGitHubUsers() {
        retrofitGitHubService
                .getGitHubUserAPI()
                .getJavaDevsNairobi()
                .enqueue(new Callback<GitHubUsers>() {
                    @Override
                    public void onResponse(Call<GitHubUsers> call, Response<GitHubUsers> response) {

                        if (response.isSuccessful()) {
                            ArrayList<JavaGeekGitHubUser> users = response.body().getUsers();

                            if (users != null) {
                                gitHubUserView.renderGitHubUsers(users);
                            }

                        } else {
                            Log.d("status", response.errorBody().toString());
                            Log.d("status", response.headers().toString());
                            Log.d("status", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<GitHubUsers> call, Throwable t) {
                        try {
                            Log.d("message", t.getLocalizedMessage());
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public User fetchUserDetails(String username){
        final User[] user = {new User()};
        retrofitGitHubService
                .getGitHubUserAPI()
                .getUserDetail(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.isSuccessful()) {
                            user[0] = response.body();
                            Log.d("users", response.body().getBio());
                        } else {
                            Log.d("status", response.errorBody().toString());
                            Log.d("header", response.headers().toString());
                            Log.d("message", response.message());
                            Log.d("message", response.code() + "");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        try {
                            Log.d("message", t.getMessage());
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return user[0];
    }

}
