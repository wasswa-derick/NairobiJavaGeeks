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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    TextView developersCount;
    Toolbar toolbar;

    LinearLayoutManager linearLayoutManager;
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

        if (savedInstanceState != null || !savedInstanceState.containsKey(USERS_KEY)) {
            javaGeekGitHubUserList = new ArrayList<>();
            // Load the Data from the API
        }else{
            javaGeekGitHubUserList = savedInstanceState.getParcelableArrayList(USERS_KEY);
        }
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(linearLayoutManager);

        developersCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
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
}
