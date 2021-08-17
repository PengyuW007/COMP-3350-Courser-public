package comp3350.courser.objects;

public class TimeRange {
    private int startTimeHour; // 24h
    private int startTimeMin; // 60min
    private int endTimeHour;
    private int endTimeMin;

    public TimeRange() {
        setStartTime(0);
        setEndTime(0);
    }

    public TimeRange(int[] startTime, int[] endTime) {
        setStartTimeArray(startTime);
        setEndTimeArray(endTime);
    }

    public TimeRange(int startTime, int endTime) {
        setStartTime(startTime);
        setEndTime(endTime);
    }

    public int[] getStartTimeArray() {
        return new int[] {startTimeHour, startTimeMin};
    }

    public int[] getEndTimeArray() {
        return new int[] {endTimeHour,endTimeMin};
    }

    public int getStartTime() {
        return (startTimeHour*100 + startTimeMin);
    }

    public int getEndTime() {
        return (endTimeHour*100 + endTimeMin);
    }

    public void setStartTimeArray(int[] timeArray) {
        this.startTimeHour = timeArray[0];
        this.startTimeMin = timeArray[1];
    }

    public void setEndTimeArray(int[] timeArray) {
        this.endTimeHour = timeArray[0];
        this.endTimeMin = timeArray[1];
    }

    public void setStartTime(int time) {
        this.startTimeHour = (int)(time/100);
        this.startTimeMin = (int)(time%100);
    }

    public void setEndTime(int time) {
        this.endTimeHour = (int)(time/100);
        this.endTimeMin = (int)(time%100);
    }

    public String toString() {
        return this.startTimeHour+":"+String.format("%02d", this.startTimeMin)+"-"+this.endTimeHour+":"+String.format("%02d",this.endTimeMin);
    }

    public boolean isEqual(TimeRange compare) {
        return (this.getStartTime() == compare.getStartTime()
                && this.getEndTime() == compare.getEndTime());
    }
}
