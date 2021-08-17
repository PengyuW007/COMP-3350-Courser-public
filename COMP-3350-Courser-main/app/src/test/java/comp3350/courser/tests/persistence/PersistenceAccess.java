package comp3350.courser.tests.persistence;

import java.util.ArrayList;

import comp3350.courser.objects.Course;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.Section;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.tests.persistence.SectionDB;
import comp3350.courser.tests.persistence.TimeSlotDB;

public class PersistenceAccess implements IPersistenceAccess {

    final private String dbName;
    final private String dbType = "stub";

    private int courseID = 0;
    private int sectionId = 0;
    private int timeSlotID = 0;

    private ArrayList<CourseDB> coursesTable;
    private ArrayList<SectionDB> sectionsTable;
    private ArrayList<TimeSlotDB> timeSlotsTable;

    public PersistenceAccess(String dbName) {
        this.dbName = dbName;
        coursesTable = new ArrayList<CourseDB>();
        sectionsTable = new ArrayList<SectionDB>();
        timeSlotsTable = new ArrayList<TimeSlotDB>();
    }

    /**************Interface Public Methods****************/

    /**
     * Populate the stub database with courses, lectures, labs, and timeslots
     * Lectures have a lectureFK value > 0
     * Labs have a lectureFK value of -1
     */
    public void open(String dbPath) {
        String currCourseID;

        //addCourse(String code, String department, String name, String term, int year, int creditHours)
        addCourseDB("COMP1012", "Computer Science", "Computer Programming for Scientists and Engineers", "Fall", 2020, 3);
        addCourseDB("COMP3350", "Computer Science", "Software Engineering", "Summer", 2021, 3);
        addCourseDB("COMP3380", "Computer Science", "Databases Concepts and Usage", "Fall", 2020, 3);
        //addSection(int crn, String section, String instructor, String campus, int courseFK, int lectureFK)
        currCourseID = getCourseDBId("COMP3350", "Summer", 2021);
        addSectionDB(1103, "A01", "John P. Braico (P)", "RL", currCourseID,null);

        currCourseID = getCourseDBId("COMP3380", "Fall", 2020);
        addSectionDB(10197, "A01", "Robert W. Guderian (P)", "RL", currCourseID,null);
        addSectionDB(15304, "A02", "Adam Pazdor (P)", "RL", currCourseID,null);

        currCourseID = getCourseDBId("COMP1012", "Fall", 2020);
        addSectionDB(11759, "A01", "Robert W. Guderian (P)", "RL", currCourseID,null);
        addSectionDB(11760, "A02", "Heather Matheson (P)", "RL", currCourseID,null);
        addSectionDB(11761, "B01", "Robert W. Guderian (P)", "RL", currCourseID,"11759COMP1012Fall2020");
        addSectionDB(11762, "B02", "Robert W. Guderian (P)", "RL", currCourseID,"11759COMP1012Fall2020");
        addSectionDB(11763, "B03", "Heather Matheson (P)", "RL", currCourseID,"11760COMP1012Fall2020");
        addSectionDB(11764, "B04", "Heather Matheson (P)", "RL", currCourseID,"11760COMP1012Fall2020");

        //addTime(int sectionFK, DayOfWeek weekday, int startTime, int endTime, Date startDate, Date endDate)
        addTimeDB("1103COMP3350Summer2021", DayOfWeek.TUESDAY, 1045, 1200, new Date(5, 10), new Date(8, 18));
        addTimeDB("1103COMP3350Summer2021", DayOfWeek.THURSDAY, 1045, 1200, new Date(5, 10), new Date(8, 18));

        addTimeDB("10197COMP3380Fall2020", DayOfWeek.TUESDAY, 1600, 1715, new Date(9, 9), new Date(12, 11));
        addTimeDB("10197COMP3380Fall2020", DayOfWeek.THURSDAY, 1600, 1715, new Date(9, 9), new Date(12, 11));
        addTimeDB("15304COMP3380Fall2020", DayOfWeek.TUESDAY, 830, 945, new Date(9, 9), new Date(12, 11));
        addTimeDB("15304COMP3380Fall2020", DayOfWeek.THURSDAY, 830, 945, new Date(9, 9), new Date(12, 11));

        addTimeDB("11759COMP1012Fall2020", DayOfWeek.TUESDAY, 830, 945, new Date(9, 9), new Date(12, 11));
        addTimeDB("11759COMP1012Fall2020", DayOfWeek.THURSDAY, 830, 945, new Date(9, 9), new Date(12, 11));
        addTimeDB("11760COMP1012Fall2020", DayOfWeek.MONDAY, 1230, 1320, new Date(9, 9), new Date(12, 11));
        addTimeDB("11760COMP1012Fall2020", DayOfWeek.WEDNESDAY, 1230, 1320, new Date(9, 9), new Date(12, 11));
        addTimeDB("11760COMP1012Fall2020", DayOfWeek.FRIDAY, 1230, 1320, new Date(9, 9), new Date(12, 11));

        addTimeDB("11761COMP1012Fall2020", DayOfWeek.MONDAY, 930, 1020, new Date(9, 9), new Date(12, 11));
        addTimeDB("11762COMP1012Fall2020", DayOfWeek.TUESDAY, 1000, 1050, new Date(9, 9), new Date(12, 11));
        addTimeDB("11763COMP1012Fall2020", DayOfWeek.WEDNESDAY, 830, 920, new Date(9, 9), new Date(12, 11));
        addTimeDB("11764COMP1012Fall2020", DayOfWeek.WEDNESDAY, 930, 1020, new Date(9, 9), new Date(12, 11));

        System.out.println("Opened " + dbType + " database " + dbName);

    }

