package comp3350.courser.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.example.courser.R;

import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    String courseCodes[], courseNames[], courseTerms[];
    ArrayList<String> selectedCourseCodes;
    Context context;

    public CourseAdapter(Context ct, String courseCodes[], String courseNames[], String courseTerms[]) {
        context = ct;
        this.courseCodes = courseCodes;
        this.courseNames = courseNames;
        this.courseTerms = courseTerms;
        this.selectedCourseCodes = new ArrayList<String>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.course_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewHolder holder, int position) {
        holder.courseName.setText(courseNames[position]);
        holder.courseCode.setText(courseCodes[position]);
        holder.courseTerm.setText(courseTerms[position]);

        holder.checkBox.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()) {
                    selectedCourseCodes.add(courseCodes[position]);
                }
                else {
                    selectedCourseCodes.remove(courseCodes[position]);
                }
            }
        }));
    }

    @Override
    public int getItemCount() {
        return courseNames.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseName, courseCode, courseTerm;
        CheckBox checkBox;
        ConstraintLayout workspaceLayout;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.courseNameText);
            courseCode = itemView.findViewById(R.id.courseCodeText);
            courseTerm = itemView.findViewById(R.id.courseTermText);
            checkBox = itemView.findViewById(R.id.checkbox1);
            workspaceLayout = itemView.findViewById(R.id.workspaceLayout);
        }
    }

    //Gets the selected course list to send to the business layer
    public ArrayList<String> getSelectedCourseCodes() {
        return selectedCourseCodes;
    }


}
