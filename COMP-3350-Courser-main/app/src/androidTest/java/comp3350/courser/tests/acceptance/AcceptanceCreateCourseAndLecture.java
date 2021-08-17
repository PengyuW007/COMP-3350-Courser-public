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
public class AcceptanceCreateCourseAndLecture {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testHomeScreen(){
        onView(withId(R.id.createWorkspaceButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddCourseAllClassTimes(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.courseName)).check(matches(isDisplayed()));
        onView(withId(R.id.courseCode)).check(matches(isDisplayed()));
        onView(withId(R.id.courseTerm)).check(matches(isDisplayed()));
        onView(withId(R.id.courseYear)).check(matches(isDisplayed()));
        onView(withId(R.id.courseDepartment)).check(matches(isDisplayed()));
        onView(withId(R.id.courseCreditHours)).check(matches(isDisplayed()));

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName1"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode1"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm1"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1990"), pressImeActionButton());
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept1"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("4"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionCRN)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionCode)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionInstructor)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionCampus)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionLocation)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionDateRange)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionClassTimes)).check(matches(isDisplayed()));

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("11111"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode1"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst1"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp1"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc1"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());
        onView(withId(R.id.classTimeTuesday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionTuesdayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionTuesdayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());
        onView(withId(R.id.classTimeWednesday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionWednesdayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionWednesdayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());
        onView(withId(R.id.classTimeThursday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionThursdayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionThusrdayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());
        onView(withId(R.id.classTimeFriday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionFridayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionFridayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.finishButton)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        onView(withText("TestCode1")).check(matches(isDisplayed()));

    }

    @Test
    public void testAddCourseOneClassTime(){
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
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName2"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode2"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm2"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1992"), pressImeActionButton());
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept2"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("5"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        Espresso.closeSoftKeyboard();

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("22222"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode2"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst2"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp2"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc2"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.finishButton)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        onView(withText("TestCode2")).check(matches(isDisplayed()));

    }

    @Test
    public void testAddCourseValidationErrorEmpty(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));

