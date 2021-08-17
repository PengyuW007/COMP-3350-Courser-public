package comp3350.courser.objects;

import java.util.ArrayList;
import java.util.EnumMap;

public class Lab extends Section {

    public Lab(int crn, String section, String instructor, String campus, ArrayList<TimeSlot> timeSlots) {
        super(crn, section, instructor, campus, timeSlots);
    }

    public Lab(int crn, String section, String instructor, ArrayList<TimeSlot> timeSlots) {
        super(crn, section, instructor, timeSlots);
    }
}
