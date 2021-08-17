package comp3350.courser.tests.persistence;

class CourseDB {
    protected String courseID;
    protected String code; // eg. COMP3350
    protected String department; // eg. Computer Science
    protected String name; // eg. Software Engineering 2
    protected String term; // eg. Summer
    protected int year; // eg. 2021
    protected int creditHours; // eg. 3

    protected CourseDB(String courseID, String code, String department, String name ,String term, int year, int creditHours) {
        this.courseID = courseID;
        this.code = code;
        this.department = department;
        this.name = name;
        this.term = term;
        this.year = year;
        this.creditHours = creditHours;
    }

}
