package com.example.chaiss;

import androidx.test.espresso.Espresso;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainMenuUITest {
    @Rule
    public ActivityScenarioRule<MainMenu> activityRule =
            new ActivityScenarioRule<>(MainMenu.class);

    @Test
    public void playButtonIsDisplayed(){
        Espresso.onView(withId(R.id.button2))
                .check(matches(isDisplayed()));
    }
}