    /**
     * Close the stub DB.
     * This clears all the tables and prints a confirmation message
     */
    public void close() {
        coursesTable = new ArrayList<>();
        sectionsTable = new ArrayList<>();
        timeSlotsTable = new ArrayList<>();
        //courses = new ArrayList<>();
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    /**
     * Returns an arraylist of all courses that are in the DB.
     * Each course has a list of it's lectures
     * Each lecture has a list of labs
     */
    public ArrayList getAllCourses() {
        Course currCourse;

        ArrayList<Course> returnList = new ArrayList<>();
        for (int i = 0; i < coursesTable.size(); i++) {

            currCourse = new Course(coursesTable.get(i).code, coursesTable.get(i).name, coursesTable.get(i).department, coursesTable.get(i).term, coursesTable.get(i).year, coursesTable.get(i).creditHours, null);
            returnList.add(currCourse);

        }

        return returnList;
    }

    /**
     * Adds a course to the DB
     */
    public String addCourse(Course course) {
        String courseID = course.getCourseCode() + course.getTerm() + course.getYear();

        CourseDB newCourse = new CourseDB(courseID, course.getCourseCode(),
                course.getDepartment(),
                course.getName(),
                course.getTerm(),
                course.getYear(),
                course.getCreditHours());
        this.coursesTable.add(newCourse);

        return courseID;
    }

    /**
     * Get the courseID of a course by courseCode, Term, Year
     * Public method calls private
     */
    public String getCourseId(Course course) {
        String outCourseID = null;

        for (int i = 0; i < coursesTable.size(); i++) {
            if (coursesTable.get(i).code.equalsIgnoreCase(course.getCourseCode())
                    && coursesTable.get(i).term.equalsIgnoreCase(course.getTerm())
                    && coursesTable.get(i).year == course.getYear()) {
                outCourseID = coursesTable.get(i).courseID;
            }
        }

        return outCourseID;
    }

    public String getSectionId(Section section, String courseId) {
        String outSectionID = null;

        for (int i = 0; i < sectionsTable.size(); i++) {
            if (sectionsTable.get(i).crn == section.getCrn()
                    && sectionsTable.get(i).courseFK.equalsIgnoreCase(courseId)) {
                outSectionID = sectionsTable.get(i).sectionId;
            }
        }

        return outSectionID;
    }

    /**
     * Get the courseID of a course by courseCode, Term, Year
     */
    public Course getCourseById(String inCourseID) {
        Course returnCourse = null;

        for (int i = 0; i < coursesTable.size() && returnCourse == null; i++) {
            if (coursesTable.get(i).courseID.equalsIgnoreCase(inCourseID)) {
                returnCourse = new Course(coursesTable.get(i).code, coursesTable.get(i).name, coursesTable.get(i).department, coursesTable.get(i).term, coursesTable.get(i).year, coursesTable.get(i).creditHours, null);
            }
        }

        return returnCourse;
    }

    /**
     * Add a lab to DB by calling the addSectionDB method. Since a lab is a type of section.
     */
    public String addLab(String courseId, String lectureId, Lab lab) {//(int crn, String section, String instructor, String campus, int courseFK, int lectureFK) {
        return addSectionDB(lab.getCrn(),
                lab.getSection(),
                lab.getInstructor(),
                lab.getCampus(),
                courseId,
                lectureId);
    }

    /**
     * Add a lecture to DB by calling the addSectionDB method. Since a lecture is a type of section.
     */
    public String addLecture(String courseId, Lecture lecture) {
        return addSectionDB(lecture.getCrn(),
                lecture.getSection(),
                lecture.getInstructor(),
                lecture.getCampus(),
                courseId,
                null);
    }


    public String addTimeSlot(String sectionID, TimeSlot newSlot) {
        return addTimeDB(sectionID, newSlot.getDayOfWeek(), newSlot.getTimes().getStartTime(), newSlot.getTimes().getEndTime(), newSlot.getStartDate(), newSlot.getEndDate());
    }

    public TimeSlot getTimeSlotById(String TimeId) {

        TimeSlot currTime = null;

        for (int i = 0; i < timeSlotsTable.size(); i++) {

            if (timeSlotsTable.get(i).timeID.equals(TimeId)) {
                currTime = new TimeSlot(timeSlotsTable.get(i).weekday, timeSlotsTable.get(i).timeRange, timeSlotsTable.get(i).startDate, timeSlotsTable.get(i).endDate);
            }
        }

        return currTime;
    }

    public ArrayList<TimeSlot> getTimeSlots(String sectionId) {

        TimeSlot currTime = null;
        ArrayList<TimeSlot> returnList = new ArrayList<>();

        for (int i = 0; i < timeSlotsTable.size(); i++) {
            if (timeSlotsTable.get(i).sectionFK.equalsIgnoreCase(sectionId)) {
                currTime = new TimeSlot(timeSlotsTable.get(i).timeID, timeSlotsTable.get(i).weekday, timeSlotsTable.get(i).timeRange, timeSlotsTable.get(i).startDate, timeSlotsTable.get(i).endDate);
                returnList.add(currTime);
            }
        }

        return returnList;

    }

    public ArrayList<Lecture> getLectures(String courseId) {
        Lecture currLecture;
        ArrayList<Lecture> returnList = new ArrayList<>();

        for (int i = 0; i < sectionsTable.size(); i++) {
            if (sectionsTable.get(i).courseFK.equalsIgnoreCase(courseId) && sectionsTable.get(i).lectureFK == null) {
                currLecture = new Lecture(sectionsTable.get(i).crn, sectionsTable.get(i).section, sectionsTable.get(i).instructor, sectionsTable.get(i).campus, new ArrayList<TimeSlot>(), null);
                returnList.add(currLecture);
            }
        }

        return returnList;
    }

    public ArrayList<Lab> getLabs(String lectureId) {
        Lab currLab;
        ArrayList<Lab> returnList = new ArrayList<>();

        for (int i = 0; i < sectionsTable.size(); i++) {
            if (sectionsTable.get(i).lectureFK != null && sectionsTable.get(i).lectureFK.equalsIgnoreCase(lectureId)) {
                currLab = new Lab(sectionsTable.get(i).crn, sectionsTable.get(i).section, sectionsTable.get(i).instructor, sectionsTable.get(i).campus, new ArrayList<TimeSlot>());
                returnList.add(currLab);
            }
        }

        return returnList;
    }

    public Section getSectionById(String sectionId) {
        Section currSection = null;

        for (int i = 0; i < sectionsTable.size(); i++) {

            if (sectionsTable.get(i).sectionId.equalsIgnoreCase(sectionId)) {
                if (sectionsTable.get(i).lectureFK == null)
                    currSection = new Lecture(sectionsTable.get(i).crn, sectionsTable.get(i).section, sectionsTable.get(i).instructor, sectionsTable.get(i).campus, new ArrayList<TimeSlot>(), null);
                else
                    currSection = new Lab(sectionsTable.get(i).crn, sectionsTable.get(i).section, sectionsTable.get(i).instructor, sectionsTable.get(i).campus, new ArrayList<TimeSlot>());
            }
        }

        return currSection;
    }

    /*************Private Methods*****************/

    /**
     * Get the courseID of a course by courseCode, Term, Year
     */
    private String getCourseDBId(String courseCode, String term, int year) {
        String outCourseID = null;

        for (int i = 0; i < coursesTable.size(); i++) {
            if (coursesTable.get(i).code.equalsIgnoreCase(courseCode)
                    && coursesTable.get(i).term.equalsIgnoreCase(term)
                    && coursesTable.get(i).year == year) {
                outCourseID = coursesTable.get(i).courseID;
            }
        }
        return outCourseID;
    }


    /**
     * Add course to the DB
     */
    private String addCourseDB(String code, String department, String name, String term, int year, int creditHours) {


        String newCourseID = code + term + year;

        //String newCourseID = code+term+year;

        CourseDB newCourse = new CourseDB(newCourseID, code, department, name, term, year, creditHours);
        this.coursesTable.add(newCourse);

        return newCourseID;
    }

    /**
     * Add section to the DB
     */
    private String addSectionDB(int crn, String section, String instructor, String campus, String courseFK, String lectureFK) {
        String newSectionID = crn + courseFK;
        SectionDB newSection = new SectionDB(newSectionID, crn, section, instructor, campus, courseFK, lectureFK);
        this.sectionsTable.add(newSection);

        return newSectionID;
    }

    /**
     * Add time slot to the DB
     */
    private String addTimeDB(String sectionFK, DayOfWeek weekday, int startTime, int endTime, Date startDate, Date endDate) {
        TimeRange timeRange = new TimeRange(startTime, endTime);
        String timeSlotID = weekday.toString() + sectionFK;
        TimeSlotDB newTime = new TimeSlotDB(timeSlotID, sectionFK, weekday, timeRange, startDate, endDate);
        this.timeSlotsTable.add(newTime);

        return timeSlotID;
    }

    /**
     * Delete object from stub by ID
     */

    public boolean deleteCourse(String courseID) {
        boolean deleted = false;

        for (int i = 0; i<coursesTable.size(); i++) {
            if (coursesTable.get(i).courseID.equals(courseID)) {
                coursesTable.remove(i);
                deleted = true;
                break;
            }
        }

        return deleted;
    }

    public boolean deleteSection(String sectionID) {
        boolean deleted = false;

        for (int i = 0; i<sectionsTable.size(); i++) {
            if (sectionsTable.get(i).sectionId.equals(sectionID)) {
                sectionsTable.remove(i);
                deleted = true;
                break;
            }
        }

        return deleted;
    }

    public boolean deleteTimeSlot(String timeSlotID) {
        boolean deleted = false;

        for (int i = 0; i<timeSlotsTable.size(); i++) {
            if (timeSlotsTable.get(i).timeID.equals(timeSlotID)) {
                timeSlotsTable.remove(i);
                deleted = true;
                break;
            }
        }

        return deleted;
    }
}
