package com.rosen.wasswaderick.nairobijavageeks.presenter;

import android.util.Log;

import com.rosen.wasswaderick.nairobijavageeks.model.User;
import com.rosen.wasswaderick.nairobijavageeks.service.RetrofitGitHubService;
import com.rosen.wasswaderick.nairobijavageeks.view.PresenterDetailView;
import com.rosen.wasswaderick.nairobijavageeks.view.UserDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Derick W on 27,September,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public class UserDetailPresenter implements PresenterDetailView {

    private RetrofitGitHubService retrofitGitHubService;
    private UserDetailView userDetailView;

    public UserDetailPresenter(UserDetailView userDetailView) {
        this.userDetailView = userDetailView;
        if (this.retrofitGitHubService == null) {
            this.retrofitGitHubService = new RetrofitGitHubService();
        }
    }

    @Override
    public void fetchUserDetails(String username){
        retrofitGitHubService
                .getGitHubUserAPI()
                .getUserDetail(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.isSuccessful()) {
                            userDetailView.renderGitHubUsers(response.body());
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
    }


}
