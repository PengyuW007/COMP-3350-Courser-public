package comp3350.courser.objects;

import java.util.ArrayList;

public abstract class Section {
    private int crn;
    private String section;
    private String instructor;
    private String campus;
    private ArrayList<TimeSlot> timeSlots;

    Section(int crn, String section, String instructor, String campus, ArrayList<TimeSlot> timeSlots) {
        this.crn = crn;
        this.section = section;
        this.instructor = instructor;
        this.campus = campus;

        if (timeSlots == null) {
            this.timeSlots = new ArrayList<TimeSlot>();
        } else {
            this.timeSlots = timeSlots;
        }
    }

    Section(int crn, String section, String instructor, ArrayList<TimeSlot> timeSlots) {
        this.crn = crn;
        this.section = section;
        this.instructor = instructor;

        if (timeSlots == null) {
            this.timeSlots = new ArrayList<TimeSlot>();
        } else {
            this.timeSlots = timeSlots;
        }
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public ArrayList<TimeSlot> getClasses() {
        ArrayList<TimeSlot> copy = new ArrayList<TimeSlot>();

        for(int i = 0; i < timeSlots.size(); i++){//DayOfWeek key: this.timeSlots.keySet()){
            copy.add(this.timeSlots.get(i));
        }

        return copy;
    }

    public void setClass(ArrayList<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

/* This is a setter, I won;t fix it because it will be removed
    public void addClass(DayOfWeek weekDay, TimeSlot newClass) {
        if (this.timeSlots.isEmpty() || this.timeSlots.get(weekDay) == null) {
            TimeSlot[] newArray = {newClass};
            this.timeSlots.put(weekDay, newArray);
        }
        else {
            TimeSlot[] oldArray = this.timeSlots.get(weekDay);
            TimeSlot[] newArray = new TimeSlot[oldArray.length + 1];

            for (int i = 0; i < oldArray.length; i++) {
                newArray[i] = oldArray[i];
            }
            newArray[oldArray.length] = newClass;

            this.timeSlots.put(weekDay, newArray);
        }
    }*/

    //Please review this
    public String toString() {
        String result = "["+this.crn+"] "+this.section+", "+instructor+", "+campus+"\n";

        for(int i = 0; i < timeSlots.size(); i++) {

                result += "\t"+timeSlots.get(i).toString();

                result += "\n";
        }

        return result;
    }

    public boolean equals(Section otherSection) {
        return (this.crn == otherSection.crn);
    }

}
