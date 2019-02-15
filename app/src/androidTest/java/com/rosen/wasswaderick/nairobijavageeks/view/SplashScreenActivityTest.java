package com.rosen.wasswaderick.nairobijavageeks.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rosen.wasswaderick.nairobijavageeks.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Derick W on 05,October,2018
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
@RunWith(AndroidJUnit4.class)
public class SplashScreenActivityTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> activityRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void splashScreenActivityTest() {

        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
