package comp3350.courser.tests.persistence;

import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.TimeRange;

class TimeSlotDB {
    protected String timeID;
    protected String sectionFK;
    protected DayOfWeek weekday;
    protected TimeRange timeRange;
    protected Date startDate;
    protected Date endDate;

    protected TimeSlotDB(String timeID, String sectionFK, DayOfWeek weekday, TimeRange timeRange, Date startDate, Date endDate) {
        this.timeID = timeID;
        this.sectionFK = sectionFK;
        this.weekday = weekday;
        this.timeRange = timeRange;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
