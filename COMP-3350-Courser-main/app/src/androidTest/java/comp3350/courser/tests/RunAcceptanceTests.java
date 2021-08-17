package comp3350.courser.tests;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.courser.tests.acceptance.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({AcceptanceViewCourses.class, AcceptanceCreateCourseAndLecture.class, AcceptanceCreateCourseWithLab.class})
public class RunAcceptanceTests {
    public RunAcceptanceTests(){
        System.out.println("Run Acceptance Tests");
    }
}
