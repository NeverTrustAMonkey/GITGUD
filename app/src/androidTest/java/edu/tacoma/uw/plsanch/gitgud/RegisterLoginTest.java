package edu.tacoma.uw.plsanch.gitgud;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Zachary on 3/10/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterLoginTest {

    @Rule
    public ActivityTestRule<MenuActivity> mActivityRule = new ActivityTestRule<>(
            MenuActivity.class);

    /**
     * This test logs the previous user out, signs in as suethedinosaur and submits a genji guide
     */
    @Test
    public void testMenuActivity() {

        onView(withId(R.id.account))
                .perform(click());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("LOGOUT"))
                .perform(click());
        onView(withId(R.id.editTextEmail))
                .perform(typeText("suethedinosaur"));
        onView(withId(R.id.editText2))
                .perform(typeText("dinosorcerer"));
        onView(withId(R.id.button))
                .perform(click());
        onView(withId(R.id.guides))
                .perform(click());
        onView(withId(R.id.createButton))
                .perform(click());
        onView(withId(R.id.editTextTitle))
                .perform(typeText("The Dragon Stirs"));
        onView(withId(R.id.editTextBody))
                .perform(typeText("This is my Genji guide!\n" +
                        "How to play Genji: PRESS Q > HIT THEM WITH YOUR SWORD > ???? > PROFIT"));
        onView(withId(R.id.button4))
                .perform(click());
        onView(withText("OK"))
                .perform(click());
    }
}
