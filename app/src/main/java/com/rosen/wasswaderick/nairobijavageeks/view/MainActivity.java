package com.rosen.wasswaderick.nairobijavageeks.view;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rosen.wasswaderick.nairobijavageeks.BaseApplication;
import com.rosen.wasswaderick.nairobijavageeks.R;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.adapter.GitHubUsersAdapter;
import com.rosen.wasswaderick.nairobijavageeks.presenter.GitHubUserPresenter;
import com.rosen.wasswaderick.nairobijavageeks.service.GitHubUserAPI;
import com.rosen.wasswaderick.nairobijavageeks.utils.NetworkConnectionDetector;

import java.util.ArrayList;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements GitHubUserView, SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView;
    TextView developersCount;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    CoordinatorLayout roots;

    GitHubUsersAdapter gitHubUsersAdapter;
    ArrayList<JavaGeekGitHubUser> javaGeekGitHubUserArrayList;
    GridLayoutManager gridLayoutManager;
    PresenterView presenterView;

    ProgressDialog progressDialog;

    final String USERS_KEY = "users";
    public final static String RECYCLER_VIEW_STATE_KEY = "recycler_view_state";
    Parcelable listState;

    //  Broadcast event for internet connectivity
    BroadcastReceiver networkStateReceiver;

    @Inject
    GitHubUserAPI gitHubUserAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectDependency();

        roots = findViewById(R.id.roots);

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
        presenterView = new GitHubUserPresenter(this, getApplication(), gitHubUserAPI);
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

        // Network Utility
        networkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Boolean connection = new NetworkConnectionDetector(getApplicationContext()).InternetConnectionStatus();
                if (!connection) {
                    dismissProgressDialog();
                    Snackbar.make(roots, R.string.no_internet, Snackbar.LENGTH_LONG)
                            .setAction(R.string.connect, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                                    startActivity(settingsIntent);
                                }
                            }).show();

                    renderGitHubUsers(presenterView.fetchLocalUsers());
                }
            }
        };
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    void injectDependency(){
        ((BaseApplication) getApplication())
                .getApplicationComponent()
                .inject(MainActivity.this);
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
        //  register broadcast receive once activity is in the foreground
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        if (listState != null) {
            gridLayoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    protected void onPause() {
        // disconnect expensive broadcast receiver
        unregisterReceiver(networkStateReceiver);
        super.onPause();
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

        presenterView.insertUser(javaGeekGitHubUsers);

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

