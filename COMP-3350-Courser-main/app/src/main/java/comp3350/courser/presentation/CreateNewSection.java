package comp3350.courser.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import comp3350.courser.presentation.Validator;

import androidx.appcompat.app.AppCompatActivity;

import com.example.courser.R;

import java.util.ArrayList;

import comp3350.courser.business.CourseService;
import comp3350.courser.business.SectionService;
import comp3350.courser.business.TimeSlotService;
import comp3350.courser.objects.Course;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;

public class CreateNewSection extends AppCompatActivity {

    private Course courseCreated;
    private CourseService courseService;
    private String courseId;
    private SectionService sectionService;
    private TimeSlotService timeSlotService;

    private EditText sectionCRNField;
    private EditText sectionCodeField;
    private EditText sectionInstructorField;
    private EditText sectionCampusField;
    private EditText sectionLocationField;
    private EditText sectionStartDateField;
    private EditText sectionEndDateField;
    private EditText sectionMondayField;
    private EditText sectionTuesdayField;
    private EditText sectionWednesdayField;
    private EditText sectionThursdayField;
    private EditText sectionFridayField;
    private EditText sectionMondayEndField;
    private EditText sectionTuesdayEndField;
    private EditText sectionWednesdayEndField;
    private EditText sectionThursdayEndField;
    private EditText sectionFridayEndField;

