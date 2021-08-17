package comp3350.courser.objects;

public class TimeSlot {
    private String timeSlotID;

    private DayOfWeek dayOfWeek;

    private int startTime;
    private int endTime;
    private int startDay;
    private int startMonth;
    private int endDay;
    private int endMonth;
    private String location;


    //No ID constructors

    public TimeSlot(DayOfWeek day, TimeRange classTime, Date startDate, Date endDate, String location) {

        this.timeSlotID = "0";

        this.dayOfWeek = day;
        this.startTime = classTime.getStartTime();
        this.endTime = classTime.getEndTime();
        this.startDay = startDate.getDay();
        this.startMonth = startDate.getMonth();
        this.endDay = endDate.getDay();
        this.endMonth = endDate.getMonth();
        this.location = location;
    }

    public TimeSlot(DayOfWeek day, TimeRange classTime, Date startDate, Date endDate) {

        this.timeSlotID = "0";

        this.dayOfWeek = day;
        this.startTime = classTime.getStartTime();
        this.endTime = classTime.getEndTime();
        this.startDay = startDate.getDay();
        this.startMonth = startDate.getMonth();
        this.endDay = endDate.getDay();
        this.endMonth = endDate.getMonth();
        this.location = "TBA";
    }

    //Constructors with ID

    public TimeSlot(String id, DayOfWeek day, TimeRange classTime, Date startDate, Date endDate, String location) {

        this.timeSlotID = id;

        this.dayOfWeek = day;
        this.startTime = classTime.getStartTime();
        this.endTime = classTime.getEndTime();
        this.startDay = startDate.getDay();
        this.startMonth = startDate.getMonth();
        this.endDay = endDate.getDay();
        this.endMonth = endDate.getMonth();
        this.location = location;

    }

    public TimeSlot(String id, DayOfWeek day, TimeRange classTime, Date startDate, Date endDate) {

        this.timeSlotID = id;

        this.dayOfWeek = day;
        this.startTime = classTime.getStartTime();
        this.endTime = classTime.getEndTime();
        this.startDay = startDate.getDay();
        this.startMonth = startDate.getMonth();
        this.endDay = endDate.getDay();
        this.endMonth = endDate.getMonth();
        this.location = "TBA";

    }

    public TimeRange getTimes() { return new TimeRange(startTime, endTime); }

    public String getLocation() {
        return location;
    }
    
    public Date getStartDate() {
        return new Date(startMonth, startDay);
    }

    public Date getEndDate() {
        return new Date(endMonth, endDay);
    }

    public DayOfWeek getDayOfWeek(){
        return dayOfWeek;
    }

    public boolean isEqual(TimeSlot compare){
        return this.dayOfWeek.equals(compare.dayOfWeek) &&
                this.startTime == compare.startTime     &&
                this.endTime == compare.endTime         &&
                this.startDay == compare.startDay       &&
                this.endDay == compare.endDay           &&
                this.startMonth == compare.startMonth   &&
                this.endMonth == compare.endMonth       &&
                this.location.equals(compare.location);
    }

    public String toString() {
        return "<Times: " + startTime/100 + ":" + startTime%100 + "-" + endTime/100 + ":" + endTime%100 +
                ", Dates: " + dayOfWeek + " " + startDay + "/" + startMonth +"-" + endDay + "/" + endMonth + ", Location: " + location + ">";
    }
}
