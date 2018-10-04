package com.rosen.wasswaderick.nairobijavageeks.view;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rosen.wasswaderick.nairobijavageeks.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Derick W on 02,October,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void clickItem() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.developers_list)).perform(RecyclerViewActions.scrollToPosition(0));

        onView(withId(R.id.developers_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


//        onView(withId(R.id.developers_list))
//                .check(matches(isDisplayed()));
//
//        pressBack();
//        // verify the visibility of recycler view on screen
//        onView(withId(R.id.developers_list)).check(matches(isDisplayed()));
//        // perform click on view at 3rd position in RecyclerView
//        onView(withId(R.id.developers_list)).perform(RecyclerViewActions.scrollToPosition(2));

    }


    @Test
    public void orientationChanges(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Perform Configuration Change
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        activityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }



}
