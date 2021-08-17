package comp3350.courser.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.courser.business.AccessService;
import comp3350.courser.business.CourseService;
import comp3350.courser.business.SectionService;
import comp3350.courser.objects.Course;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;

import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.tests.persistence.PersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class SectionServiceTest extends TestCase {

    public SectionServiceTest(String arg0) {
        super(arg0);
    }

    private static String dbName = MainActivity.getDBPathName();

    @Test
    public void testIsConflictingWithIdenticalLectures() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        SectionService sectionService = new SectionService();

        int[] c1Time1 = {14, 00};
        int[] c1Time2 = {16, 00};

        TimeRange r1 = new TimeRange(c1Time1, c1Time2);
        ArrayList<TimeSlot> slotSec1 = new ArrayList<TimeSlot>();
        slotSec1.add(new TimeSlot(DayOfWeek.MONDAY,r1, new Date(1, 1), new Date(2, 2)));

        Lecture sec1 = new Lecture(123, "A01", "Prof", slotSec1);

        assertTrue(sectionService.isConflicting(sec1, sec1));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithOverlappingLectures() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        SectionService sectionService = new SectionService();

        int[] c1Time1 = {14, 00};
        int[] c1Time2 = {16, 00};

        TimeRange r1 = new TimeRange(c1Time1, c1Time2);
        ArrayList<TimeSlot> slotSec1 = new ArrayList<TimeSlot>();
        slotSec1.add(new TimeSlot(DayOfWeek.MONDAY, r1, new Date(1, 1), new Date(2, 2)));

        int[] c2Time1 = {15, 00};
        int[] c2Time2 = {17, 00};

        TimeRange r2 = new TimeRange(c2Time1, c2Time2);
        ArrayList<TimeSlot> slotSec2 = new ArrayList<TimeSlot>();
        slotSec2.add(new TimeSlot(DayOfWeek.MONDAY,r2, new Date(1, 1), new Date(2, 2)));

        Lecture sec1 = new Lecture(123, "A01", "Prof", slotSec1);
        Lecture sec2 = new Lecture(123, "A01", "Prof", slotSec2);

        assertTrue(sectionService.isConflicting(sec1, sec2));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithSeparateLectures() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        SectionService sectionService = new SectionService();

        int[] c1Time1 = {14, 00};
        int[] c1Time2 = {16, 00};

        TimeRange r1 = new TimeRange(c1Time1, c1Time2);
        ArrayList<TimeSlot> slotSec1 = new ArrayList<TimeSlot>();
        slotSec1.add(new TimeSlot(DayOfWeek.MONDAY, r1, new Date(1, 1), new Date(2, 2)));

        int[] c2Time1 = {18, 00};
        int[] c2Time2 = {20, 00};

        TimeRange r2 = new TimeRange(c2Time1, c2Time2);
        ArrayList<TimeSlot> slotSec2 = new ArrayList<TimeSlot>();
        slotSec2.add(new TimeSlot(DayOfWeek.MONDAY, r2, new Date(1, 1), new Date(2, 2)));

        Lecture sec1 = new Lecture(123, "A01", "Prof", slotSec1);
        Lecture sec2 = new Lecture(123, "A01", "Prof", slotSec2);

        assertFalse(sectionService.isConflicting(sec1, sec2));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithIdenticalLectureAndLab() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        SectionService sectionService = new SectionService();

        int[] c1Time1 = {14, 00};
        int[] c1Time2 = {16, 00};

        TimeRange r1 = new TimeRange(c1Time1, c1Time2);
        ArrayList<TimeSlot> slotSec1 = new ArrayList<TimeSlot>();
        slotSec1.add(new TimeSlot(DayOfWeek.MONDAY,r1, new Date(1, 1), new Date(2, 2)));

        int[] l1Time1 = {10, 00};
        int[] l1Time2 = {11, 00};

        TimeRange labR1 = new TimeRange(l1Time1, l1Time2);
        ArrayList<TimeSlot> slotLab1 = new ArrayList<TimeSlot>();
        slotLab1.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR1, new Date(1, 1), new Date(2, 2)));

        Lab lab1 = new Lab(234, "B01", "TA", slotLab1);
        ArrayList<Lab> list1 = new ArrayList<Lab>();
        list1.add(lab1);

        Lecture sec1 = new Lecture(123, "A01", "Prof", slotSec1, list1);

        assertTrue(sectionService.isConflicting(sec1, sec1));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithOverlappingLectureAndLab() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        SectionService sectionService = new SectionService();

        int[] c1Time1 = {14, 00};
        int[] c1Time2 = {16, 00};

        TimeRange r1 = new TimeRange(c1Time1, c1Time2);
        ArrayList<TimeSlot> slotSec1 = new ArrayList<TimeSlot>();
        slotSec1.add(new TimeSlot(DayOfWeek.MONDAY,r1, new Date(1, 1), new Date(2, 2)));

        int[] l1Time1 = {10, 00};
        int[] l1Time2 = {11, 00};

        TimeRange labR1 = new TimeRange(l1Time1, l1Time2);
        ArrayList<TimeSlot> slotLab1 = new ArrayList<TimeSlot>();
        slotLab1.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR1, new Date(1, 1), new Date(2, 2)));

        Lab lab1 = new Lab(234, "B01", "TA", slotLab1);
        ArrayList<Lab> list1 = new ArrayList<Lab>();
        list1.add(lab1);

        Lecture sec1 = new Lecture(123, "A01", "Prof", slotSec1, list1);

        int[] c2Time1 = {15, 00};
        int[] c2Time2 = {17, 00};

        TimeRange r2 = new TimeRange(c2Time1, c2Time2);
        ArrayList<TimeSlot> slotSec2 = new ArrayList<TimeSlot>();
        slotSec2.add(new TimeSlot(DayOfWeek.MONDAY,r2, new Date(1, 1), new Date(2, 2)));

        int[] l2Time1 = {10, 00};
        int[] l2Time2 = {12, 00};

        TimeRange labR2 = new TimeRange(l2Time1, l2Time2);
        ArrayList<TimeSlot> slotLab2 = new ArrayList<TimeSlot>();
        slotLab2.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR2, new Date(1, 1), new Date(2, 2)));

        Lab lab2 = new Lab(234, "B01", "TA", slotLab2);
        ArrayList<Lab> list2 = new ArrayList<Lab>();
        list2.add(lab2);

        Lecture sec2 = new Lecture(163, "A03", "Prof2", slotSec2, list2);

        assertTrue(sectionService.isConflicting(sec1, sec2));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithSeparateLectureAndLab() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        SectionService sectionService = new SectionService();

        int[] c1Time1 = {14, 00};
        int[] c1Time2 = {16, 00};

        TimeRange r1 = new TimeRange(c1Time1, c1Time2);
        ArrayList<TimeSlot> slotSec1 = new ArrayList<TimeSlot>();
        slotSec1.add(new TimeSlot(DayOfWeek.MONDAY,r1, new Date(1, 1), new Date(2, 2)));

        int[] l1Time1 = {10, 00};
        int[] l1Time2 = {11, 00};
        TimeRange labR1 = new TimeRange(l1Time1, l1Time2);
        ArrayList<TimeSlot> slotLab1 = new ArrayList<TimeSlot>();
        slotLab1.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR1, new Date(1, 1), new Date(2, 2)));

        Lab lab1 = new Lab(234, "B01", "TA", slotLab1);
        ArrayList<Lab> list1 = new ArrayList<Lab>();
        list1.add(lab1);

        Lecture sec1 = new Lecture(123, "A01", "Prof", slotSec1, list1);

        int[] c2Time1 = {18, 00};
        int[] c2Time2 = {20, 00};

        TimeRange r2 = new TimeRange(c2Time1, c2Time2);
        ArrayList<TimeSlot> slotSec2 = new ArrayList<TimeSlot>();
        slotSec2.add(new TimeSlot(DayOfWeek.MONDAY,r2, new Date(1, 1), new Date(2, 2)));

        int[] l2Time1 = {15, 00};
        int[] l2Time2 = {16, 00};

        TimeRange labR2 = new TimeRange(l2Time1, l2Time2);
        ArrayList<TimeSlot> slotLab2 = new ArrayList<TimeSlot>();
        slotLab2.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR2, new Date(1, 1), new Date(2, 2)));

        Lab lab2 = new Lab(234, "B01", "TA", slotLab2);
        ArrayList<Lab> list2 = new ArrayList<Lab>();
        list2.add(lab2);

        Lecture sec2 = new Lecture(163, "A03", "Prof2", slotSec2, list2);

        assertFalse(sectionService.isConflicting(sec1, sec2));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithIdenticalLabs() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        SectionService sectionService = new SectionService();

        int[] l1Time1 = {10, 00};
        int[] l1Time2 = {11, 00};

        TimeRange labR1 = new TimeRange(l1Time1, l1Time2);
        ArrayList<TimeSlot> slotLab1 = new ArrayList<TimeSlot>();
        slotLab1.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR1, new Date(1, 1), new Date(2, 2)));

        Lab lab1 = new Lab(234, "B01", "TA", slotLab1);

        assertTrue(sectionService.isConflicting(lab1, lab1));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithOverlappingLabs() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        SectionService sectionService = new SectionService();

        int[] l1Time1 = {10, 00};
        int[] l1Time2 = {12, 00};

        TimeRange labR1 = new TimeRange(l1Time1, l1Time2);
        ArrayList<TimeSlot> slotLab1 = new ArrayList<TimeSlot>();
        slotLab1.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR1, new Date(1, 1), new Date(2, 2)));

        Lab lab1 = new Lab(234, "B01", "TA", slotLab1);

        int[] l2Time1 = {11, 00};
        int[] l2Time2 = {13, 00};

        TimeRange labR2 = new TimeRange(l2Time1, l2Time2);
        ArrayList<TimeSlot> slotLab2 = new ArrayList<TimeSlot>();
        slotLab2.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR2, new Date(1, 1), new Date(2, 2)));

        Lab lab2 = new Lab(234, "B01", "TA", slotLab2);

        assertTrue(sectionService.isConflicting(lab1, lab2));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsUniqueOnDuplicateLecture() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        CourseService courseService = new CourseService();
        SectionService sectionService = new SectionService();

        Lecture newLecture = new Lecture(123123, "A01", "Testman", "Test camp", null);
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        lectures.add(newLecture);

        Course newCourse = new Course("COMP1010", "Intro Comp", "CS", "Summer", 2021, 3, lectures);
        String newCourseId = courseService.addCourse(newCourse);

        assertTrue(sectionService.isUnique(newLecture, newCourseId));

        sectionService.addLecture(newCourseId, newLecture);

        assertFalse(sectionService.isUnique(newLecture, newCourseId));

        AccessService.closeDataAccess();
    }
}