        //click without filling fields
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.courseNameField)).check(matches(hasErrorText("Course name cannot be empty!")));
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName3"));

        onView(withId(R.id.courseCodeField)).check(matches(hasErrorText("Course code cannot be empty!")));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode3"), pressImeActionButton());

        onView(withId(R.id.courseTermField)).check(matches(hasErrorText("Term cannot be empty!")));
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm3"), pressImeActionButton());

        onView(withId(R.id.courseYearField)).check(matches(hasErrorText("Course year must be a non-empty integer!")));
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1993"), pressImeActionButton());

        onView(withId(R.id.courseDepartmentField)).check(matches(hasErrorText("Course department cannot be empty!")));
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept3"), pressImeActionButton());

        onView(withId(R.id.courseCreditHoursField)).check(matches(hasErrorText("Course credits must be a non-empty integer!")));
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("6"), pressImeActionButton());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewSection Page
        Espresso.closeSoftKeyboard();
        //click without filling fields
        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.finishButton)).perform(click());

        onView(withText("You must fill in at least one time slot!")).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.scrollView2)).perform(swipeDown());

        //click without filling fields
        onView(withId(R.id.sectionCRNField)).check(matches(hasErrorText("Section CRN must be a non-empty integer!")));
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("33333"), pressImeActionButton());

        onView(withId(R.id.sectionCodeField)).check(matches(hasErrorText("Section code cannot be empty!")));
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode3"), pressImeActionButton());

        onView(withId(R.id.sectionInstructorField)).check(matches(hasErrorText("Instructor cannot be empty!")));
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst3"), pressImeActionButton());

        onView(withId(R.id.sectionCampusField)).check(matches(hasErrorText("Campus cannot be empty!")));
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp3"), pressImeActionButton());

        onView(withId(R.id.sectionLocationFIeld)).check(matches(hasErrorText("Location cannot be empty!")));
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc3"), pressImeActionButton());

        onView(withId(R.id.sectionStartDateField)).check(matches(hasErrorText("Invalid start date format!")));
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());

        onView(withId(R.id.sectionEndDateField)).check(matches(hasErrorText("Invalid end date format!")));
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
      
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());

        onView(withId(R.id.finishButton)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        onView(withText("TestCode3")).check(matches(isDisplayed()));

    }


    @Test
    public void testAddCourseValidationErrorClassTimes(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));
        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName4"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode4"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm4"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1994"), pressImeActionButton());
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept4"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("7"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("44444"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode4"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst4"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp4"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc4"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        //click without filling fields
        onView(withId(R.id.finishButton)).check(matches(isDisplayed())).perform(click());

        onView(withText("You must fill in at least one time slot!")).check(matches(isDisplayed()));
        Espresso.pressBack();

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());

        onView(withId(R.id.sectionMondayEndField)).check(matches(hasErrorText("You must fill in both fields for a day!")));
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("1000"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        //click without filling fields
        onView(withId(R.id.finishButton)).check(matches(isDisplayed())).perform(click());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());

        onView(withId(R.id.sectionMondayEndField)).check(matches(hasErrorText("Invalid time format!")));
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.finishButton)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        onView(withText("TestCode4")).check(matches(isDisplayed()));

    }


    @Test
    public void testAddCourseDuplicateError(){
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.courseName)).check(matches(isDisplayed()));
        onView(withId(R.id.courseCode)).check(matches(isDisplayed()));
        onView(withId(R.id.courseTerm)).check(matches(isDisplayed()));
        onView(withId(R.id.courseYear)).check(matches(isDisplayed()));
        onView(withId(R.id.courseDepartment)).check(matches(isDisplayed()));
        onView(withId(R.id.courseCreditHours)).check(matches(isDisplayed()));

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("Software Engineering 1"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("COMP3350"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("Summer"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("2021"), pressImeActionButton());
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDeptDup"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("3"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        onView(withText("There is already a course with code 'COMP3350' in the term 'Summer 2021'!")).check(matches(isDisplayed()));
        Espresso.pressBack();

    }


    @Test
    public void testAddMultipleSections() {
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName5"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode5"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm5"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1995"), pressImeActionButton());
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept5"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("5"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("55555"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode5"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst5"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp5"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc5"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addNewSectionButton)).check(matches(isDisplayed())).perform(click());

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("555552"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode52"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst52"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp52"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc52"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.scrollView2)).perform(swipeUp());
        onView(withId(R.id.finishButton)).check(matches(isDisplayed())).perform(click());

        //Back to workspace check if course was added
        onView(withId(R.id.recyclerView)).perform(swipeUp());
        onView(withText("TestCode5")).check(matches(isDisplayed()));
    }


    @Test
    public void testAddSectionDuplicateError() {
        //MainActivity click button to get into workspace
        onView(withId(R.id.createWorkspaceButton)).perform(click());

        //in workspace check that the buttons exist
        onView(withId(R.id.createScheduleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewCourseButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewCourse Page
        onView(withId(R.id.courseCreationTitle)).check(matches(isDisplayed()));

        //Fill happy path
        onView(withId(R.id.courseNameField)).perform(clearText(), typeText("TestName6"));
        onView(withId(R.id.courseCodeField)).perform(clearText(), typeText("TestCode6"), pressImeActionButton());
        onView(withId(R.id.courseTermField)).perform(clearText(), typeText("TestTerm6"), pressImeActionButton());
        onView(withId(R.id.courseYearField)).perform(clearText(), typeText("1996"), pressImeActionButton());
        onView(withId(R.id.courseDepartmentField)).perform(clearText(), typeText("TestDept6"), pressImeActionButton());
        onView(withId(R.id.courseCreditHoursField)).perform(clearText(), typeText("6"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addSectionsButton)).check(matches(isDisplayed())).perform(click());

        //Now on CreateNewSection Page
        onView(withId(R.id.courseTitleNewSection)).check(matches(isDisplayed()));

        //Fill happy path
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("66666"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode6"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst6"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp6"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc6"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addNewSectionButton)).check(matches(isDisplayed())).perform(click());

        //Fill happy path Duplicate
        onView(withId(R.id.sectionCRNField)).perform(clearText(), typeText("66666"), pressImeActionButton());
        onView(withId(R.id.sectionCodeField)).perform(clearText(), typeText("TestSectCode6"), pressImeActionButton());
        onView(withId(R.id.sectionInstructorField)).perform(clearText(), typeText("TestInst6"), pressImeActionButton());
        onView(withId(R.id.sectionCampusField)).perform(clearText(), typeText("TestCamp6"), pressImeActionButton());
        onView(withId(R.id.sectionLocationFIeld)).perform(clearText(), typeText("TestLoc6"), pressImeActionButton());
        onView(withId(R.id.sectionStartDateField)).perform(clearText(), typeText("01/01"), pressImeActionButton());
        onView(withId(R.id.sectionEndDateField)).perform(clearText(), typeText("02/02"), pressImeActionButton());

        //ClassTime
        onView(withId(R.id.classTimeMonday)).check(matches(isDisplayed()));
        onView(withId(R.id.sectionMondayField)).perform(clearText(), typeText("08:00"), pressImeActionButton());
        onView(withId(R.id.sectionMondayEndField)).perform(clearText(), typeText("10:00"), pressImeActionButton());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addNewSectionButton)).check(matches(isDisplayed())).perform(click());

        onView(withText("This course already has a section with the CRN 66666!")).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

}
