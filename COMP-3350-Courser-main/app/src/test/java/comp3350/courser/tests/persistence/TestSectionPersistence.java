package comp3350.courser.tests.persistence;

import comp3350.courser.objects.Course;

import comp3350.courser.objects.Section;

import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.persistence.IPersistenceAccess;

import comp3350.courser.persistence.PersistenceAccessDB;

import comp3350.courser.presentation.MainActivity;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

public class TestSectionPersistence extends TestCase {


    private Lecture lecture1;
    private Lecture lecture2;
    private Lab lab1;
    private Lab lab2;
    private IPersistenceAccess testAccess;

    private void start() {
        //SWITCH BETWEEN REAL DB AND STUB
        //STUB
        //testAccess = new PersistenceAccess(MainActivity.getDBPathName());
        //testAccess.open(MainActivity.getDBPathName());

        //REAL
        testAccess = new PersistenceAccessDB(MainActivity.getDBPathName());
        testAccess.open(MainActivity.getDBPathName());
    }

    private String dbPath = MainActivity.getDBPathName();

    @Test
    public void testSimple() {
        assertEquals(1, 1);
    }

    @Test
    public void testAddLecturesWithoutLabs() {

        start();
        Course course1 = new Course("COMP3380", "Databases Concepts and Usage", "Computer Science", "Fall", 2015, 3, null);

        testAccess.addCourse(course1);
        String currCourseID = testAccess.getCourseId(course1);
        //only add lectures, normal test
        lecture1 = new Lecture(1103, "A01", "John P. Braico (P)", "RL", null);
        testAccess.addLecture(currCourseID, lecture1);
        ArrayList<Lecture> lectures = testAccess.getLectures(currCourseID);
        assertEquals(1, lectures.size());
        assertEquals(1103, lectures.get(0).getCrn());

        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());
        testAccess.close();
    }

    @Test
    public void testAddSection() {

        start();
        Course course3 = new Course("COMP3380", "Databases Concepts and Usage", "Computer Science", "Fall", 2016, 3, null);

        testAccess.addCourse(course3);
        String currCourseID = testAccess.getCourseId(course3);
        //only add lectures, normal test
        lecture1 = new Lecture(1103, "A01", "John P. Braico (P)", "RL", null);
        testAccess.addLecture(currCourseID, lecture1);
        ArrayList<Lecture> lectures = testAccess.getLectures(currCourseID);

        assertEquals(1, lectures.size());
        assertEquals(1103, lectures.get(0).getCrn());

        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());

        testAccess.close();

    }//end testAddLecturesWithoutLabs


    @Test
    public void testAddLecturesWithLabs() {

        //COMP1012 has labs
        start();
        Course course2 = new Course("COMP1012", "Computer Programming for Scientists and Engineers", "Computer Science", "Fall", 2015, 3, null);
        testAccess.addCourse(course2);
        String currCourseID = testAccess.getCourseId(course2);

        //labs addition
        lab1 = new Lab(11761, "B01", "Robert W. Guderian (P)", null);
        ArrayList<Lab> labArraylist = new ArrayList<Lab>();
        labArraylist.add(lab1);

        //lectures addition
        lecture1 = new Lecture(11759, "A01", "Robert W. Guderian (P)", "RL", null, labArraylist);
        testAccess.addLecture(currCourseID, lecture1);
        testAccess.addLab("COMP1012Fall2015", "11759COMP1012Fall2015", lab1);

        ArrayList<Lecture> lectures = testAccess.getLectures(currCourseID);
        ArrayList<Lab> labs = testAccess.getLabs(currCourseID);

        assertEquals(1, lectures.size());
        assertEquals(1, labArraylist.size());//lab arraylist added successfully
        assertEquals(0, labs.size());//
        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());
        testAccess.close();
    }//end testAddLecturesByLab

    @Test
    public void testAddSectionMultiple() {

        start();
        Course course4 = new Course("COMP1012", "Computer Programming for Scientists and Engineers", "Computer Science", "Fall", 2016, 3, null);

        testAccess.addCourse(course4);
        String currCourseID = testAccess.getCourseId(course4);
        lecture1 = new Lecture(10197, "A01", "Robert W. Guderian (P)", "RL", null);
        lecture2 = new Lecture(15304, "A02", "Adam Pazdor (P)", "RL", null);
        testAccess.addLecture(currCourseID, lecture1);
        testAccess.addLecture(currCourseID, lecture2);
        ArrayList<Lecture> lectures = testAccess.getLectures(currCourseID);

        assertEquals(2, lectures.size());


        assertEquals(10197, lectures.get(0).getCrn());
        assertEquals(15304, lectures.get(1).getCrn());

        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());
        testAccess.close();

    }//end testAddSectionMultiple

    @Test
    public void testAddDuplicateLectures() {

        start();
        Course course = new Course("COMP3380", "Databases Concepts and Usage", "Computer Science", "Fall", 2019, 3, null);

        testAccess.addCourse(course);
        String currCourseID = testAccess.getCourseId(course);
        lecture1 = new Lecture(10197, "A01", "Robert W. Guderian (P)", "RL", null);
        lecture2 = new Lecture(10197, "A01", "Robert W. Guderian (P)", "RL", null);
        testAccess.addLecture(currCourseID, lecture1);

        //assertNull(testAccess.addLecture(currCourseID, lecture1));
        ArrayList<Lecture> lectures = testAccess.getLectures(currCourseID);

        assertEquals(1, lectures.size());
        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());

        testAccess.close();
    }//end testAddDuplicateLectures


    @Test
    public void testAddDuplicateLabs() {

        start();
        Course course = new Course("COMP3380", "Databases Concepts and Usage", "Computer Science", "Fall", 2018, 3, null);

        testAccess.addCourse(course);
        String currCourseID = testAccess.getCourseId(course);

        lab1 = new Lab(11761, "B01", "Robert W. Guderian (P)", null);
        lab2 = new Lab(11761, "B01", "Robert W. Guderian (P)", null);
        ArrayList<Lab> labArraylist = new ArrayList<Lab>();
        labArraylist.add(lab1);
        labArraylist.add(lab2);

        //lectures addition
        lecture1 = new Lecture(11759, "A01", "Robert W. Guderian (P)", "RL", null, labArraylist);
        testAccess.addLecture(currCourseID, lecture1);
        testAccess.addLab("COMP1012Fall2015", "11759COMP1012Fall2015", lab1);

        ArrayList<Lecture> lectures = testAccess.getLectures(currCourseID);
        ArrayList<Lab> labs = testAccess.getLabs(currCourseID);

        assertEquals(1, lectures.size());
        assertEquals(2, labArraylist.size());
        assertEquals(0, labs.size());
        assertEquals(11759, lectures.get(0).getCrn());

        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());

        testAccess.close();
    }

    @Test

    public void testGetSectionID() {

        start();
        Course course = new Course("COMP3380", "Databases Concepts and Usage", "Computer Science", "Fall", 1992, 3, null);

        testAccess.addCourse(course);
        String currCourseID = testAccess.getCourseId(course);

        lecture1 = new Lecture(10197, "A01", "Robert W. Guderian (P)", "RL", null);

        testAccess.addLecture(currCourseID, lecture1);

        assertEquals("10197COMP3380Fall2020", testAccess.getSectionId(lecture1, "COMP3380Fall2020"));
        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());
        testAccess.close();
    }//end testGetSectionID

    @Test
    public void testGetSectionIDSectionNotExist() {

        start();
        lecture1 = new Lecture(10197, "A01", "Robert W. Guderian (P)", "RL", null);

        assertEquals(null, testAccess.getSectionId(lecture1, "COMP3380Fall2015"));
        testAccess.close();

    }//end testGetSectionIDSectionNotExist

    @Test
    public void testGetSectionByID() {

        start();
        Course course = new Course("COMP3380", "Databases Concepts and Usage", "Computer Science", "Fall", 1993, 3, null);

        testAccess.addCourse(course);
        String currCourseID = testAccess.getCourseId(course);

        lecture1 = new Lecture(11759, "A01", "Robert W. Guderian (P)", "RL", null);
        testAccess.addLecture(currCourseID, lecture1);

        Section section1 = testAccess.getSectionById("11759COMP1012Fall2020");
        assertEquals("11759COMP1012Fall2020", testAccess.getSectionId(section1, "COMP1012Fall2020"));
        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());
        testAccess.close();

    }//end testGetSectionByID

    @Test
    public void testGetSectionByIDNotExist() {

        start();
        Course course = new Course("COMP3380", "Databases Concepts and Usage", "Computer Science", "Fall", 1994, 3, null);

        testAccess.addCourse(course);
        String currCourseID = testAccess.getCourseId(course);

        lecture1 = new Lecture(11759, "A01", "Robert W. Guderian (P)", "RL", null);

        String lectureID = "11111COMP1012Fall2020";//random lecture ID
        Section section1 = testAccess.getSectionById(lectureID);

        assertNull(section1);

        testAccess.deleteCourse(currCourseID);
        assertEquals (3,testAccess.getAllCourses().size());
        testAccess.close();

    }//end testGetSectionByIDNotExist

    public void testDeleteSection() {

        start();
        Course course5 = new Course("COMP3380", "Databases Concepts and Usage", "Computer Science", "Fall", 2017, 3, null);

        String courseID = testAccess.addCourse(course5);

        lecture1 = new Lecture(1103, "A01", "John P. Braico (P)", "RL", null);
        String lecID = testAccess.addLecture(courseID, lecture1);

        assertTrue(lecture1.equals(testAccess.getSectionById(lecID)));

        testAccess.deleteSection(lecID);

        assertNull(testAccess.getSectionById(lecID));

        testAccess.deleteCourse(courseID);
        assertEquals (3,testAccess.getAllCourses().size());

        testAccess.close();
    }


}
