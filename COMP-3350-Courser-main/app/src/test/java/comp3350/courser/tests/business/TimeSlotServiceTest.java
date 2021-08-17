package comp3350.courser.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.courser.business.AccessService;
import comp3350.courser.business.TimeSlotService;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.tests.persistence.PersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class TimeSlotServiceTest extends TestCase {

    private static String dbName = MainActivity.getDBPathName();


    @Test
    public void testIsConflictingWithIdenticalSlots(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        TimeSlotService timeSlotService = new TimeSlotService();

        TimeSlot  ts1 = new TimeSlot(DayOfWeek.MONDAY,new TimeRange(1000, 1400), new Date(1, 1), new Date(2, 2));

        assertTrue(timeSlotService.isConflicting(ts1, ts1));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithDifferentSlots(){

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        TimeSlotService timeSlotService = new TimeSlotService();

        TimeSlot  ts1 = new TimeSlot(DayOfWeek.MONDAY,new TimeRange(1000, 1400), new Date(1, 1), new Date(2, 2));
        TimeSlot  ts2 = new TimeSlot(DayOfWeek.MONDAY,new TimeRange(1600, 2000), new Date(1, 1), new Date(2, 2));

        assertFalse(timeSlotService.isConflicting(ts1, ts2));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithSameTime() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        TimeSlotService timeSlotService = new TimeSlotService();

        TimeSlot  ts1 = new TimeSlot(DayOfWeek.MONDAY,new TimeRange(1000, 1400), new Date(1, 1), new Date(2, 1));
        TimeSlot  ts2 = new TimeSlot(DayOfWeek.MONDAY,new TimeRange(1000, 1400), new Date(3, 1), new Date(4, 2));

        assertFalse(timeSlotService.isConflicting(ts1, ts2));

        AccessService.closeDataAccess();
    }

    @Test
    public void testIsConflictingWithSameDates() {

        AccessService.closeDataAccess();
        AccessService.createDataAccess(new PersistenceAccess(dbName));
        TimeSlotService timeSlotService = new TimeSlotService();

        TimeSlot  ts1 = new TimeSlot(DayOfWeek.MONDAY,new TimeRange(1200, 1400), new Date(1, 1), new Date(2, 1));
        TimeSlot  ts2 = new TimeSlot(DayOfWeek.MONDAY,new TimeRange(1600, 1800), new Date(1, 1), new Date(2, 1));

        assertFalse(timeSlotService.isConflicting(ts1, ts2));

        AccessService.closeDataAccess();
    }

}
