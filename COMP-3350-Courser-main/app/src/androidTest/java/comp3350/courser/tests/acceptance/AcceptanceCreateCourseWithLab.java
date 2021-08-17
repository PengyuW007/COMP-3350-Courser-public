package comp3350.courser.tests.acceptance;

import org.junit.*;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

import com.example.courser.R;
import comp3350.courser.presentation.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AcceptanceCreateCourseWithLab {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testHomeScreen(){
        onView(withId(R.id.createWorkspaceButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddCourseWithOneLab(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName7"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode7"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm7"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1997"), pressImeActionButton());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept7"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("7"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("77777"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode7"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst7"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp7"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc7"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addLabButton)).check(matches(isDisplayed())).perform(click());


        Espresso.closeSoftKeyboard();

        //Now on CreateNewSection Page
        onView(withId(R.id.courseLectureTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.labCRNField)).perform(clearText(), typeText("77772"), pressImeActionButton());
        onView(withId(R.id.labCodeField)).perform(clearText(), typeText("TestLabCode7"), pressImeActionButton());
        onView(withId(R.id.labInstructorField)).perform(clearText(), typeText("TestInst7"), pressImeActionButton());
        onView(withId(R.id.labCampusField)).perform(clearText(), typeText("TestCamp7"), pressImeActionButton());
        onView(withId(R.id.labLocationField)).perform(clearText(), typeText("TestLoc7"), pressImeActionButton());
        onView(withId(R.id.labStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.labEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.labTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.labMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.labMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.finishButtonLab)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        onView(withText("TestCode7")).check(matches(isDisplayed()));

    }

    @Test
    public void testAddCourseWithMultipleLabs(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName8"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode8"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm8"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1998"), pressImeActionButton());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept8"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("8"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeDown());

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("88888"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode8"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst8"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp8"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc8"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());

        onView(withId(R.id.addLabButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewSection Page
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeDown());
        onView(withId(R.id.courseLectureTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeDown());

        //Fill happy path
        onView(withId(R.id.labCRNField)).perform(clearText(), typeText("88882"), pressImeActionButton());
        onView(withId(R.id.labCodeField)).perform(clearText(), typeText("TestLabCode8"), pressImeActionButton());
        onView(withId(R.id.labInstructorField)).perform(clearText(), typeText("TestInst8"), pressImeActionButton());
        onView(withId(R.id.labCampusField)).perform(clearText(), typeText("TestCamp8"), pressImeActionButton());
        onView(withId(R.id.labLocationField)).perform(clearText(), typeText("TestLoc8"), pressImeActionButton());
        onView(withId(R.id.labStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.labEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.labTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.labMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.labMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.addNewLabButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewSection Page
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeDown());
        onView(withId(R.id.courseLectureTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.labCRNField)).perform(clearText(), typeText("88883"), pressImeActionButton());
        onView(withId(R.id.labCodeField)).perform(clearText(), typeText("TestLabCode8"), pressImeActionButton());
        onView(withId(R.id.labInstructorField)).perform(clearText(), typeText("TestInst8"), pressImeActionButton());
        onView(withId(R.id.labCampusField)).perform(clearText(), typeText("TestCamp8"), pressImeActionButton());
        onView(withId(R.id.labLocationField)).perform(clearText(), typeText("TestLoc8"), pressImeActionButton());
        onView(withId(R.id.labStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.labEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.labTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.labMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.labMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.finishButtonLab)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        onView(withText("TestCode8")).check(matches(isDisplayed()));

    }

    @Test
    public void testAddCourseDuplicateLabs(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName9"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode9"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm9"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1999"), pressImeActionButton());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept9"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("9"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeDown());

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("99999"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode9"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst9"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp9"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc9"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());

        onView(withId(R.id.addLabButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewSection Page
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeDown());
        onView(withId(R.id.courseLectureTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeDown());

        //Fill happy path
        onView(withId(R.id.labCRNField)).perform(clearText(), typeText("99992"), pressImeActionButton());
        onView(withId(R.id.labCodeField)).perform(clearText(), typeText("TestLabCode9"), pressImeActionButton());
        onView(withId(R.id.labInstructorField)).perform(clearText(), typeText("TestInst9"), pressImeActionButton());
        onView(withId(R.id.labCampusField)).perform(clearText(), typeText("TestCamp9"), pressImeActionButton());
        onView(withId(R.id.labLocationField)).perform(clearText(), typeText("TestLoc9"), pressImeActionButton());
        onView(withId(R.id.labStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.labEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.labTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.labMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.labMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.addNewLabButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewSection Page
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeDown());
        onView(withId(R.id.courseLectureTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.labCRNField)).perform(clearText(), typeText("99992"), pressImeActionButton());
        onView(withId(R.id.labCodeField)).perform(clearText(), typeText("TestLabCode9"), pressImeActionButton());
        onView(withId(R.id.labInstructorField)).perform(clearText(), typeText("TestInst9"), pressImeActionButton());
        onView(withId(R.id.labCampusField)).perform(clearText(), typeText("TestCamp9"), pressImeActionButton());
        onView(withId(R.id.labLocationField)).perform(clearText(), typeText("TestLoc9"), pressImeActionButton());
        onView(withId(R.id.labStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.labEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.labTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.labMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.labMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.finishButtonLab)).check(matches(isDisplayed())).perform(click());

        onView(withText("This course already has a lab with the CRN 99992!")).check(matches(isDisplayed()));
        Espresso.pressBack();

    }

    @Test
    public void testAddCourseWithLabValidationError(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName0"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode0"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm0"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1990"), pressImeActionButton());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept0"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("0"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("00000"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode0"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst0"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp0"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc0"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.addLabButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewSection Page
        onView(withId(R.id.courseLectureTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.finishButtonLab)).check(matches(isDisplayed())).perform(click());

        onView(withText("You must fill in at least one time slot!")).check(matches(isDisplayed()));
        Espresso.pressBack();

        //Fill happy path
        onView(withId(R.id.labCRNField)).check(matches(hasErrorText("Section CRN must be a non-empty integer!")));
        onView(withId(R.id.labCRNField)).perform(clearText(), typeText("00002"), pressImeActionButton());

        onView(withId(R.id.labCodeField)).check(matches(hasErrorText("Section code cannot be empty!")));
        onView(withId(R.id.labCodeField)).perform(clearText(), typeText("TestLabCode0"), pressImeActionButton());

        onView(withId(R.id.labInstructorField)).check(matches(hasErrorText("Instructor cannot be empty!")));
        onView(withId(R.id.labInstructorField)).perform(clearText(), typeText("TestInst0"), pressImeActionButton());

        onView(withId(R.id.labCampusField)).check(matches(hasErrorText("Campus cannot be empty!")));
        onView(withId(R.id.labCampusField)).perform(clearText(), typeText("TestCamp0"), pressImeActionButton());

        onView(withId(R.id.labLocationField)).check(matches(hasErrorText("Location cannot be empty!")));
        onView(withId(R.id.labLocationField)).perform(clearText(), typeText("TestLoc0"), pressImeActionButton());

        onView(withId(R.id.labStartDateField)).check(matches(hasErrorText("Invalid start date format!")));
        onView(withId(R.id.labStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());

        onView(withId(R.id.labEndDateField)).check(matches(hasErrorText("Invalid end date format!")));
        onView(withId(R.id.labEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.labTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.labMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.labMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.finishButtonLab)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeDown());
        onView(withText("TestCode0")).check(matches(isDisplayed()));

    }

    @Test
    public void testAddCourseWithLabValidationErrorClassTime() {
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName10"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode10"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm10"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1990"), pressImeActionButton());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept10"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("10"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("10000"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode10"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst10"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp10"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc10"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime for section
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.addLabButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewLab Page
        onView(withId(R.id.courseLectureTitleNewSection)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.labCRNField)).perform(clearText(), typeText("10002"), pressImeActionButton());
        onView(withId(R.id.labCodeField)).perform(clearText(), typeText("TestLabCode0"), pressImeActionButton());
        onView(withId(R.id.labInstructorField)).perform(clearText(), typeText("TestInst0"), pressImeActionButton());
        onView(withId(R.id.labCampusField)).perform(clearText(), typeText("TestCamp0"), pressImeActionButton());
        onView(withId(R.id.labLocationField)).perform(clearText(), typeText("TestLoc0"), pressImeActionButton());
        onView(withId(R.id.labStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.labEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.labTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.labMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        //click without filling fields
        onView(withId(R.id.finishButtonLab)).check(matches(isDisplayed())).perform(click());

        onView(withText("You must fill in at least one time slot!")).check(matches(isDisplayed()));
        Espresso.pressBack();

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());

        onView(withId(R.id.labMondayEndField)).check(matches(hasErrorText("You must fill in both fields for a day!")));
        onView(withId(R.id.labMondayEndField)).perform(clearText(), typeText("1000"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        //click without filling fields
        onView(withId(R.id.finishButtonLab)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());

        onView(withId(R.id.labMondayEndField)).check(matches(hasErrorText("Invalid time format!")));
        onView(withId(R.id.labMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.finishButtonLab)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        onView(withText("TestCode10")).check(matches(isDisplayed()));
    }

    }
