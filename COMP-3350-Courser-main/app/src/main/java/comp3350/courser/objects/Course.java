package comp3350.courser.objects;

import java.util.ArrayList;

public class Course {

    private String courseCode; // eg. COMP3350
    private String name; // eg. Software Engineering 2
    private String department; // eg. Computer Science
    private String term; // eg. Summer
    private int creditHours; // eg. 3
    private ArrayList<Lecture> lectures;
    private String courseID; // eg. 1
    private int year;  // eg. 2021

    public Course(){};

    public Course(String courseCode, String name, String department, String term, int year, int creditHours, ArrayList<Lecture> lectures) {
        this.courseCode = courseCode;
        this.name = name;
        this.department = department;
        this.term = term;
        this.year = year;
        this.creditHours = creditHours;

        if (lectures == null) {
            this.lectures = new ArrayList<Lecture>();
        } else {
            this.lectures = lectures;
        }
    }

    public Course(String courseID, String courseCode, String name, String department, String term, int year, int creditHours, ArrayList<Lecture> lectures) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.name = name;
        this.department = department;
        this.term = term;
        this.year = year;
        this.creditHours = creditHours;

        if (lectures == null) {
            this.lectures = new ArrayList<Lecture>();
        } else {
            this.lectures = lectures;
        }
    }

    public String getCourseID() {
        return this.courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTerm() {
        return term;
    }

    public int getYear(){
        return this.year;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public ArrayList<Lecture> getLectures() {
        ArrayList<Lecture> copy = new ArrayList<Lecture>();

        for (int i = 0; i < this.lectures.size(); i++) {
            copy.add(lectures.get(i));
        }

        return copy;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void addLecture(Lecture newLecture) {
        lectures.add(newLecture);
    }

    public boolean equals(Course compare) {
        return(this.courseCode.equalsIgnoreCase(compare.getCourseCode())
                && this.term.equalsIgnoreCase(compare.getTerm())
                && this.year==compare.getYear());
    }

}
