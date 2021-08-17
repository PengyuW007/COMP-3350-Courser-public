package comp3350.courser.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.EnumMap;

import comp3350.courser.objects.Date;
import comp3350.courser.objects.Course;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.Section;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;

public class PersistenceAccessDB implements IPersistenceAccess {
    private Connection connection;
    private ResultSet resultSet;
    private String dbType;
    private String cmdString;
    private String dbPath;

    public PersistenceAccessDB(String dbPath) {
        this.dbPath = dbPath;
    }

    /**
     * Initializes data access
     */
    public void open(String dbPath) {
        String url;
        try {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            connection = DriverManager.getConnection(url, "SA", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println("Opened " + dbType + "database ");
    }

    /**
     * Closes data access
     */
    public void close() {
        try {
            connection.createStatement().executeQuery("shutdown compact");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Closed " + dbType + "database ");
    }

    /**
     * Gets all courses from the DB
     */
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();

        try {
            cmdString = "Select * from COURSES";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("course_id"), resultSet.getString("course_code"), resultSet.getString("COURSE_NAME"), resultSet.getString("department"), resultSet.getString("term"), resultSet.getInt("year"), resultSet.getInt("CREDIT_HOURS"), null);
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    /**
     * Gets a courseId by it's course code, term, year from the DB
     */
    public String getCourseId(Course course) {
        String courseID = null;

        try {
            cmdString = "SELECT * from Courses " +
                    "WHERE course_code='" + course.getCourseCode() + "' AND term='" + course.getTerm() + "' AND year=" + course.getYear();
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                courseID = resultSet.getString("course_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseID;
    }

    /**
     * Gets a sectionId by it's CRN and courseId
     */
    public String getSectionId(Section section, String courseId) {
        String sectionID = null;

        try {
            cmdString = "SELECT * from sections " +
                    "WHERE course_id='" + courseId + "' AND CRN=" + section.getCrn();
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                sectionID = resultSet.getString("section_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sectionID;
    }

    /**
     * Gets a timeSlotId by it's course code, term, year from the DB
     */
    public String getTimeSlotID(TimeSlot timeSlot, String sectionID, DayOfWeek day) {
        String timeSlotId = null;

        try {
            cmdString = "SELECT * from timeslots " +
                    "WHERE day_of_week='" + day + "' AND start_time=" + timeSlot.getTimes().getStartTime() + " AND end_time=" + timeSlot.getTimes().getEndTime() + " AND start_day=" + timeSlot.getStartDate() + " AND start_month=" + timeSlot.getStartDate().getMonth() + " AND end_day=" + timeSlot.getEndDate().getDay() + " AND end_month=" + timeSlot.getEndDate().getMonth() + " AND location='" + timeSlot.getLocation() + "' AND section_id='" + sectionID + "'";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                timeSlotId = resultSet.getString("timeslot_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeSlotId;
    }

    /**
     * Gets a course by it's courseID from the DB
     */
    public Course getCourseById(String courseId) {
        Course course = null;
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();

        try {
            cmdString = "Select distinct * from Courses " +
                    "Where course_id='" + courseId + "'";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) //this while loop might not need to be here
            {
                course = new Course(resultSet.getString("course_id"), resultSet.getString("course_code"), resultSet.getString("COURSE_NAME"), resultSet.getString("department"), resultSet.getString("term"), resultSet.getInt("year"), resultSet.getInt("credit_hours"), lectures);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }

    /*
     * Get a time slot by it's courseID from the DB
     */
    public TimeSlot getTimeSlotById(String timeslotId) {
        TimeSlot timeSlot = null;
        TimeRange timeRange = new TimeRange();
        DayOfWeek dayOfWeek;
        Date startDate;
        Date endDate;

        try {
            cmdString = "Select distinct * from timeslots " +
                    "Where time_slot_id='" + timeslotId + "'";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                startDate = new Date(resultSet.getInt("start_month"), resultSet.getInt("start_day"));
                endDate = new Date(resultSet.getInt("end_month"), resultSet.getInt("end_day"));
                dayOfWeek = DayOfWeek.valueOf(resultSet.getString("day_of_week"));
                timeRange = new TimeRange(resultSet.getInt("start_time"), resultSet.getInt("end_time"));
                timeSlot = new TimeSlot(dayOfWeek, timeRange, startDate, endDate, resultSet.getString("location"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeSlot;
    }

    public ArrayList<Lecture> getLectures(String courseId) {
        ArrayList<Lecture> lectures = new ArrayList<>();

        try {
            cmdString = "Select * from Sections where lecture_id IS NULL AND course_id ='" + courseId + "'";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                Lecture lecture = new Lecture(resultSet.getInt("crn"), resultSet.getString("section"), resultSet.getString("instructor"), resultSet.getString("campus"), new ArrayList<TimeSlot>(), null);
                lectures.add(lecture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lectures;
    }

    public ArrayList<Lab> getLabs(String lectureId) {
        ArrayList<Lab> labs = new ArrayList<>();

        try {
            cmdString = "Select * from Sections where lecture_id = '" + lectureId + "'";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                Lab lab = new Lab(resultSet.getInt("crn"), resultSet.getString("section"), resultSet.getString("instructor"), resultSet.getString("campus"), new ArrayList<TimeSlot>());
                labs.add(lab);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labs;
    }


    public ArrayList<TimeSlot> getTimeSlots(String sectionId) {

        ArrayList<TimeSlot> timeSlots = new ArrayList<>();
        TimeSlot timeSlot = null;
        TimeRange timeRange = new TimeRange();
        DayOfWeek dayOfWeek;
        Date startDate;
        Date endDate;

        try {
            cmdString = "Select * from TIMESLOTS where SECTION_ID = '" + sectionId + "'";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {

                startDate = new Date(resultSet.getInt("start_month"), resultSet.getInt("start_day"));
                endDate = new Date(resultSet.getInt("end_month"), resultSet.getInt("end_day"));
                dayOfWeek = DayOfWeek.valueOf(resultSet.getString("day_of_week"));
                timeRange = new TimeRange(resultSet.getInt("start_time"), resultSet.getInt("end_time"));
                timeSlot = new TimeSlot(dayOfWeek, timeRange, startDate, endDate, resultSet.getString("location"));
                timeSlots.add(timeSlot);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeSlots;
    }

    /**
     * Add course to the course table
     */
    public String addCourse(Course course) {
        String courseId = course.getCourseCode() + course.getTerm() + course.getYear();
        String values;
        try {
            values = "'" + courseId + "', '" + course.getCourseCode() + "', '" + course.getName() + "', '" + course.getDepartment() + "', '" + course.getTerm() + "', " + course.getYear() + ", " + course.getCreditHours();
            cmdString = "INSERT into courses " +
                    "VALUES (" + values + ")";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getCourseId(course);
    }

    /**
     * Add lab to sections table
     */
    public String addLab(String courseId, String lectureId, Lab lab) {
        String labId = lab.getCrn() + courseId;
        try {
            cmdString = "INSERT into sections " +
                    "VALUES ('" + labId + "', " + lab.getCrn() + ", '" + lab.getSection() + "', '" + lab.getInstructor() + "', '" + lab.getCampus() + "', '" + courseId + "', '" + lectureId + "')";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getSectionId(lab, courseId);
    }

    /**
     * Add lecture to sections table
     */
    public String addLecture(String courseId, Lecture lecture) {
        String lectureId = lecture.getCrn() + courseId;
        try {
            //if add duplicate lectures
                cmdString = "INSERT into sections " +
                        "VALUES ('" + lectureId + "', " + lecture.getCrn() + ", '" + lecture.getSection() + "', '" + lecture.getInstructor() + "', '" + lecture.getCampus() + "', '" + courseId + "', null)";
                resultSet = connection.createStatement().executeQuery(cmdString);

        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return getSectionId(lecture, courseId);
    }

    /*
        /*
         * Add timeslot to timeslot table
         */
    public String addTimeSlot(String sectionID, TimeSlot timeSlot) {
        String timeId = timeSlot.getDayOfWeek().toString() + sectionID;
        try {
            cmdString = "INSERT into timeslots " +
                    "VALUES ('" + timeId + "', '" + timeSlot.getDayOfWeek().toString() + "', " + timeSlot.getTimes().getStartTime() + ", " + timeSlot.getTimes().getEndTime() + ", " + timeSlot.getStartDate().getDay() + ", " + timeSlot.getStartDate().getMonth() + ", " + timeSlot.getEndDate().getDay() + ", " + timeSlot.getEndDate().getMonth() + ", '" + timeSlot.getLocation() + "', '" + sectionID + "')";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return timeId;
    }

    public Section getSectionById(String sectionId) {
        Section section = null;
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();

        try {
            cmdString = "Select distinct * from Sections " +
                    "Where section_id='" + sectionId + "'";
            resultSet = connection.createStatement().executeQuery(cmdString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getString("lecture_id") == null) {
                    section = new Lecture(resultSet.getInt("crn"), resultSet.getString("section"), resultSet.getString("instructor"), resultSet.getString("campus"), null);
                } else {
                    section = new Lab(resultSet.getInt("crn"), resultSet.getString("section"), resultSet.getString("instructor"), resultSet.getString("campus"), null);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return section;
    }

    /**
     * Delete a row from appropriate table given an ID
     */
    public boolean deleteCourse(String courseID) {
        try {
            cmdString = "Delete from courses where course_id='" + courseID + "'";
            PreparedStatement prepState = connection.prepareStatement(cmdString);
            prepState.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSection(String sectionID) {
        try {
            cmdString = "Delete from sections where section_id='" + sectionID + "'";
            PreparedStatement prepState = connection.prepareStatement(cmdString);
            prepState.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteTimeSlot(String timeSlotID) {
        try {
            cmdString = "Delete from timeslots where time_slot_id='" + timeSlotID + "'";
            PreparedStatement prepState = connection.prepareStatement(cmdString);
            prepState.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
