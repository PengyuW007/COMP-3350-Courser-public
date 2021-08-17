package comp3350.courser.tests.persistence;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.ArrayList;

import comp3350.courser.objects.Course;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.persistence.PersistenceAccessDB;
import comp3350.courser.presentation.MainActivity;

public class TestTimeSlotPersistence extends TestCase {

    Course course;
    TimeSlot ts1;
    TimeSlot ts2;
    Lecture lec1;
    Lecture lec2;
    Lab l;

    IPersistenceAccess testAccess;

    @BeforeClass
    private void start(){

        //SWITCH BETWEEN REAL DB AND STUB

    //STUB
        //testAccess = new PersistenceAccess(MainActivity.getDBPathName());
        //testAccess.open(MainActivity.getDBPathName());

    //REAL
        testAccess = new PersistenceAccessDB(MainActivity.getDBPathName());
        testAccess.open(MainActivity.getDBPathName());

        /**
         * course1
         *      lec1
         *          14:00 - 18:00, 1/1 - 2/2, MONDAY
         *      lec2
         *          16:00 - 20:00, 1/1 - 2/2, WEDNESDAY
         */

        TimeRange range1 = new TimeRange(1400, 1800);
        ArrayList<TimeSlot> slotLec1 = new ArrayList<TimeSlot>();
        ts1 = new TimeSlot(DayOfWeek.MONDAY,range1, new Date(1, 1), new Date(2, 2));
        slotLec1.add(ts1);

        lec1 = new Lecture(123, "A01", "Prof1", slotLec1);

        TimeRange range2 = new TimeRange(1600, 2000);
        ArrayList<TimeSlot> slotLec2 = new ArrayList<TimeSlot>();
        ts2 = new TimeSlot(DayOfWeek.WEDNESDAY,range2, new Date(1, 1), new Date(2, 2));
        slotLec2.add(ts2);

        lec2 = new Lecture(1234, "A02", "Prof2", slotLec2);

        ArrayList<Lecture> lecList1 = new ArrayList<Lecture>();
        lecList1.add(lec1);
        lecList1.add(lec2);

        l = new Lab(354, "B03", "TA", slotLec2);

        course = new Course("TEST1234", "Test course", "Tests", "Summer", 2022, 3, lecList1);

    }

    @Test
    public void testAddTimeSlot(){
        start();

        String courseID = testAccess.addCourse(course);

        String lecID1 = testAccess.addLecture(courseID, lec1);

        String timeID1 = testAccess.addTimeSlot(lecID1, ts1);

        assertTrue(ts1.isEqual(testAccess.getTimeSlotById(timeID1)));

        testAccess.deleteCourse(courseID);

        assertEquals (3,testAccess.getAllCourses().size());

    }

    @Test
    public void testAddMultipleTimeSlots(){
        start();

        String courseID = testAccess.addCourse(course);

        String lecID1 = testAccess.addLecture(courseID, lec1);
        String lecID2 = testAccess.addLecture(courseID, lec2);

        String timeID1 = testAccess.addTimeSlot(lecID1, ts1);
        String timeID2 = testAccess.addTimeSlot(lecID2, ts2);


        assertTrue(ts1.isEqual(testAccess.getTimeSlotById(timeID1)));
        assertTrue(ts2.isEqual(testAccess.getTimeSlotById(timeID2)));

        testAccess.deleteCourse(courseID);

        assertEquals (3,testAccess.getAllCourses().size());

    }

    @Test
    public void testDeleteTimeSlot(){
        start();

        String courseID = testAccess.addCourse(course);

        String lecID1 = testAccess.addLecture(courseID, lec1);
        String lecID2 = testAccess.addLecture(courseID, lec2);

        String timeID1 = testAccess.addTimeSlot(lecID1, ts1);
        String timeID2 = testAccess.addTimeSlot(lecID2, ts2);

        assertTrue(ts1.isEqual(testAccess.getTimeSlotById(timeID1)));
        assertTrue(ts2.isEqual(testAccess.getTimeSlotById(timeID2)));

        testAccess.deleteTimeSlot(timeID1);

        assertNull(testAccess.getTimeSlotById(timeID1));
        assertNotNull(testAccess.getTimeSlotById(timeID2));

        testAccess.deleteCourse(courseID);

        assertEquals (3,testAccess.getAllCourses().size());
    }

