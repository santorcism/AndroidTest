package com.example.Lab3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.filters.SmallTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class EspressoMainTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void checkTimeVisible() {
        onView(withId(R.id.time)).check(matches(isDisplayed()));
    }

    @Test
    public void checkTestModeButtonYes(){
        for (int i=0; i<10; i++){
            onView(withId(R.id.yes_btn)).perform(click());
        }
        onView(withId(R.id.points)).check(matches(withText("10")));

    }

    @Test
    public void checkTestModeButtonNo(){
        onView(withId(R.id.no_btn)).check(matches(isClickable()));

        for (int i=0; i<10; i++){
            onView(withId(R.id.no_btn)).perform(click());
        }
        onView(withId(R.id.points)).check(matches(withText("0")));

    }

}
