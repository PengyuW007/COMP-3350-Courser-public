package comp3350.courser.business;

import java.util.ArrayList;
import comp3350.courser.objects.Course;
import comp3350.courser.objects.Schedule;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.Section;
import comp3350.courser.persistence.IPersistenceAccess;
import comp3350.courser.presentation.MainActivity;

public class ScheduleService {

    private IPersistenceAccess access;

    public  ScheduleService(){
        access = AccessService.getDataAccess(MainActivity.getDBPathName());
    }

    public ArrayList<Schedule> generateAllSchedules(ArrayList<Course> courses) {
        ArrayList<Schedule> lectureSchedules = generateLectureSchedules(courses);

        return addLabsToLectureSchedules(lectureSchedules, courses);
    }

    private ArrayList<Schedule> generateLectureSchedules(ArrayList<Course> courses) {
        ArrayList<Schedule> lectureSchedules = new ArrayList<Schedule>();

        int numCourses = courses.size();
        int[] courseIndices = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            courseIndices[i] = 0;
        }

        boolean allSchedulesMade = false;

        while(!allSchedulesMade){
            Schedule newSchedule = new Schedule(courses);

            for(int i = 0; i < numCourses; i++){
                // Add the current combination to the schedules arraylist
                ArrayList<Lecture> currCourseLectures = courses.get(i).getLectures();
                newSchedule.addSection(currCourseLectures.get(courseIndices[i]));
            }

            lectureSchedules.add(newSchedule);

            int nextIndex = numCourses-1;
            while (nextIndex >= 0 && (courseIndices[nextIndex] + 1 >= courses.get(nextIndex).getLectures().size())) {
                nextIndex--;
            }

            if (nextIndex < 0) {
                allSchedulesMade = true;
            } else {
                courseIndices[nextIndex]++;

                for (int i = nextIndex+1; i < numCourses; i++) {
                    courseIndices[i] = 0;
                }
            }
        }

        return lectureSchedules;
    }

    private ArrayList<Schedule> addLabsToLectureSchedules(ArrayList<Schedule> lectureSchedules, ArrayList<Course> courses) {
        ArrayList<Schedule> lectureAndLabSchedules = new ArrayList<Schedule>();

        for (int currLectureSchedule = 0; currLectureSchedule < lectureSchedules.size(); currLectureSchedule++) {
            int numLectures = lectureSchedules.get(currLectureSchedule).getSections().size();
            int[] courseIndices = new int[numLectures];
            boolean allSchedulesMade = false;

            for(int currLecture = 0; currLecture < numLectures; currLecture++){
                courseIndices[currLecture] = 0;
            }

            while(!allSchedulesMade){
                Schedule newSchedule = new Schedule(courses);

                for(int currLecture = 0; currLecture < numLectures; currLecture++){
                    // Add the current combination to the schedules arraylist
                    Lecture targetLecture = null;
                    Section targetSection = lectureSchedules.get(currLectureSchedule).getSections().get(currLecture);

                    if (targetSection instanceof Lecture) {
                        targetLecture = (Lecture) targetSection;
                    }

                    newSchedule.addSectionUnique(targetLecture); // adds the target lecture, but only once, to the current schedule.

                    if (targetLecture != null && targetLecture.getLabs().size() > 0){
                        newSchedule.addSectionUnique(targetLecture.getLabs().get(courseIndices[currLecture]));
                    }
                }

                lectureAndLabSchedules.add(newSchedule);

                int nextIndex = numLectures-1;
                Schedule targetSchedule = lectureAndLabSchedules.get(currLectureSchedule);
                Section targetSection = targetSchedule.getSections().get(nextIndex);
                Lecture targetLecture = null;
                int numLabs = 0;

                if (targetSection instanceof Lecture) {
                    targetLecture = (Lecture) targetSection;
                }

                if (targetLecture != null) {
                    numLabs = targetLecture.getLabs().size();
                }

                while(nextIndex >= 0 && (courseIndices[nextIndex] + 1 >= numLabs)) {
                    nextIndex--;
                }

                if(nextIndex < 0){
                    allSchedulesMade = true;
                } else {
                    courseIndices[nextIndex]++;

                    for(int j = nextIndex+1; j < numLectures; j++){
                        courseIndices[j] = 0;
                    }
                }
            }
        }
        return lectureAndLabSchedules;
    }
}
