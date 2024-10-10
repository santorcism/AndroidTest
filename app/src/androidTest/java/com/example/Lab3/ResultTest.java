package com.example.Lab3;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class ResultTest {

    @Rule
    public ActivityScenarioRule<ResultActivity> activityRule =
            new ActivityScenarioRule<>(ResultActivity.class);


    @Test
    public void testIntentMock() {

        Context context = Mockito.mock(Context.class);
        Intent intent = ResultActivity.createIntent(context, "5");
        Assert.assertNotNull(intent);

        Assert.assertEquals("5", intent.getStringExtra("result"));


    }


}
