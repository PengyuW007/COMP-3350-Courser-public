package comp3350.courser.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350.courser.tests.business.CourseServiceTest;
import comp3350.courser.tests.business.ScheduleServiceTest;
import comp3350.courser.tests.business.SectionServiceTest;
import comp3350.courser.tests.business.TimeSlotServiceTest;
import comp3350.courser.tests.objects.CourseTest;
import comp3350.courser.tests.objects.ScheduleTest;
import comp3350.courser.tests.objects.SectionTest;


public class RunUnitTests {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CourseServiceTest.class);
        suite.addTestSuite(ScheduleServiceTest.class);
        suite.addTestSuite(SectionServiceTest.class);
        suite.addTestSuite(TimeSlotServiceTest.class);
        suite.addTestSuite(CourseTest.class);
        suite.addTestSuite(ScheduleTest.class);
        suite.addTestSuite(SectionTest.class);
        return suite;
    }

}
