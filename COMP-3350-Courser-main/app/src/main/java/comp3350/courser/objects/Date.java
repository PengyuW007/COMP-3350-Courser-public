package comp3350.courser.objects;

public class Date {
    private int month;
    private int day;

    public Date(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isAfterOrEqual(Date otherDate) {
        return (this.month > otherDate.getMonth())
                || (this.month == otherDate.getMonth()
                && this.day >= otherDate.getDay());
    }

    public boolean isBeforeOrEqual(Date otherDate) {
        return (this.month < otherDate.getMonth())
                || (this.month == otherDate.getMonth()
                && this.day <= otherDate.getDay());
    }

    public String toString() {
        return this.month+"/"+this.day;
    }

    public boolean isEqual(Date otherDate){
        return (this.month == otherDate.getMonth()
                && this.day == otherDate.getDay());
    }
}
