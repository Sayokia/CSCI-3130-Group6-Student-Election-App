package com.example.a3130_vote;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Rule;
import androidx.test.rule.ActivityTestRule;

import com.example.a3130_vote.activities.SplashActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {

        // Context of the app under test.
    }
    @RunWith(AndroidJUnit4.class)

    @LargeTest
    public class ChangeTextBehaviorTest {

        private String stringToBetyped;

        @Rule
        public ActivityTestRule<SplashActivity> activityRule
                = new ActivityTestRule<>(SplashActivity.class);

        @Before
        public void initValidString() {
            // Specify a valid string.
            stringToBetyped = "Espresso";
        }

        @Test
        public void changeText_sameActivity() {
            // Type text and then press the button.
            onView(withId(R.id.getstarted))
                    .perform(typeText(stringToBetyped), closeSoftKeyboard());

        }
    }
}
