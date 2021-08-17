package comp3350.courser.objects;

import java.util.ArrayList;

public class Schedule {
    ArrayList<Section> secList;
    ArrayList<Course> courseList;

    public Schedule() {
        courseList = new ArrayList<Course>();
        secList = new ArrayList<Section>();
    }

    public Schedule(ArrayList<Course> courses) {
        if (courses == null) {
            courseList = new ArrayList<Course>();
        } else {
            courseList = courses;
        }

        secList = new ArrayList<Section>();
    }

    public ArrayList<Course> getCourses() {
        return this.courseList;
    }

    public ArrayList<Section> getSections() {
        return this.secList;
    }

    public void addSection(Section newItem) {
        this.secList.add(newItem);
    }

    public void addSectionUnique(Section newItem) {
        if (!this.secList.contains(newItem)) {
            this.addSection(newItem);
        }
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < secList.size(); i++) {
            Section section = secList.get(i);
            if (section instanceof Lecture) {
                section = (Lecture)section;
            }
            result += section.toString();
        }

        return result;
    }
}