    EditText[] startFields;
    EditText[] endFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_section);

        courseService = new CourseService();
        sectionService = new SectionService();
        timeSlotService = new TimeSlotService();

        courseId = getIntent().getStringExtra("CourseID");

        courseCreated = courseService.getCourseById(courseId);

        TextView courseTitle = (TextView) findViewById(R.id.courseTitleNewSection);
        courseTitle.setText(courseCreated.getName());

        sectionCRNField = (EditText)findViewById(R.id.sectionCRNField);
        sectionCodeField = (EditText)findViewById(R.id.sectionCodeField);
        sectionInstructorField = (EditText)findViewById(R.id.sectionInstructorField);
        sectionCampusField = (EditText)findViewById(R.id.sectionCampusField);
        sectionLocationField = (EditText)findViewById(R.id.sectionLocationFIeld);
        sectionStartDateField = (EditText)findViewById(R.id.sectionStartDateField);
        sectionEndDateField = (EditText)findViewById(R.id.sectionEndDateField);
        sectionMondayField = (EditText)findViewById(R.id.sectionMondayField);
        sectionTuesdayField = (EditText)findViewById(R.id.sectionTuesdayField);
        sectionWednesdayField = (EditText)findViewById(R.id.sectionWednesdayField);
        sectionThursdayField = (EditText)findViewById(R.id.sectionThursdayField);
        sectionFridayField = (EditText)findViewById(R.id.sectionFridayField);
        sectionMondayEndField = (EditText)findViewById(R.id.sectionMondayEndField);
        sectionTuesdayEndField = (EditText)findViewById(R.id.sectionTuesdayEndField);
        sectionWednesdayEndField = (EditText)findViewById(R.id.sectionWednesdayEndField);
        sectionThursdayEndField = (EditText)findViewById(R.id.sectionThusrdayEndField);
        sectionFridayEndField = (EditText)findViewById(R.id.sectionFridayEndField);

        startFields = new EditText[]{sectionMondayField, sectionTuesdayField, sectionWednesdayField, sectionThursdayField, sectionFridayField};
        endFields = new EditText[]{sectionMondayEndField, sectionTuesdayEndField, sectionWednesdayEndField, sectionThursdayEndField, sectionFridayEndField};

        Button AddNewSections = (Button)findViewById(R.id.addNewSectionButton);
        AddNewSections.setOnClickListener((v -> buttonAddSectionsOnClick()));

        Button AddLab = (Button)findViewById(R.id.addLabButton);
        AddLab.setOnClickListener((v -> buttonAddLabOnClick()));

        Button finishCreate = (Button)findViewById(R.id.finishButton);
        finishCreate.setOnClickListener((v -> buttonFinishOnClick()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_section_menu, menu);
        return true;
    }

    public void buttonAddSectionsOnClick() {
        if (Validator.validateSection(
                this,
                sectionCRNField,
                sectionCodeField,
                sectionInstructorField,
                sectionCampusField,
                sectionLocationField,
                sectionStartDateField,
                sectionEndDateField,
                startFields,
                endFields)) {
            String lectureId = addSections();

            if (lectureId != null) {
                Intent newSectionIntent = new Intent(CreateNewSection.this, CreateNewSection.class);
                newSectionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newSectionIntent.putExtra("CourseID", courseId);
                CreateNewSection.this.startActivity(newSectionIntent);
            }
        }
    }

    public void buttonAddLabOnClick() {
        if (Validator.validateSection(
                this,
                sectionCRNField,
                sectionCodeField,
                sectionInstructorField,
                sectionCampusField,
                sectionLocationField,
                sectionStartDateField,
                sectionEndDateField,
                startFields,
                endFields)) {
            String lectureId = addSections();
            ArrayList<String> LabFKs = new ArrayList<>();
            LabFKs.add(courseId);
            LabFKs.add(lectureId);

            if (lectureId != null) {
                Intent newLabIntent = new Intent(CreateNewSection.this, CreateNewLab.class);
                newLabIntent.putExtra("LabFKs", LabFKs);
                CreateNewSection.this.startActivity(newLabIntent);
            }
        }
    }

    public void buttonFinishOnClick() {
        if (Validator.validateSection(
                this,
                sectionCRNField,
                sectionCodeField,
                sectionInstructorField,
                sectionCampusField,
                sectionLocationField,
                sectionStartDateField,
                sectionEndDateField,
                startFields,
                endFields)) {
            String lectureId = addSections();

            if (lectureId != null) {
                Intent newFinishIntent = new Intent(CreateNewSection.this, WorkspaceActivity.class);
                CreateNewSection.this.startActivity(newFinishIntent);
            }
        }
    }

    public String addSections() {
        Lecture newLecture;
        String newLectureId = null;
        Date newStartDate;
        Date newEndDate;

        int sectionCRNNew = Integer.parseInt(sectionCRNField.getText().toString());
        String sectionCodeNew = sectionCodeField.getText().toString();
        String sectionInstructorNew = sectionInstructorField.getText().toString();
        String sectionCampusNew = sectionCampusField.getText().toString();
        String sectionLocationNew = sectionLocationField.getText().toString();
        String sectionStartDateNew = sectionStartDateField.getText().toString();
        String sectionEndDateNew = sectionEndDateField.getText().toString();

        newLecture = new Lecture(sectionCRNNew, sectionCodeNew, sectionInstructorNew, sectionCampusNew, null);

        if (sectionService.isUnique(newLecture, courseId)) {
            newLectureId = sectionService.addLecture(courseId, newLecture);
        } else {
            Alerts.warning(this, "This course already has a section with the CRN "+sectionCRNField.getText().toString()+"!");
        }

        if (newLectureId != null) {
            newStartDate = new Date(Integer.parseInt(sectionStartDateNew.substring(0, 2)), Integer.parseInt(sectionStartDateNew.substring(3)));
            newEndDate = new Date(Integer.parseInt(sectionEndDateNew.substring(0, 2)), Integer.parseInt(sectionEndDateNew.substring(3)));

            //Monday
            addTimeSlot(newLectureId, DayOfWeek.MONDAY, sectionMondayField, sectionMondayEndField,
                    newStartDate, newEndDate, sectionLocationNew);

            //Tuesday
            addTimeSlot(newLectureId, DayOfWeek.TUESDAY, sectionTuesdayField, sectionTuesdayEndField,
                    newStartDate, newEndDate, sectionLocationNew);

            //Wednesday
            addTimeSlot(newLectureId, DayOfWeek.WEDNESDAY, sectionWednesdayField, sectionWednesdayEndField,
                    newStartDate, newEndDate, sectionLocationNew);

            //Thursday
            addTimeSlot(newLectureId, DayOfWeek.THURSDAY, sectionThursdayField, sectionThursdayEndField,
                    newStartDate, newEndDate, sectionLocationNew);

            //Friday
            addTimeSlot(newLectureId, DayOfWeek.FRIDAY, sectionFridayField, sectionFridayEndField,
                    newStartDate, newEndDate, sectionLocationNew);
        }

        return newLectureId;
    }

    public void addTimeSlot(String sectionId, DayOfWeek weekday, EditText inputFieldStartTime, EditText inputFieldEndTime,
                            Date inputStartDate, Date inputEndDate, String inputLocation){

        if (!inputFieldStartTime.getText().toString().trim().equals("") && !inputFieldEndTime.getText().toString().trim().equals("")) {
            int sectionWeekdayStartTime = Integer.parseInt(inputFieldStartTime.getText().toString().replace(":", ""));
            int sectionWeekdayEndTime = Integer.parseInt(inputFieldEndTime.getText().toString().replace(":", ""));

            TimeRange newTimeRange = new TimeRange(sectionWeekdayStartTime, sectionWeekdayEndTime);
            timeSlotService.addTimeSlot(sectionId, weekday, new TimeSlot(weekday, newTimeRange, inputStartDate, inputEndDate, inputLocation));
        }
    }
}
