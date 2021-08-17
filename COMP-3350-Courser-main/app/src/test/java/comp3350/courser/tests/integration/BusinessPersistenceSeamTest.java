package comp3350.courser.tests.integration;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.courser.business.*;
import comp3350.courser.objects.*;
import comp3350.courser.persistence.PersistenceAccessDB;
import comp3350.courser.presentation.MainActivity;

public class BusinessPersistenceSeamTest extends TestCase {
    private static String dbName = MainActivity.getDBPathName();
    private static PersistenceAccessDB access;

    public BusinessPersistenceSeamTest(String args) {
        super(args);
    }

    public void start() {
        access = new PersistenceAccessDB(dbName);
    }

    public void testCourseService() {
        CourseService cs;
        SectionService ss;
        TimeSlotService ts;

        Course course1, course2;
        Lecture lec1, lec2;
        Lab lab1, lab2;
        TimeSlot timeSlot1, timeSlot2;

        start();

        AccessService.closeDataAccess();

        System.out.println("\nStarting Integration test of CourseService to persistence");

        AccessService.createDataAccess(access);

        cs = new CourseService();
        assertTrue(access.deleteCourse("COMP3010Summer2020"));

        //The current number of courses in DB
        assertEquals(3, cs.getCourseList().size());

        //Add one course to DB
        course1 = new Course("11759", "COMP3010", "Distributed Computing", "Computer Science", "Summer", 2020, 3, null);
        assertTrue(cs.isUnique(course1));//There is "course", so this will not be unique

        assertEquals("COMP3010Summer2020", cs.addCourse(course1));
        //After addition
        assertFalse(cs.isUnique(course1));//added would not be unique any more
        //After adding one course, the courseList size changed
        assertEquals(4, cs.getCourseList().size());

        course2 = cs.getCourseById("COMP3010Summer2020");
        assertEquals("Distributed Computing", course2.getName());
        assertEquals("COMP3010Summer2020", cs.getCourseId(course2));


        assertTrue(access.deleteCourse("COMP3010Summer2020"));
        assertEquals(3, cs.getCourseList().size());

        AccessService.closeDataAccess();

        System.out.println("Finished Integration test of CourseService to persistence");
    }//end testCourseService


    public void testSectionService() {
        SectionService ss;
        CourseService cs;
        Course course;
        Section section1, section2;
        Lecture lec1, lec2;
        Lab lab1, lab2;
        String result;

        start();

        AccessService.closeDataAccess();

        System.out.println("\nStarting Integration test of SectionService to persistence");

        AccessService.createDataAccess(access);

        ss = new SectionService();
        cs = new CourseService();

        assertTrue(access.deleteCourse("COMP1012Fall2021"));
        course = new Course("COMP1012", "Computer Science", "Operating Systems", "Fall", 2021, 3, null);
        cs.addCourse(course);
        String currCourseID = cs.getCourseId(course);
        lec1 = new Lecture(11759, "11759COMP1012Fall2021", "Robert W. Guderian (P)", "RL", null);
        lab1 = new Lab(11761, "11761COMP1012Fall2021", "Robert W. Guderian (P)", null);

        assertFalse(ss.isConflicting(lec1, lec1));
        assertTrue(access.deleteSection("11759COMP1012Fall2021"));

        //add a lecture
        result = ss.addLecture(currCourseID, lec1);
        assertEquals("11759COMP1012Fall2021", result);
        assertTrue(access.getLectures("COMP1012Fall2021").size()==1);//add one lecture to the course

        result = ss.addLab("COMP1012Fall2021","11759COMP1012Fall2021",lab1);
        assertEquals("11761COMP1012Fall2021",result);

        int crn = ss.getSectionById("11761COMP1012Fall2021").getCrn();
        assertTrue(crn==11761);

        assertFalse(ss.isUnique(lec1,"COMP1012Fall2021"));

        assertTrue(access.deleteSection("11761COMP1012Fall2021"));//delete lab of the course
        assertTrue(access.deleteSection("11759COMP1012Fall2021"));//delete lecture of the course
        assertTrue(access.deleteCourse("COMP1012Fall2021"));//delete the course
        assertEquals(3, cs.getCourseList().size());
        AccessService.closeDataAccess();

        System.out.println("Finished Integration test of SectionService to persistence");

    }//end testSectionService

    public void testTimeSlotService() {
        CourseService cs;
        SectionService ss;
        TimeSlotService ts;
        Course course;
        Lecture lec1,lec2;
        Lab lab1;
        TimeSlot timeSlot1;

        start();

        AccessService.closeDataAccess();

        System.out.println("\nStarting Integration test of TimeSlotService to persistence");

        AccessService.createDataAccess(access);
        assertTrue(access.deleteCourse("COMP1012Fall2021"));
        cs = new CourseService();
        ss = new SectionService();


        lec1 = new Lecture(11759, "11759COMP1012Fall2021", "Robert W. Guderian (P)", "RL", null);

        ts = new TimeSlotService();
        TimeRange range1 = new TimeRange(1400,1800);
        ArrayList<TimeSlot>slotLec1 = new ArrayList<TimeSlot>();
        timeSlot1=new TimeSlot(DayOfWeek.MONDAY,range1, new Date(1, 1), new Date(2, 2));
        slotLec1.add(timeSlot1);

        ArrayList<Lecture>lectures = new ArrayList<Lecture>();
        lectures.add(lec1);

        course = new Course("COMP1012", "Computer Science", "Operating Systems", "Fall", 2021, 3,lectures);
        String currCourseID = cs.getCourseId(course);
        cs.addCourse(course);

        assertTrue(ts.isConflicting(timeSlot1,timeSlot1));

        access.deleteSection("11759COMP1012Fall2021");//delete lecture of the course
        assertTrue(access.deleteCourse("COMP1012Fall2021"));//delete the course
        assertNull(access.getCourseById("COMP1012Fall2021"));
        assertNull(access.getSectionById("11759COMP1012Fall2021"));
        assertEquals(3, cs.getCourseList().size());
        AccessService.closeDataAccess();

        System.out.println("Finished Integration test of TimeSlotService to persistence");

    }


}
