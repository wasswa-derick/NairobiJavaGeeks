package com.rosen.wasswaderick.nairobijavageeks.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rosen.wasswaderick.nairobijavageeks.R;

public class DetailActivity extends AppCompatActivity{

    ImageView profileImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("@KauamaBruce");

        profileImage = findViewById(R.id.profile_image);

        //load animation for the profile Image
        AnimationSet animationSet = new AnimationSet(true);
        Animation animation_zoom = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        animationSet.addAnimation(animation_zoom);
        profileImage.setAnimation(animationSet);


    }
}
