package com.rosen.wasswaderick.nairobijavageeks.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rosen.wasswaderick.nairobijavageeks.R;
import com.rosen.wasswaderick.nairobijavageeks.adapter.GlideApp;
import com.rosen.wasswaderick.nairobijavageeks.databinding.DeveloperLayoutBinding;
import com.rosen.wasswaderick.nairobijavageeks.model.JavaGeekGitHubUser;
import com.rosen.wasswaderick.nairobijavageeks.model.User;
import com.rosen.wasswaderick.nairobijavageeks.presenter.UserDetailPresenter;

public class DetailActivity extends AppCompatActivity implements UserDetailView {

    ImageView profileImage;
    FloatingActionButton share;

    PresenterDetailView presenterDetailView;
    User userDetails;
    String username;
    String image;
    String htmlUrlData;

    ProgressDialog progressDialog;

    final String USER_DETAILS_KEY = "user";

    DeveloperLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.developer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenterDetailView = new UserDetailPresenter(this);
        progressDialog = new ProgressDialog(DetailActivity.this);

        profileImage = findViewById(R.id.profile_image);
        share = findViewById(R.id.share);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        username = bundle.getString("username");
        image = bundle.getString("image");
        htmlUrlData = bundle.getString("htmlUrl");

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String usernameHandle = "@" + username;
        getSupportActionBar().setTitle(usernameHandle);
        
        //load animation for the profile Image
        AnimationSet animationSet = new AnimationSet(true);
        Animation animationZoom = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        animationSet.addAnimation(animationZoom);
        // profileImage.setAnimation(animationSet);

        // Retrieve the Developer data
        if (savedInstanceState != null) {
            // Reinstate the saved Instance
            userDetails = savedInstanceState.getParcelable(USER_DETAILS_KEY);
            this.renderGitHubUsers(userDetails);
        }else{
            // Load the Data from the API
            presenterDetailView.fetchUserDetails(username);
            showProgressDialog();
        }


        // Profile Sharing
        shareButtonClickHandler();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(USER_DETAILS_KEY, userDetails);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void renderGitHubUsers(User user) {
        dismissProgressDialog();

        binding.setUser(user);

        JavaGeekGitHubUser gitHubUser = new JavaGeekGitHubUser();
        gitHubUser.setHtmlUrl(htmlUrlData);
        gitHubUser.setImage(image);
        binding.setGithubuser(gitHubUser);
    }

    public void shareButtonClickHandler(){
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = "Check out this awesome developer @" + username + ", " + htmlUrlData + ".";
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, content);
                startActivity(Intent.createChooser(shareIntent, "Share Profile"));
            }
        });
    }

    public void showProgressDialog(){
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Wait while loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgressDialog(){
        progressDialog.dismiss();
    }
}
