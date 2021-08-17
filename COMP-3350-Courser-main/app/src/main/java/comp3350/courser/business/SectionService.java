package comp3350.courser.business;

import java.sql.Time;
import java.util.ArrayList;
import java.util.EnumMap;


import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.Section;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class SectionService {

    private IPersistenceAccess access;

    public  SectionService(){
        access = AccessService.getDataAccess(MainActivity.getDBPathName());
    }

    public boolean isConflicting(Section s1, Section s2){

        TimeSlotService timeSlotService = new TimeSlotService();

        ArrayList<TimeSlot> s1TimeSlots = s1.getClasses();
        ArrayList<TimeSlot> s2TimeSlots = s2.getClasses();
        Boolean isConflicting = false;

        if (s1TimeSlots != null && s2TimeSlots != null){
            // Loop through the time slots for a current day.
            // If any of the time slots conflict with the other course's slots for that day, the sections are in conflict.
            for (int i = 0; i < s1TimeSlots.size(); i++) {
                for (int j = 0; j < s2TimeSlots.size(); j++) {
                    if (timeSlotService.isConflicting(s1TimeSlots.get(i), s2TimeSlots.get(j))) {
                        isConflicting = true;
                    }
                }
            }
        }

        return isConflicting;
    }

    public String addLecture(String courseId, Lecture lecture) {
        return access.addLecture(courseId, lecture);
    }

    public String addLab(String courseID, String lectureId, Lab lab) {
        return access.addLab(courseID, lectureId, lab);
    }

    public Section getSectionById(String sectionId){
        return access.getSectionById(sectionId);
    }

    public boolean isUnique(Section targetSection, String courseId) {
        return access.getSectionId(targetSection, courseId) == null;
    }
}
