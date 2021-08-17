package comp3350.courser.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumMap;

import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;

public class SectionTest extends TestCase {
    public SectionTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testLectureCreationWithNoLab(){
        /**
         * lec1
         *      14:00 - 18:00, 1/1 - 2/2, MONDAY
         */

        TimeRange range1 = new TimeRange(1400, 1800);
        ArrayList<TimeSlot> slot1 = new ArrayList<TimeSlot>();
        slot1.add(new TimeSlot(DayOfWeek.MONDAY,range1, new Date(1, 1), new Date(2, 2)));


        Lecture lec1 = new Lecture(123, "A01", "Prof1", slot1);

        assertEquals(123, lec1.getCrn());
        assertEquals("A01", lec1.getSection());
        assertEquals("Prof1", lec1.getInstructor());
        assertEquals(slot1, lec1.getClasses());
    }

    @Test
    public void testLectureCreationWithLabs(){
        /**
         * lec1
         *      14:00 - 18:00, 1/1 - 2/2, MONDAY
         *
         *      lab1
         *          10:00 - 11:00, 1/1 - 2/2, WEDNESDAY
         *
         *      lab2
         *          15:00 - 16:00, 1/1 - 2/2, WEDNESDAY
         */

        TimeRange lecR1 = new TimeRange(1400, 1800);
        ArrayList<TimeSlot> slotLec1 = new ArrayList<TimeSlot>();
        slotLec1.add(new TimeSlot(DayOfWeek.MONDAY, lecR1, new Date(1, 1), new Date(2, 2)));

        int[] l1Time1 = {10, 00};
        int[] l1Time2 = {11, 00};

        TimeRange labR1 = new TimeRange(l1Time1, l1Time2);
        ArrayList<TimeSlot> slotLab1 = new ArrayList<TimeSlot>();
        slotLab1.add(new TimeSlot(DayOfWeek.WEDNESDAY, labR1, new Date(1, 1), new Date(2, 2)));

        Lab lab1 = new Lab(234, "B01", "TA", slotLab1);
        ArrayList<Lab> list1 = new ArrayList<Lab>();
        list1.add(lab1);

        Lecture lec1 = new Lecture(123, "A01", "Prof1", slotLec1, list1);

        assertEquals(list1, lec1.getLabs());

        int[] l2Time1 = {15, 00};
        int[] l2Time2 = {16, 00};

        TimeRange labR2 = new TimeRange(l2Time1, l2Time2);
        ArrayList<TimeSlot> slotLab2 = new ArrayList<TimeSlot>();
        slotLab2.add(new TimeSlot(DayOfWeek.WEDNESDAY,labR2, new Date(1, 1), new Date(2, 2)));

        Lab lab2 = new Lab(234, "B01", "TA", slotLab2);

        list1.add(lab2);

        lec1.addLabs(lab2);

        assertEquals(list1, lec1.getLabs());
    }
}
