package comp3350.courser.business;

import java.util.ArrayList;
import java.util.EnumMap;

import comp3350.courser.objects.Course;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.TimeSlot;
import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class CourseService {

    private IPersistenceAccess access;

    public  CourseService(){
        access = AccessService.getDataAccess(MainActivity.getDBPathName());
    }

    public ArrayList<Course> getCourseList() {
        return access.getAllCourses();
    }

    public String addCourse(Course course) {
        return access.addCourse(course);
    }

    public Course getCourseById(String id) {
        return access.getCourseById(id);
    }

    public String getCourseId(Course course) {
        return access.getCourseId(course);
    }

    public ArrayList<Course> getAllCourses(){

        String currCourseId;
        Course currCourse;
        ArrayList<Course> listDBCourses = access.getAllCourses();
        ArrayList<Course> listCourses = new ArrayList<>();
        ArrayList<Lecture> lectures;

        for (int i=0; i< listDBCourses.size(); i++){
            currCourseId = access.getCourseId(listDBCourses.get(i));
            lectures = getLectures(currCourseId);
            currCourse = new Course(listDBCourses.get(i).getCourseCode(),listDBCourses.get(i).getName(), listDBCourses.get(i).getDepartment(), listDBCourses.get(i).getTerm(), listDBCourses.get(i).getYear(), listDBCourses.get(i).getCreditHours(), lectures);
            listCourses.add(currCourse);
        }

        return listCourses;

    }

    public ArrayList<Lecture> getLectures(String courseId){

        String currLectureId;
        Lecture currLecture;
        ArrayList<Lab> listLabs;
        ArrayList<TimeSlot> timeSlots;
        ArrayList<Lecture> listLectures = new ArrayList<Lecture>();
        ArrayList<Lecture> listDBLectures = access.getLectures(courseId);

        for (int i=0; i< listDBLectures.size(); i++){
            currLectureId = access.getSectionId(listDBLectures.get(i), courseId);
            listLabs = getLabs(currLectureId, courseId);
            timeSlots = access.getTimeSlots(currLectureId);
            currLecture = new Lecture(listDBLectures.get(i).getCrn(), listDBLectures.get(i).getSection(), listDBLectures.get(i).getInstructor(), listDBLectures.get(i).getCampus(), timeSlots, listLabs);
            listLectures.add(currLecture);
        }

        return listLectures;
    }

    public ArrayList<Lab> getLabs(String lectureId, String courseId){

        Lab currLab;
        String currLabId;
        ArrayList<TimeSlot> timeSlots;
        ArrayList<Lab> listLabs = new ArrayList<Lab>();
        ArrayList<Lab> listDBLabs = access.getLabs(lectureId);

        for (int i=0; i< listDBLabs.size(); i++){
            currLabId = access.getSectionId(listDBLabs.get(i), courseId);
            timeSlots = access.getTimeSlots(currLabId);
            currLab = new Lab(listDBLabs.get(i).getCrn(), listDBLabs.get(i).getSection(), listDBLabs.get(i).getInstructor(), listDBLabs.get(i).getCampus(), timeSlots);
            listLabs.add(currLab);
        }

        return listLabs;
    }

    public boolean isUnique(Course course) {
        return access.getCourseId(course) == null;
    }

}
