package com.example.chaiss;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AnalysisButtonTest {

    @Rule
    public ActivityScenarioRule<MainMenu> activityRule =
            new ActivityScenarioRule<>(MainMenu.class);

    @Test
    public void testAnalysisButtonIsDisplayed() {
        // Verify the Analysis button is displayed on the main menu
        Espresso.onView(withId(R.id.button6))
                .check(matches(isDisplayed()));
    }
}
