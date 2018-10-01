package com.rosen.wasswaderick.nairobijavageeks.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rosen.wasswaderick.nairobijavageeks.R;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.adapter.GitHubUsersAdapter;
import com.rosen.wasswaderick.nairobijavageeks.presenter.GitHubUserPresenter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GitHubUserView, SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView;
    TextView developersCount;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;

    GitHubUsersAdapter gitHubUsersAdapter;
    ArrayList<JavaGeekGitHubUser> javaGeekGitHubUserArrayList;
    GridLayoutManager gridLayoutManager;
    PresenterView presenterView;

    ProgressDialog progressDialog;

    final String USERS_KEY = "users";
    public final static String RECYCLER_VIEW_STATE_KEY = "recycler_view_state";
    Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorAccent);

        recyclerView = findViewById(R.id.developers_list);
        developersCount = findViewById(R.id.developers_count);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenterView = new GitHubUserPresenter(this);
        progressDialog = new ProgressDialog(MainActivity.this);
        gridLayoutManager = new GridLayoutManager(this, 2);

        if (savedInstanceState != null) {
            javaGeekGitHubUserArrayList = new ArrayList<>();
            javaGeekGitHubUserArrayList = savedInstanceState.getParcelableArrayList(USERS_KEY);
            renderGitHubUsers(javaGeekGitHubUserArrayList);
        }else{
            // Load the Data from the API
            presenterView.fetchNairobiJavaGitHubUsers();
            showProgressDialog();
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS_KEY, javaGeekGitHubUserArrayList);

        // Save RECYCLER VIEW state
        listState = gridLayoutManager.onSaveInstanceState();
        outState.putParcelable(RECYCLER_VIEW_STATE_KEY, listState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Retrieve list state and list/item positions
        if(savedInstanceState != null)
            listState = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            gridLayoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    public void renderGitHubUsers(ArrayList<JavaGeekGitHubUser> javaGeekGitHubUsers) {

        // stopping swipe refresh
        dismissProgressDialog();
        swipeRefreshLayout.setRefreshing(false);

        javaGeekGitHubUserArrayList = javaGeekGitHubUsers;
        gitHubUsersAdapter = new GitHubUsersAdapter(getApplicationContext(), javaGeekGitHubUsers);
        recyclerView.setAdapter(gitHubUsersAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        gitHubUsersAdapter.notifyDataSetChanged();

        String countDevelopers = javaGeekGitHubUsers.size() + " Developers";
        developersCount.setText(countDevelopers);

        // Recycler View On Click Handler
        launchDeveloperDetails();
    }

    public void launchDeveloperDetails(){
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClick(getApplicationContext(), new RecyclerViewItemClick.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                JavaGeekGitHubUser user = javaGeekGitHubUserArrayList.get(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", user.getUsername());
                bundle.putString("image", user.getImage());
                bundle.putString("url", user.getUrl());
                bundle.putString("htmlUrl", user.getHtmlUrl());
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        }));
    }

    @Override
    public void onRefresh() {
        presenterView.fetchNairobiJavaGitHubUsers();
    }

    public void showProgressDialog(){
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Wait while loading users ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgressDialog(){
        progressDialog.dismiss();
    }
}

