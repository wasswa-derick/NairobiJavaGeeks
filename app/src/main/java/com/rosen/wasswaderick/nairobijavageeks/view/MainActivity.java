package com.rosen.wasswaderick.nairobijavageeks.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rosen.wasswaderick.nairobijavageeks.R;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.adapter.GitHubUsersAdapter;
import com.rosen.wasswaderick.nairobijavageeks.model.User;
import com.rosen.wasswaderick.nairobijavageeks.presenter.GitHubUserPresenter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GitHubUserView{
    RecyclerView recyclerView;
    TextView developersCount;
    Toolbar toolbar;
    GitHubUsersAdapter gitHubUsersAdapter;
    ArrayList<JavaGeekGitHubUser> javaGeekGitHubUserArrayList;
    LinearLayoutManager linearLayoutManager;
    PresenterView presenterView;

    final String USERS_KEY = "users";
    ArrayList<JavaGeekGitHubUser> javaGeekGitHubUserList;
    public final static String RECYCLER_VIEW_STATE_KEY = "recycler_view_state";
    Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.developers_list);
        developersCount = findViewById(R.id.developers_count);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenterView = new GitHubUserPresenter(this);


        if (savedInstanceState != null) {
            javaGeekGitHubUserList = new ArrayList<>();
            javaGeekGitHubUserList = savedInstanceState.getParcelableArrayList(USERS_KEY);
        }else{
            // Load the Data from the API
            presenterView.fetchNairobiJavaGitHubUsers();
        }
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS_KEY, javaGeekGitHubUserList);

        // Save RECYCLER VIEW state
        listState = linearLayoutManager.onSaveInstanceState();
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
            linearLayoutManager.onRestoreInstanceState(listState);
        }
    }


    @Override
    public void renderGitHubUsers(ArrayList<JavaGeekGitHubUser> javaGeekGitHubUsers) {
        javaGeekGitHubUserArrayList = javaGeekGitHubUsers;
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        gitHubUsersAdapter = new GitHubUsersAdapter(getApplicationContext(), javaGeekGitHubUsers);
        recyclerView.setAdapter(gitHubUsersAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
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

}
