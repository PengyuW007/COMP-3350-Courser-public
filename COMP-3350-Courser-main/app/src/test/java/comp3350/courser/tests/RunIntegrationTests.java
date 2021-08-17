package comp3350.courser.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.courser.tests.integration.BusinessPersistenceSeamTest;

import comp3350.courser.tests.business.AccessServiceTest;
import comp3350.courser.tests.persistence.TestCoursePersistence;
import comp3350.courser.tests.persistence.TestSectionPersistence;
import comp3350.courser.tests.persistence.TestTimeSlotPersistence;

public class RunIntegrationTests {

    public static Test suite(){
        TestSuite suite = new TestSuite();

        suite.addTestSuite(AccessServiceTest.class);
        suite.addTestSuite(TestCoursePersistence.class);
        suite.addTestSuite(TestSectionPersistence.class);
        suite.addTestSuite(TestTimeSlotPersistence.class);
        suite.addTestSuite(BusinessPersistenceSeamTest.class);

        return suite;
    }
}
