package comp3350.courser.business;

import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.Date;
import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class TimeSlotService {

    private IPersistenceAccess access;

    public TimeSlotService(){
        access = AccessService.getDataAccess(MainActivity.getDBPathName());
    }

    public boolean isConflicting(TimeSlot ts1, TimeSlot ts2) {
        Date ts1StartDate = ts1.getStartDate();
        Date ts1EndDate = ts1.getEndDate();
        Date ts2StartDate = ts2.getStartDate();
        Date ts2EndDate = ts2.getEndDate();
        TimeRange ts1TimeRange = ts1.getTimes();
        TimeRange ts2TimeRange = ts2.getTimes();

        return (ts1StartDate.isBeforeOrEqual(ts2EndDate) && ts2StartDate.isBeforeOrEqual(ts1EndDate))
                && (ts1TimeRange.getStartTime() <= ts2TimeRange.getEndTime()
                    && ts2TimeRange.getStartTime() <= ts1TimeRange.getEndTime());
    }


    public String addTimeSlot(String sectionID, DayOfWeek weekday, TimeSlot timeSlot) {
        return access.addTimeSlot(sectionID, timeSlot);
    }
    public String addTimeSlot(String sectionID, TimeSlot timeSlot) {
        return access.addTimeSlot(sectionID, timeSlot);

    }
}