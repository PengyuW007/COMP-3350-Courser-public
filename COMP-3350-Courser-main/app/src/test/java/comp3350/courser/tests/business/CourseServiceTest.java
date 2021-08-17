package comp3350.courser.tests.business;

import junit.framework.TestCase;

import org.junit.Test;
import java.util.ArrayList;

import comp3350.courser.business.AccessService;
import comp3350.courser.business.CourseService;
import comp3350.courser.objects.Course;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.tests.persistence.PersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class CourseServiceTest extends TestCase {
    private static String dbName = MainActivity.getDBPathName();

    public CourseServiceTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testGetCourseList(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Course> courseList;
        courseList = courseService.getCourseList();
        assertEquals(3, courseList.size());

        courseService.addCourse(new Course("COMP3010", "Distributed Computing", "Computer Science", "Summer", 2021, 3, null));

        courseList = courseService.getCourseList();

        assertEquals(4, courseList.size());
        assertEquals("COMP3010", courseList.get(3).getCourseCode());

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsUniqueOnEmptyCourse() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        assertTrue(courseService.isUnique(new Course()));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsUniqueOnDuplicateCourse() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        Course newCourse = new Course("COMP3430", "Operating Systems", "Computer Science", "Summer", 2032, 3, null);

        assertTrue(courseService.isUnique(newCourse));

        courseService.addCourse(newCourse);

        assertFalse(courseService.isUnique(newCourse));

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetAllCourses(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Course> courseList;
        courseList = courseService.getAllCourses();
        assertEquals(3, courseList.size());

        courseService.addCourse(new Course("COMP3010", "Distributed Computing", "Computer Science", "Summer", 2021, 3, null));

        courseList = courseService.getAllCourses();

        assertEquals(4, courseList.size());
        assertEquals("COMP3010", courseList.get(3).getCourseCode());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetAllCoursesNoLabs(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Course> courseList;
        courseList = courseService.getAllCourses();
        assertEquals("COMP3380", courseList.get(2).getCourseCode());

        ArrayList<Lecture> lectureList = courseList.get(2).getLectures();
        assertEquals(2, lectureList.size());

        assertEquals(10197, lectureList.get(0).getCrn());
        assertEquals(0,lectureList.get(0).getLabs().size());
        assertEquals(2,lectureList.get(0).getClasses().size());

        assertEquals(15304, lectureList.get(1).getCrn());
        assertEquals(0,lectureList.get(1).getLabs().size());
        assertEquals(2,lectureList.get(1).getClasses().size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetAllCoursesWithLabs(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Course> courseList;
        courseList = courseService.getAllCourses();

        assertEquals("COMP1012", courseList.get(0).getCourseCode());

        ArrayList<Lecture> lectureList = courseList.get(0).getLectures();
        assertEquals(2, lectureList.size());

        assertEquals(11759, lectureList.get(0).getCrn());
        assertEquals(2,lectureList.get(0).getClasses().size());
        assertEquals(2,lectureList.get(0).getLabs().size());
        assertEquals(1,lectureList.get(0).getLabs().get(0).getClasses().size());
        assertEquals(1,lectureList.get(0).getLabs().get(1).getClasses().size());

        assertEquals(11760, lectureList.get(1).getCrn());
        assertEquals(3,lectureList.get(1).getClasses().size());
        assertEquals(2,lectureList.get(1).getLabs().size());
        assertEquals(1,lectureList.get(1).getLabs().get(0).getClasses().size());
        assertEquals(1,lectureList.get(1).getLabs().get(1).getClasses().size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetLecturesNoLabs(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Lecture> lectureList = courseService.getLectures("COMP3380Fall2020");
        assertEquals(2, lectureList.size());

        assertEquals(10197, lectureList.get(0).getCrn());
        assertEquals(0,lectureList.get(0).getLabs().size());
        assertEquals(2,lectureList.get(0).getClasses().size());

        assertEquals(15304, lectureList.get(1).getCrn());
        assertEquals(0,lectureList.get(1).getLabs().size());
        assertEquals(2,lectureList.get(1).getClasses().size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetLecturesWithLabs(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Lecture> lectureList = courseService.getLectures("COMP1012Fall2020");
        assertEquals(2, lectureList.size());

        assertEquals(11759, lectureList.get(0).getCrn());
        assertEquals(2,lectureList.get(0).getClasses().size());
        assertEquals(2,lectureList.get(0).getLabs().size());
        assertEquals(1,lectureList.get(0).getLabs().get(0).getClasses().size());
        assertEquals(1,lectureList.get(0).getLabs().get(1).getClasses().size());

        assertEquals(11760, lectureList.get(1).getCrn());
        assertEquals(3,lectureList.get(1).getClasses().size());
        assertEquals(2,lectureList.get(1).getLabs().size());
        assertEquals(1,lectureList.get(1).getLabs().get(0).getClasses().size());
        assertEquals(1,lectureList.get(1).getLabs().get(1).getClasses().size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetLabs(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Lab> labList = courseService.getLabs("11759COMP1012Fall2020","COMP1012Fall2020");
        assertEquals(2, labList.size());

        assertEquals(11761, labList.get(0).getCrn());
        assertEquals(1,labList.get(0).getClasses().size());

        assertEquals(11762, labList.get(1).getCrn());
        assertEquals(1,labList.get(1).getClasses().size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetLecturesCourseIdNotExist(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Lecture> lectureList = courseService.getLectures("nonexistentCourseID");
        assertEquals(0, lectureList.size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetLecturesCourseWithNoLecture(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        courseService.addCourse(new Course("COMP3010", "Distributed Computing", "Computer Science", "Summer", 2021, 3, null));

        ArrayList<Lecture> lectureList = courseService.getLectures("COMP3010Summer2021");
        assertEquals(0, lectureList.size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetLabLectureIdNotExist(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Lab> labList = courseService.getLabs("nonexistentLectureID", "COMP3010Summer2021");
        assertEquals(0, labList.size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetLabCourseIdNotExist(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Lab> labList = courseService.getLabs("nonexistentLectureID", "nonexistentCourseID");
        assertEquals(0, labList.size());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetLabLectureWithNoLab(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();

        ArrayList<Lab> labList = courseService.getLabs("1103COMP3350Summer2021", "COMP3350Summer2021");
        assertEquals(0, labList.size());

        AccessService.closeDataAccess();
    }

}
