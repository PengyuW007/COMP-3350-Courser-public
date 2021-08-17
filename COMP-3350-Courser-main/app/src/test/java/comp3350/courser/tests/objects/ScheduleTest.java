package comp3350.courser.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;

import comp3350.courser.objects.Course;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.Schedule;
import comp3350.courser.objects.Section;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;

public class ScheduleTest extends TestCase {
    public ScheduleTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testScheduleCreationWithNoLabs(){
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

        TimeRange range1 = new TimeRange(1400, 1800);
        ArrayList<TimeSlot> slotLec1 = new ArrayList<TimeSlot>();
        slotLec1.add(new TimeSlot(DayOfWeek.MONDAY,range1, new Date(1, 1), new Date(2, 2)));

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

        Schedule mySchedule = new Schedule(cList);

        assertEquals(cList, mySchedule.getCourses());

        TimeRange range5 = new TimeRange(1100, 1500);
        ArrayList<TimeSlot> slotLec5 = new ArrayList<TimeSlot>();
        slotLec5.add(new TimeSlot(DayOfWeek.MONDAY,range5, new Date(1, 1), new Date(2, 2)));

        Lecture lec5 = new Lecture(123, "A01", "Prof1", slotLec5);

        ArrayList<Section> lecListAll = new ArrayList<Section>();
        lecListAll.add(lec1);
        lecListAll.add(lec2);
        lecListAll.add(lec3);
        lecListAll.add(lec4);
        lecListAll.add(lec5);

        mySchedule.addSection(lec1);
        mySchedule.addSection(lec2);
        mySchedule.addSection(lec3);
        mySchedule.addSection(lec4);
        mySchedule.addSection(lec5);

        assertEquals(lecListAll, mySchedule.getSections());

    }
}
