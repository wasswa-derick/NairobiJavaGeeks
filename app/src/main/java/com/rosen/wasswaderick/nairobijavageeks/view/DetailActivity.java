package com.rosen.wasswaderick.nairobijavageeks.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.rosen.wasswaderick.nairobijavageeks.R;
import com.rosen.wasswaderick.nairobijavageeks.adapter.GlideApp;
import com.rosen.wasswaderick.nairobijavageeks.model.User;
import com.rosen.wasswaderick.nairobijavageeks.presenter.GitHubUserPresenter;
import com.rosen.wasswaderick.nairobijavageeks.presenter.UserDetailPresenter;

public class DetailActivity extends AppCompatActivity implements UserDetailView {

    ImageView profileImage;
    TextView name, role, location, followers, following, repositories, bio, htmlURL;

    PresenterDetailView presenterDetailView;
    User userDetails;
    String username, image, htmlUrlData;
    final String USER_DETAILS_KEY = "user";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenterDetailView = new UserDetailPresenter(this);

        profileImage = findViewById(R.id.profile_image);
        name = findViewById(R.id.name);
        role = findViewById(R.id.role);
        location = findViewById(R.id.location);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        repositories = findViewById(R.id.repositories);
        htmlURL = findViewById(R.id.htmlUrl);
        bio = findViewById(R.id.bio);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        username = bundle.getString("username");
        image = bundle.getString("image");
        htmlUrlData = bundle.getString("htmlUrl");

        if (toolbar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            String usernameHandle = "@" + username;
            getSupportActionBar().setTitle(usernameHandle);
        }

        GlideApp
                .with(getApplicationContext())
                .asBitmap()
                .load(image)
                .placeholder(R.drawable.cat)
                .centerCrop()
                .into(profileImage);

        //load animation for the profile Image
        AnimationSet animationSet = new AnimationSet(true);
        Animation animationZoom = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        animationSet.addAnimation(animationZoom);
        profileImage.setAnimation(animationSet);

        // Retrieve the Developer data
        if (savedInstanceState != null) {
            // Reinstate the saved Instance
            userDetails = savedInstanceState.getParcelable(USER_DETAILS_KEY);
            this.renderGitHubUsers(userDetails);
        }else{
            // Load the Data from the API
            presenterDetailView.fetchUserDetails(username);
        }

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

        userDetails = user;
        name.setText(user.getName());

        String company = user.getCompany() != null ? "(" + user.getCompany() + ")" : "";
        String workRole = "Software Developer " + company;
        String followerData = user.getFollowers() + " followers";
        String followingData = user.getFollowing() + " following";
        String publicRepoData = user.getPublicRepos() + " public repositories";

        htmlURL.setText(htmlUrlData);

        role.setText(workRole);
        location.setText(user.getLocation());
        followers.setText(followerData);
        following.setText(followingData);
        repositories.setText(publicRepoData);
        bio.setText(user.getBio());
    }
}
