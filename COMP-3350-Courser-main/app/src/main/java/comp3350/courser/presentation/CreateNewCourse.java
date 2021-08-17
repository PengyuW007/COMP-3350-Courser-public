
package comp3350.courser.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.courser.R;
import comp3350.courser.presentation.Validator;

import comp3350.courser.business.CourseService;
import comp3350.courser.objects.Course;

public class CreateNewCourse extends AppCompatActivity {

    private EditText courseNameField;
    private EditText courseCodeField;
    private EditText courseTermField;
    private EditText courseYearField;
    private EditText courseDepartmentField;
    private EditText courseCreditHoursField;
    private CourseService courseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_course);

        courseService = new CourseService();

        courseNameField = (EditText)findViewById(R.id.courseNameField);
        courseCodeField = (EditText)findViewById(R.id.courseCodeField);
        courseTermField = (EditText)findViewById(R.id.courseTermField);
        courseYearField = (EditText)findViewById(R.id.courseYearField);
        courseDepartmentField = (EditText)findViewById(R.id.courseDepartmentField);
        courseCreditHoursField = (EditText)findViewById(R.id.courseCreditHoursField);

        Button AddSections = (Button)findViewById(R.id.addSectionsButton);
        AddSections.setOnClickListener((v -> buttonAddSectionsOnClick()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_course_menu, menu);
        return true;
    }


    public void buttonAddSectionsOnClick() {
        String newCourseId = createCourse();

        if (newCourseId != null) {
            Intent newSectionIntent = new Intent(CreateNewCourse.this, CreateNewSection.class);
            newSectionIntent.putExtra("CourseID", newCourseId);
            CreateNewCourse.this.startActivity(newSectionIntent);
        }
    }

    public String createCourse(){
        Course newCourse;
        String newCourseID = null;

        boolean isValid = Validator.validateNewCourse(courseNameField, courseCodeField, courseTermField, courseYearField, courseDepartmentField, courseCreditHoursField);

        if (isValid) {
            String newCourseName = courseNameField.getText().toString();
            String newCourseCode = courseCodeField.getText().toString();
            String newCourseTerm = courseTermField.getText().toString();
            String newCourseDepartment = courseDepartmentField.getText().toString();
            int newCourseYear = Integer.parseInt(courseYearField.getText().toString());
            int newCourseCredits = Integer.parseInt(courseCreditHoursField.getText().toString());

            newCourse = new Course(newCourseCode, newCourseName, newCourseDepartment, newCourseTerm, newCourseYear, newCourseCredits, null);

            if (!courseService.isUnique(newCourse)) {
                Alerts.warning(this, "There is already a course with code '"+newCourseCode+"' in the term '"+newCourseTerm+" "+newCourseYear+"'!");
            } else {
                newCourseID = courseService.addCourse(newCourse);
            }
        }

        return newCourseID;
    }
}
