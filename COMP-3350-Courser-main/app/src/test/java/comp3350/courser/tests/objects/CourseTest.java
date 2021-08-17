package comp3350.courser.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.Course;

import java.util.ArrayList;
import java.util.EnumMap;

public class CourseTest extends TestCase {
    public CourseTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testCourseCreation(){
        /**
         * course1
         *      lec1
         *          14:00 - 18:00, 1/1 - 2/2, MONDAY
         *      lec2
         *          16:00 - 20:00, 1/1 - 2/2, MONDAY
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

        assertEquals("TEST1234", course1.getCourseCode());
        assertEquals("Test course", course1.getName());
        assertEquals("Tests", course1.getDepartment());
        assertEquals("Summer", course1.getTerm());
        assertEquals(2021, course1.getYear());
        assertEquals(3, course1.getCreditHours());
        assertEquals(lecList1, course1.getLectures());
    }


    @Test
    public void testAddLecture(){
        /**
        * course1
        *       lec1
        *           14:00 - 18:00, 1/1 - 2/2, MONDAY
        *       lec2
        *           16:00 - 20:00, 1/1 - 2/2, MONDAY
        *       lec3
        *           10:00 - 12:00, 1/1 - 2/2, WEDNESDAY
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

        TimeRange range3 = new TimeRange(1000, 1200);
        ArrayList<TimeSlot> slotLec3 = new ArrayList<TimeSlot>();
        slotLec3.add(new TimeSlot(DayOfWeek.WEDNESDAY,range3, new Date(1, 1), new Date(2, 2)));

        Lecture lec3 = new Lecture(12345, "A03", "Prof3", slotLec3);

        lecList1.add(lec3);

        course1.addLecture(lec3);

        assertEquals(lecList1, course1.getLectures());
    }

    @Test
    public void testEquals(){
        /**
        * course1
        *     lec1
        *           14:00 - 18:00, 1/1 - 2/2, MONDAY
        *     lec2
        *           16:00 - 20:00, 1/1 - 2/2, MONDAY
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
        Course course2 = new Course("TEST1234", "Test course", "Tests", "Summer", 2021, 3, lecList1);
        Course course3 = new Course("T1234", "Tesrse", "Test", "Summer", 2021, 3, lecList1);

        assertTrue(course1.equals(course2));

        assertFalse(course1.equals(course3));
    }

}