    @Test
    public void testGetTimeSlotById(){
        start();

        String courseID = testAccess.addCourse(course);

        String lecID1 = testAccess.addLecture(courseID, lec1);

        String timeID1 = testAccess.addTimeSlot(lecID1, ts1);

        assertTrue(ts1.isEqual(testAccess.getTimeSlotById(timeID1)));

        testAccess.deleteCourse(courseID);

        assertEquals (3,testAccess.getAllCourses().size());
    }

    @Test
    public void testGetTimeSlotByIdOnEmptyList(){
        start();
        assertNull(testAccess.getTimeSlotById("TESTID"));
    }

    @Test
    public void testGetTimeSlotsLecture() {
        start();

        String courseID = testAccess.addCourse(course);

        String lecID1 = testAccess.addLecture(courseID, lec1);
        String lecID2 = testAccess.addLecture(courseID, lec2);

        String timeID1 = testAccess.addTimeSlot(lecID1, ts1);
        String timeID2 = testAccess.addTimeSlot(lecID2, ts2);

        ArrayList<TimeSlot> lectureTimeSlots = testAccess.getTimeSlots(lecID1);

        assertEquals(1, lectureTimeSlots.size());
        assertEquals(DayOfWeek.MONDAY, lectureTimeSlots.get(0).getDayOfWeek());

        testAccess.deleteCourse(courseID);

        assertEquals (3,testAccess.getAllCourses().size());

    }

    @Test
    public void testGetTimeSlotsLectureMultiple() {
        start();

        String courseID = testAccess.addCourse(course);

        String lecID1 = testAccess.addLecture(courseID, lec1);

        String timeID1 = testAccess.addTimeSlot(lecID1, ts1);
        String timeID2 = testAccess.addTimeSlot(lecID1, ts2);

        ArrayList<TimeSlot> lectureTimeSlots = testAccess.getTimeSlots(lecID1);

        assertEquals(2, lectureTimeSlots.size());
        assertEquals(DayOfWeek.MONDAY, lectureTimeSlots.get(0).getDayOfWeek());
        assertEquals(DayOfWeek.WEDNESDAY, lectureTimeSlots.get(1).getDayOfWeek());

        testAccess.deleteCourse(courseID);

        assertEquals (3,testAccess.getAllCourses().size());
    }

    @Test
    public void testGetTimeSlotsLab() {
        start();

        String courseID = testAccess.addCourse(course);

        String lecID1 = testAccess.addLecture(courseID, lec1);

        String labID = testAccess.addLab(courseID, lecID1,  l);

        String timeID1 = testAccess.addTimeSlot(lecID1, ts1);
        String timeID2 = testAccess.addTimeSlot(labID, ts2);

        ArrayList<TimeSlot> lectureTimeSlots = testAccess.getTimeSlots(labID);

        assertEquals(1, lectureTimeSlots.size());
        assertEquals(DayOfWeek.WEDNESDAY, lectureTimeSlots.get(0).getDayOfWeek());
        assertEquals(1, lectureTimeSlots.get(0).getStartDate().getDay());
        assertEquals(1, lectureTimeSlots.get(0).getStartDate().getMonth());

        testAccess.deleteCourse(courseID);

        assertEquals (3,testAccess.getAllCourses().size());

    }

    @Test
    public void testGetTimeSlotsSectionFKNotExist() {
        start();

        ArrayList<TimeSlot> lectureTimeSlots = testAccess.getTimeSlots("nonexistentSectionFk");

        assertEquals(0, lectureTimeSlots.size());

    }

    @AfterClass
    public void tearDown(){
        testAccess.close();
    }

}
