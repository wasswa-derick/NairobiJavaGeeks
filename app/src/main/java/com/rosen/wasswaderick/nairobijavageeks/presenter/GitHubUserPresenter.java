package com.rosen.wasswaderick.nairobijavageeks.presenter;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.rosen.wasswaderick.nairobijavageeks.data.db.GitHubUserDao;
import com.rosen.wasswaderick.nairobijavageeks.data.db.GitHubUserDatabase;
import com.rosen.wasswaderick.nairobijavageeks.model.GitHubUsers;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.service.RetrofitGitHubService;
import com.rosen.wasswaderick.nairobijavageeks.view.GitHubUserView;
import com.rosen.wasswaderick.nairobijavageeks.view.PresenterView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    private GitHubUserDao gitHubUserDao;
    private ArrayList<JavaGeekGitHubUser> allUsers;

    public GitHubUserPresenter(GitHubUserView gitHubUserView, Application application) {
        this.gitHubUserView = gitHubUserView;
        if (this.retrofitGitHubService == null) {
            this.retrofitGitHubService = new RetrofitGitHubService();
        }

        GitHubUserDatabase db = GitHubUserDatabase.getDatabase(application);
        gitHubUserDao = db.gitHubUserDao();
        try {
            allUsers = getAllGithubUsers();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<JavaGeekGitHubUser> getAllGithubUsers() throws ExecutionException, InterruptedException {

        Callable<ArrayList<JavaGeekGitHubUser>> callable = new Callable<ArrayList<JavaGeekGitHubUser>>() {
            @Override
            public ArrayList<JavaGeekGitHubUser> call() throws Exception {
                return (ArrayList<JavaGeekGitHubUser>) gitHubUserDao.getAllGithubUsers();
            }
        };

        Future<ArrayList<JavaGeekGitHubUser>> future = Executors.newSingleThreadExecutor().submit(callable);

        return future.get();
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
    public ArrayList<JavaGeekGitHubUser> fetchLocalUsers() {
        return allUsers;
    }

    @Override
    public void insertUser(List<JavaGeekGitHubUser> javaGeekGitHubUser) {
        for (JavaGeekGitHubUser gitHubUser: javaGeekGitHubUser) {
            new insertAsyncTask(gitHubUserDao).execute(gitHubUser);
        }
    }

    private static class insertAsyncTask extends AsyncTask<JavaGeekGitHubUser, Void, Void> {

        private GitHubUserDao mAsyncTaskDao;

        insertAsyncTask(GitHubUserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final JavaGeekGitHubUser... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


}
