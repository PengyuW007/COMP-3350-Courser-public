package comp3350.courser.tests.persistence;

import comp3350.courser.objects.Course;
import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.persistence.PersistenceAccessDB;
import comp3350.courser.presentation.MainActivity;

import junit.framework.*;
import org.junit.Test;

public class TestCoursePersistence extends TestCase {

    private Course course1 = new Course("COMP3430", "Computer Science", "Operating Systems", "Winter", 2022, 3, null);
    String course1Id = "COMP3430Winter2022";
    private Course course2 = new Course("COMP4350", "Computer Science", "Software Engineering 2", "Winter", 2022, 3, null);
    String course2Id = "COMP4350Winter2022";

    private IPersistenceAccess testAccess;

    @Test
    public void testSimple() {
        assertEquals(1, 1);
    }

    private void start(){
        //SWITCH BETWEEN REAL DB AND STUB
    //STUB
        //testAccess = new PersistenceAccess(MainActivity.getDBPathName());
        //testAccess.open(MainActivity.getDBPathName());

    //REAL
        testAccess = new PersistenceAccessDB(MainActivity.getDBPathName());
        testAccess.open(MainActivity.getDBPathName());
    }

    @Test
    public void testAddCourse() {
        start();

        assertEquals(3,testAccess.getAllCourses().size());

        testAccess.addCourse(course1);

        assertEquals(4, testAccess.getAllCourses().size());
        assertEquals(course1Id, testAccess.getCourseId(course1));

        testAccess.deleteCourse(course1Id);
        assertEquals (3,testAccess.getAllCourses().size());
        testAccess.close();
    }

    @Test
    public void testAddCourseMultiple() {
        start();

        assertEquals(3, testAccess.getAllCourses().size());
        testAccess.addCourse(course1);
        testAccess.addCourse(course2);

        assertEquals(5, testAccess.getAllCourses().size());
        assertEquals(course1Id, testAccess.getCourseId(course1));
        assertEquals(course2Id, testAccess.getCourseId(course2));

        testAccess.deleteCourse(course1Id);
        testAccess.deleteCourse(course2Id);
        assertEquals (3,testAccess.getAllCourses().size());

        testAccess.close();

    }

    @Test
    public void testGetCourseIDFromEmptyList() {
        start();

        assertNull(testAccess.getCourseId(course1));
    }

    @Test
    public void testGetCourseIDNotFound(){
        start();

        assertNull(testAccess.getCourseId(course1));
    }

    @Test
    public void testDeleteCourse(){
        start();

        String courseID = testAccess.addCourse(course1);

        assertTrue(course1.equals(testAccess.getCourseById(courseID)));

        testAccess.deleteCourse(courseID);
        assertEquals (3,testAccess.getAllCourses().size());

        assertNull(testAccess.getCourseById(courseID));
    }
}
