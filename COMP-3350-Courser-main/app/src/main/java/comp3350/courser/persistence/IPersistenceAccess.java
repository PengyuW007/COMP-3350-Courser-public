package comp3350.courser.persistence;

import java.util.ArrayList;

import comp3350.courser.objects.Course;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.Section;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.objects.DayOfWeek;

public interface IPersistenceAccess {

    /**
     * Initialize the data access
     */
    void open(String dbPath);

    /**
     * Close the data access
     */
    void close();

    /**
     * Add a course to the DB
     */
    String addCourse(Course course);

    /**
     * Add a lab to the DB
     */
    String addLab(String courseId, String lectureId, Lab lab);

    /**
     * Add a lecture to the DB
     */
    String addLecture(String courseId, Lecture lecture);

    /**
     * Add a new TimeSlot to the database.
     */
    String addTimeSlot(String sectionID, TimeSlot newSlot);

    /**
     * Get a list of all courses from DB
     */
    ArrayList<Course> getAllCourses();

    /**
     * Get course from DB by ID
     */
    Course getCourseById(String courseId);

    /**
     * Get the ID from course on the DB
     */
    String getCourseId(Course course);

    String getSectionId(Section section, String courseId);

    /**
     * Get a Section from DB by ID
     */
    Section getSectionById(String sectionId);

    /**
     * Get a TimeSlot from DB by ID
     */
    TimeSlot getTimeSlotById(String timeSlotId);

     /**
     * Get a list of timeSlots of a section from DB
     */
     ArrayList<TimeSlot> getTimeSlots(String sectionId);

    /**
     * Get a list of lectures of a course from DB
     */
    ArrayList<Lecture> getLectures(String courseId);

    /**
     * Get a list of labs of a section from DB
     */
    ArrayList<Lab> getLabs(String lectureId);

    /**
     * Delete a row from DB by ID
     */

    boolean deleteCourse(String courseID);

    boolean deleteSection(String sectionID);

    boolean deleteTimeSlot(String timeSlotID);


}
