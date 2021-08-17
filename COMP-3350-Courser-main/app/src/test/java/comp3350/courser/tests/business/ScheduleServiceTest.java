package comp3350.courser.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.courser.business.AccessService;
import comp3350.courser.business.ScheduleService;
import comp3350.courser.objects.Course;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.Schedule;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.tests.persistence.PersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class ScheduleServiceTest extends TestCase{

    private static String dbName = MainActivity.getDBPathName();

    public ScheduleServiceTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testGetAllSchedulesWithNoLabs() {
        /**
         * course1
         *      lec1
         *          14:00 - 18:00, 1/1 - 2/2, MONDAY
         *      lec2
         *          16:00 - 20:00, 1/1 - 2/2, MONDAY
         * course2
         *      lec3
         *          12:00 - 16:00, 1/1 - 2/2, MONDAY
         *      lec4
         *          10:00 - 14:00, 1/1 - 2/2, MONDAY
         */

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        ScheduleService scheduleService = new ScheduleService();

        TimeRange range1 = new TimeRange(1400, 1800);
        ArrayList<TimeSlot> slotLec1 = new ArrayList<TimeSlot>();
        slotLec1.add(new TimeSlot(DayOfWeek.MONDAY, range1, new Date(1, 1), new Date(2, 2)));

        Lecture lec1 = new Lecture(123, "A01", "Prof1", slotLec1);

        TimeRange range2 = new TimeRange(1600, 2000);
        ArrayList<TimeSlot> slotLec2 = new ArrayList<TimeSlot>();
        slotLec2.add(new TimeSlot(DayOfWeek.MONDAY,range2, new Date(1, 1), new Date(2, 2)));

        Lecture lec2 = new Lecture(1234, "A02", "Prof2", slotLec2);

        ArrayList<Lecture> lecList1 = new ArrayList<Lecture>();
        lecList1.add(lec1);
        lecList1.add(lec2);

        Course course1 = new Course("TEST1234", "Test course", "Tests", "Summer", 2021, 3, lecList1);

        TimeRange range3 = new TimeRange(1200, 1600);
        ArrayList<TimeSlot> slotLec3 = new ArrayList<TimeSlot>();
        slotLec3.add(new TimeSlot(DayOfWeek.MONDAY,range3, new Date(1, 1), new Date(2, 2)));

        Lecture lec3 = new Lecture(123, "A01", "Prof3", slotLec3);

        TimeRange range4 = new TimeRange(1000, 1400);
        ArrayList<TimeSlot> slotLec4 = new ArrayList<TimeSlot>();
        slotLec4.add(new TimeSlot(DayOfWeek.MONDAY,range4, new Date(1, 1), new Date(2, 2)));

        Lecture lec4 = new Lecture(1234, "A02", "Prof4", slotLec4);

        ArrayList<Lecture> lecList2 = new ArrayList<Lecture>();
        lecList2.add(lec3);
        lecList2.add(lec4);

        Course course2 = new Course("TEST1234", "Test course", "Tests", "Summer", 2021, 3, lecList2);

        ArrayList<Course> cList = new ArrayList<Course>();
        cList.add(course1);
        cList.add(course2);

        ArrayList<Schedule> sList;
        sList = scheduleService.generateAllSchedules(cList);

        Schedule firstSched = sList.get(0);
        Course firstCourse = firstSched.getCourses().get(0);
        Lecture firstLecture = firstCourse.getLectures().get(0);

        assertEquals(4, sList.size());
        assertEquals(2, sList.get(0).getCourses().size());
        assertEquals("Prof1", firstLecture.getInstructor());

        AccessService.closeDataAccess();
    }

    @Test
    public void testGetAllSchedulesWithLabs() {
        /**
         * course1
         *      lecture1: 14:00 - 18:00, 1/1 - 2/2, MONDAY
         *          lab1: 14:00 - 16:00, 1/1 - 2/2, WEDNESDAY
         *      lecture2: 16:00 - 20:00. 1/1 - 2/2, MONDAY
         *          lab1: 14:00 - 16:00, 1/1 - 2/2, WEDNESDAY
         * course2
         *      lecture3: 12:00 - 16:00, 1/1 - 2/2, MONDAY
         *          lab2: 14:00 - 16:00, 1/1 - 2/2, THURSDAY
         *      lecture4: 10:00 - 14:00, 1/1 - 2/2, MONDAY
         *          lab2: 14:00 - 16:00, 1/1 - 2/2, THURSDAY
         *
         */

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        ScheduleService scheduleService = new ScheduleService();

        TimeRange range1 = new TimeRange(1400, 1800);
        ArrayList<TimeSlot> slotLec1 = new ArrayList<TimeSlot>();
        slotLec1.add(new TimeSlot(DayOfWeek.MONDAY,range1, new Date(1, 1), new Date(2, 2)));

        TimeRange labRange1 = new TimeRange(1400, 1600);
        ArrayList<TimeSlot> slotLab1 = new ArrayList<TimeSlot>();
        slotLab1.add(new TimeSlot(DayOfWeek.WEDNESDAY,labRange1, new Date(1, 1), new Date(2, 2)));

        ArrayList<Lab> labList1 = new ArrayList<Lab>();
        labList1.add(new Lab(454, "B01", "TA1", slotLab1));

        Lecture lec1 = new Lecture(123, "A01", "Prof1", slotLec1, labList1);

        TimeRange range2 = new TimeRange(1600, 2000);
        ArrayList<TimeSlot> slotLec2 = new ArrayList<TimeSlot>();
        slotLec2.add(new TimeSlot(DayOfWeek.MONDAY,range2, new Date(1, 1), new Date(2, 2)));

        Lecture lec2 = new Lecture(1234, "A02", "Prof2", slotLec2, labList1);

        ArrayList<Lecture> lecList1 = new ArrayList<Lecture>();
        lecList1.add(lec1);
        lecList1.add(lec2);

        Course course1 = new Course("TEST1234", "Test course", "Tests", "Summer", 2021, 3, lecList1);

        TimeRange range3 = new TimeRange(1200, 1600);
        ArrayList<TimeSlot> slotLec3 = new ArrayList<TimeSlot>();
        slotLec3.add(new TimeSlot(DayOfWeek.MONDAY,range3, new Date(1, 1), new Date(2, 2)));

        TimeRange labRange2 = new TimeRange(1400, 1600);
        ArrayList<TimeSlot> slotLab2 = new ArrayList<TimeSlot>();
        slotLab2.add(new TimeSlot(DayOfWeek.THURSDAY,labRange2, new Date(1, 1), new Date(2, 2)));

        ArrayList<Lab> labList2 = new ArrayList<Lab>();
        labList2.add(new Lab(454, "B01", "TA2", slotLab2));

        Lecture lec3 = new Lecture(123, "A01", "Prof3", slotLec3, labList2);

        TimeRange range4 = new TimeRange(1000, 1400);
        ArrayList<TimeSlot> slotLec4 = new ArrayList<TimeSlot>();
        slotLec4.add(new TimeSlot(DayOfWeek.MONDAY,range4, new Date(1, 1), new Date(2, 2)));

        Lecture lec4 = new Lecture(1234, "A02", "Prof4", slotLec4, labList2);

        ArrayList<Lecture> lecList2 = new ArrayList<Lecture>();
        lecList2.add(lec3);
        lecList2.add(lec4);

        Course course2 = new Course("TEST1234", "Test course", "Tests", "Summer", 2021, 3, lecList2);

        ArrayList<Course> cList = new ArrayList<Course>();
        cList.add(course1);
        cList.add(course2);

        ArrayList<Schedule> sList;
        sList = scheduleService.generateAllSchedules(cList);

        Schedule firstSched = sList.get(0);
        Course firstCourse = firstSched.getCourses().get(0);
        Lecture firstLecture = firstCourse.getLectures().get(0);

        assertEquals(2, sList.get(0).getCourses().size());
        assertEquals("Prof1", firstLecture.getInstructor());
        assertEquals(4, sList.size());

        AccessService.closeDataAccess();
    }

}
