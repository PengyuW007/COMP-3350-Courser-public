package comp3350.courser.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.courser.R;

import java.util.ArrayList;
import java.util.List;

import comp3350.courser.business.CourseService;
import comp3350.courser.business.SectionService;
import comp3350.courser.business.TimeSlotService;
import comp3350.courser.objects.Course;
import comp3350.courser.objects.Date;
import comp3350.courser.objects.DayOfWeek;
import comp3350.courser.objects.Lab;
import comp3350.courser.objects.Lecture;
import comp3350.courser.objects.TimeRange;
import comp3350.courser.objects.TimeSlot;

public class CreateNewLab  extends AppCompatActivity {

    private Course courseCreated;
    private Lecture lectureCreated;
    private String courseId;
    private String lectureId;
    private CourseService courseService;
    private SectionService sectionService;
    private TimeSlotService timeSlotService;

    private EditText labCRNField;
    private EditText labCodeField;
    private EditText labInstructorField;
    private EditText labCampusField;
    private EditText labLocationField;
    private EditText labStartDateField;
    private EditText labEndDateField;
    private EditText labMondayField;
    private EditText labTuesdayField;
    private EditText labWednesdayField;
    private EditText labThursdayField;
    private EditText labFridayField;
    private EditText labMondayEndField;
    private EditText labTuesdayEndField;
    private EditText labWednesdayEndField;
    private EditText labThursdayEndField;
    private EditText labFridayEndField;

