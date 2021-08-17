package comp3350.courser.tests.business;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.courser.business.AccessService;

import comp3350.courser.objects.Course;

import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.persistence.PersistenceAccessDB;
import comp3350.courser.tests.persistence.PersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class AccessServiceTest extends TestCase {
    private String dbName = MainActivity.dbName;
    IPersistenceAccess testAccess;

    public AccessServiceTest(String args) {
        super(args);
    }

    @BeforeClass
    private void start() {

        //SWITCH BETWEEN REAL DB AND STUB

        //STUB
        //testAccess = new PersistenceAccess(dbName);

        //REAL
        testAccess = new PersistenceAccessDB(dbName);
    }

    @Test
    public void testCreateDataAccessDB(){
        ArrayList<Course> courseList;
        IPersistenceAccess database;
        start();

        AccessService.closeDataAccess();
        AccessService.createDataAccess(testAccess);
        database = AccessService.getDataAccess(dbName);

        courseList = database.getAllCourses();

        assertNotNull (courseList);
        assertEquals (3 , courseList.size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testCreateDataAccessIP() {
        ArrayList<Course> courseList;
        IPersistenceAccess database;
        start();

        AccessService.closeDataAccess();
        AccessService.createDataAccess(testAccess);
        database = AccessService.getDataAccess(dbName);

        courseList = database.getAllCourses();

        assertNotNull (courseList );
        assertEquals (3,courseList.size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetDataAccess() {
        ArrayList<Course> courseList;
        IPersistenceAccess database;
        start();

        AccessService.closeDataAccess();
        AccessService.createDataAccess(testAccess);
        database = AccessService.getDataAccess(dbName);

        courseList = database.getAllCourses();

        assertNotNull (courseList);
        assertEquals (3,courseList.size());

        assertEquals ("COMP3350", courseList.get(1).getCourseCode());
        assertEquals (3,courseList.get(1).getCreditHours());
        assertEquals ("Computer Science",courseList.get(1).getDepartment());
        assertEquals ("Software Engineering",courseList.get(1).getName());
        assertEquals ("Summer",courseList.get(1).getTerm());
        assertEquals (2021,courseList.get(1).getYear() );

        AccessService.closeDataAccess();
    }

    @Test
    public void testCloseDataAccess() {
        ArrayList<Course> courseList;
        IPersistenceAccess database;
        start();

        AccessService.closeDataAccess();
        AccessService.createDataAccess(testAccess);
        database = AccessService.getDataAccess(dbName);

        courseList = database.getAllCourses();
        assertNotNull(courseList);

        AccessService.closeDataAccess();
    }

}
