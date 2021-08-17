package comp3350.courser.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.courser.R;
import java.util.ArrayList;

import comp3350.courser.business.CourseService;
import comp3350.courser.objects.Course;

public class WorkspaceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CourseService courseService = new CourseService();
    ArrayList<Course> courses; // This will receive a list of courses from the DB
    String[] courseCodes;
    String[] courseNames; // These are the fields that will be displayed in the workspace
    String[] courseTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);

        recyclerView = findViewById(R.id.recyclerView);

        Button createSchedule = (Button)findViewById(R.id.createScheduleButton);

        courses = courseService.getCourseList();
        getCourses(); // Populates the fields to be displayed

        CourseAdapter courseAdapter = new CourseAdapter(this, courseCodes, courseNames, courseTerms);
        recyclerView.setAdapter(courseAdapter);

        createSchedule.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("submit button");
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.workspace_menu,menu);
        return true;
    }

    public void getCourses() {
        courseCodes = new String[courses.size()];
        courseNames = new String[courses.size()];
        courseTerms = new String[courses.size()];

        for(int i = 0; i < courses.size(); i++) {
            courseCodes[i] = courses.get(i).getCourseCode();
            courseNames[i] = courses.get(i).getName();
            courseTerms[i] = courses.get(i).getTerm();
        }
    }

    public void buttonNewCourseOnClick(View v) {
        Intent newCourseIntent = new Intent(WorkspaceActivity.this, CreateNewCourse.class);
        WorkspaceActivity.this.startActivity(newCourseIntent);
    }

}