package comp3350.courser.tests.acceptance;

import org.junit.*;
import org.junit.runner.RunWith;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static comp3350.courser.tests.acceptance.TestUtils.withRecyclerView;

import com.example.courser.R;
import comp3350.courser.presentation.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AcceptanceViewCourses {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testHomeScreen(){
        onView(withId(R.id.createWorkspaceButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testViewCourses(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed()));

        //check recicler view with list of courses
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withText("Databases Concepts and Usage")).check(matches(isDisplayed()));
        onView(withText("Summer")).check(matches(isDisplayed()));
        onView(withText("COMP1012")).check(matches(isDisplayed()));
    }

    @Test
    public void testViewCoursesCheckbox(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed()));

        //check recicler view with list of courses
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.checkbox1)).check(matches(isNotChecked()));
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ReclyclerClickOnView.ReclyclerClickOnView(R.id.checkbox1)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(0, R.id.checkbox1)).check(matches(isChecked()));

        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(1, R.id.checkbox1)).check(matches(isNotChecked()));
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ReclyclerClickOnView.ReclyclerClickOnView(R.id.checkbox1)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(1, R.id.checkbox1)).check(matches(isChecked()));

        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(2, R.id.checkbox1)).check(matches(isNotChecked()));
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(2, ReclyclerClickOnView.ReclyclerClickOnView(R.id.checkbox1)));
        onView(withRecyclerView(R.id.recyclerView).atPositionOnView(2, R.id.checkbox1)).check(matches(isChecked()));

    }

}
