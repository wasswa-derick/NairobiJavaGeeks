package com.rosen.wasswaderick.nairobijavageeks.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rosen.wasswaderick.nairobijavageeks.R;

import static android.support.test.espresso.Espresso.onView;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by Derick W on 02,October,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest{

    @Rule
    public ActivityTestRule<DetailActivity> activityDetailRule = new ActivityTestRule<DetailActivity>(DetailActivity.class, true, true){
        @Override
        protected Intent getActivityIntent() {
            /* added predefined intent data */
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

            Intent intent = new Intent(targetContext, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("username", "Test");
            bundle.putString("image", "https://avatars3.githubusercontent.com/u/10762793?v=4");
            bundle.putString("url", "https://api.github.com/users/valentineRutto");
            bundle.putString("htmlUrl", "https://github.com/valentineRutto");
            intent.putExtra("data", bundle);
            return intent;
        }
    };

    @Test
    public void orientationChanges(){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.htmlUrl)).check(matches(withText("https://github.com/valentineRutto")));
        // Perform Configuration Change
        activityDetailRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        activityDetailRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