    EditText[] startFields;
    EditText[] endFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_lab);

        courseService = new CourseService();
        sectionService = new SectionService();
        timeSlotService = new TimeSlotService();

        List<String> labFKs = getIntent().getStringArrayListExtra("LabFKs");
        courseId = labFKs.get(0);
        lectureId = labFKs.get(1);

        courseCreated = courseService.getCourseById(courseId);
        lectureCreated = (Lecture) sectionService.getSectionById(lectureId);

        TextView courseTitle = (TextView) findViewById(R.id.courseLectureTitleNewSection);
        courseTitle.setText(courseCreated.getName() + " - " + lectureCreated.getSection());

        labCRNField = (EditText)findViewById(R.id.labCRNField);
        labCodeField = (EditText)findViewById(R.id.labCodeField);
        labInstructorField = (EditText)findViewById(R.id.labInstructorField);
        labCampusField = (EditText)findViewById(R.id.labCampusField);
        labLocationField = (EditText)findViewById(R.id.labLocationField);
        labStartDateField = (EditText)findViewById(R.id.labStartDateField);
        labEndDateField = (EditText)findViewById(R.id.labEndDateField);
        labMondayField = (EditText)findViewById(R.id.labMondayField);
        labTuesdayField = (EditText)findViewById(R.id.labTuesdayField);
        labWednesdayField = (EditText)findViewById(R.id.labWednesdayField);
        labThursdayField = (EditText)findViewById(R.id.labThursdayField);
        labFridayField = (EditText)findViewById(R.id.labFridayField);
        labMondayEndField = (EditText)findViewById(R.id.labMondayEndField);
        labTuesdayEndField = (EditText)findViewById(R.id.labTuesdayEndField);
        labWednesdayEndField = (EditText)findViewById(R.id.labWednesdayEndField);
        labThursdayEndField = (EditText)findViewById(R.id.labThusrdayEndField);
        labFridayEndField = (EditText)findViewById(R.id.labFridayEndField);

        startFields = new EditText[]{labMondayField, labTuesdayField, labWednesdayField, labThursdayField, labFridayField};
        endFields = new EditText[]{labMondayEndField, labTuesdayEndField, labWednesdayEndField, labThursdayEndField, labFridayEndField};

        Button AddAnotherSection = (Button)findViewById(R.id.addAnotherSectionButton);
        AddAnotherSection.setOnClickListener((v -> buttonAddAnotherSectionsOnClick()));

        Button AddNewLab = (Button)findViewById(R.id.addNewLabButton);
        AddNewLab.setOnClickListener((v -> buttonAddNewLabOnClick()));

        Button finishCreateLab = (Button)findViewById(R.id.finishButtonLab);
        finishCreateLab.setOnClickListener((v -> buttonFinishLabOnClick()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_lab_menu, menu);
        return true;
    }

    public void buttonAddAnotherSectionsOnClick() {
        if (Validator.validateSection(
                this,
                labCRNField,
                labCodeField,
                labInstructorField,
                labCampusField,
                labLocationField,
                labStartDateField,
                labEndDateField,
                startFields,
                endFields)) {
            String newLabId = addLab();

            if (newLabId != null) {
                Intent newSectionIntent = new Intent(CreateNewLab.this, CreateNewSection.class);
                newSectionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newSectionIntent.putExtra("CourseID", courseId);
                CreateNewLab.this.startActivity(newSectionIntent);
            }
        }
    }

    public void buttonAddNewLabOnClick(){
        if (Validator.validateSection(
                this,
                labCRNField,
                labCodeField,
                labInstructorField,
                labCampusField,
                labLocationField,
                labStartDateField,
                labEndDateField,
                startFields,
                endFields)) {
            String newLabId = addLab();
            ArrayList<String> labFKs = new ArrayList<String>();
            labFKs.add(courseId);
            labFKs.add(lectureId);

            if (newLabId != null) {
                Intent newLabIntent = new Intent(CreateNewLab.this, CreateNewLab.class);
                newLabIntent.putExtra("LabFKs", labFKs);
                CreateNewLab.this.startActivity(newLabIntent);
            }
        }
    }

    public void buttonFinishLabOnClick(){
        if (Validator.validateSection(
                this,
                labCRNField,
                labCodeField,
                labInstructorField,
                labCampusField,
                labLocationField,
                labStartDateField,
                labEndDateField,
                startFields,
                endFields)) {
            String newLabId = addLab();

            if (newLabId != null) {
                Intent newFinishIntent = new Intent(CreateNewLab.this, WorkspaceActivity.class);
                CreateNewLab.this.startActivity(newFinishIntent);
            }
        }
    }

    public String addLab(){
        Lab newLab;
        String newLabId = null;
        Date newStartDate;
        Date newEndDate;

        int labCRNNew = Integer.parseInt(labCRNField.getText().toString());
        String labCodeNew = labCodeField.getText().toString();
        String labInstructorNew = labInstructorField.getText().toString();
        String labCampusNew = labCampusField.getText().toString();
        String labLocationNew = labLocationField.getText().toString();
        String labStartDateNew = labStartDateField.getText().toString();
        String labEndDateNew = labEndDateField.getText().toString();

        newLab = new Lab(labCRNNew, labCodeNew, labInstructorNew, labCampusNew, null);

        if (sectionService.isUnique(newLab, courseId)) {
            newLabId = sectionService.addLab(courseId, lectureId, newLab);
        } else {
            Alerts.warning(this, "This course already has a lab with the CRN "+labCRNField.getText().toString()+"!");
        }

        if (newLabId != null) {
            newStartDate = new Date(Integer.parseInt(labStartDateNew.substring(0, 2)), Integer.parseInt(labStartDateNew.substring(3)));
            newEndDate = new Date(Integer.parseInt(labEndDateNew.substring(0, 2)), Integer.parseInt(labEndDateNew.substring(3)));

            //Monday
            addTimeSlot(newLabId, DayOfWeek.MONDAY, labMondayField, labMondayEndField,
                    newStartDate, newEndDate, labLocationNew);

            //Tuesday
            addTimeSlot(newLabId, DayOfWeek.TUESDAY, labTuesdayField, labTuesdayEndField,
                    newStartDate, newEndDate, labLocationNew);

            //Wednesday
            addTimeSlot(newLabId, DayOfWeek.WEDNESDAY, labWednesdayField, labWednesdayEndField,
                    newStartDate, newEndDate, labLocationNew);

            //Thursday
            addTimeSlot(newLabId, DayOfWeek.THURSDAY, labThursdayField, labThursdayEndField,
                    newStartDate, newEndDate, labLocationNew);

            //Friday
            addTimeSlot(newLabId, DayOfWeek.FRIDAY, labFridayField, labFridayEndField,
                    newStartDate, newEndDate, labLocationNew);
        }

        return newLabId;
    }

    public void addTimeSlot(String labId, DayOfWeek weekday, EditText inputFieldStartTime, EditText inputFieldEndTime,
                            Date inputStartDate, Date inputEndDate, String inputLocation){

        if (!inputFieldStartTime.getText().toString().trim().equals("") && !inputFieldEndTime.getText().toString().trim().equals("")) {
            int labWeekdayStartTime = Integer.parseInt(inputFieldStartTime.getText().toString().replace(":", ""));
            int labWeekdayEndTime = Integer.parseInt(inputFieldEndTime.getText().toString().replace(":", ""));

            TimeRange newTimeRange = new TimeRange(labWeekdayStartTime, labWeekdayEndTime);
            timeSlotService.addTimeSlot(labId, weekday, new TimeSlot(weekday, newTimeRange, inputStartDate, inputEndDate, inputLocation));
        }
    }


}
