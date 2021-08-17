package comp3350.courser.objects;

import java.util.ArrayList;
import java.util.EnumMap;

public class Lecture extends Section {
    private ArrayList<Lab> labs;

    public Lecture(int crn, String section, String instructor, String campus, ArrayList<TimeSlot> timeSlots) {
        super(crn, section, instructor, campus, timeSlots);
        this.labs = new ArrayList<Lab>();
    }

    public Lecture(int crn, String section, String instructor, ArrayList<TimeSlot> timeSlots) {
        super(crn, section, instructor, timeSlots);
        this.labs = new ArrayList<Lab>();
    }

    public Lecture(int crn, String section, String instructor, String campus, ArrayList<TimeSlot> timeSlots, ArrayList<Lab> labs) {
        super(crn, section, instructor, campus, timeSlots);

        if (labs == null) {
            this.labs = new ArrayList<Lab>();
        } else {
            this.labs = labs;
        }
    }

    public Lecture(int crn, String section, String instructor, ArrayList<TimeSlot> timeSlots, ArrayList<Lab> labs) {
        super(crn, section, instructor, timeSlots);

        if (labs == null) {
            this.labs = new ArrayList<Lab>();
        } else {
            this.labs = labs;
        }
    }

    public void addLabs(Lab newLab) {
        this.labs.add(newLab);
    }

    public void setLabs(ArrayList<Lab> classes) {
        this.labs = classes;
    }

    public ArrayList<Lab> getLabs() {
        ArrayList<Lab> copy = new ArrayList<Lab>();

        for (int i = 0; i < this.labs.size(); i++){
            copy.add(labs.get(i));
        }

        return copy;
    }
}
