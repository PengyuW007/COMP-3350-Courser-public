package comp3350.courser.presentation;

import android.app.Activity;
import android.widget.EditText;

public class Validator {
    public static boolean validateNewCourse(
            EditText courseName,
            EditText courseCode,
            EditText courseTerm,
            EditText courseYear,
            EditText courseDepartment,
            EditText courseCredits
    ) {
        String newCourseName = courseName.getText().toString();
        String newCourseCode = courseCode.getText().toString();
        String newCourseTerm = courseTerm.getText().toString();
        String newCourseYear = courseYear.getText().toString();
        String newCourseDepartment = courseYear.getText().toString();
        String newCourseCredits = courseCredits.getText().toString();

        boolean wasValid = true;

        if (newCourseName.trim().equals("")) {
            courseName.setError("Course name cannot be empty!");
            wasValid = false;
        }

        if (newCourseCode.trim().equals("")) {
            courseCode.setError("Course code cannot be empty!");
            wasValid = false;
        }

        if (newCourseTerm.trim().equals("")) {
            courseTerm.setError("Term cannot be empty!");
            wasValid = false;
        }

        try {
            Integer.parseInt(newCourseYear);
        } catch (NumberFormatException e) {
            courseYear.setError("Course year must be a non-empty integer!");
            wasValid = false;
        }

        if (newCourseDepartment.trim().equals("")) {
            courseDepartment.setError("Course department cannot be empty!");
            wasValid = false;
        }

        try {
            Integer.parseInt(newCourseCredits);
        } catch (NumberFormatException e) {
            courseCredits.setError("Course credits must be a non-empty integer!");
            wasValid = false;
        }

        return wasValid;
    }

    public static boolean validateSection(
            Activity source,
            EditText sectionCRNField,
            EditText sectionCodeField,
            EditText sectionInstructorField,
            EditText sectionCampusField,
            EditText sectionLocationField,
            EditText sectionStartDateField,
            EditText sectionEndDateField,
            EditText[] startFields,
            EditText[] endFields
    ) {
        boolean wasValid = true;

        String crnField = sectionCRNField.getText().toString();
        String codeField = sectionCodeField.getText().toString();
        String instructorField = sectionInstructorField.getText().toString();
        String campusField = sectionCampusField.getText().toString();
        String locationField = sectionLocationField.getText().toString();
        String startDateField = sectionStartDateField.getText().toString();
        String endDateField = sectionEndDateField.getText().toString();

        try {
            Integer.parseInt(crnField);
        } catch (NumberFormatException e) {
            sectionCRNField.setError("Section CRN must be a non-empty integer!");
            wasValid = false;
        }

        if (codeField.trim().equals("")) {
            sectionCodeField.setError("Section code cannot be empty!");
            wasValid = false;
        }

        if (instructorField.trim().equals("")) {
            sectionInstructorField.setError("Instructor cannot be empty!");
            wasValid = false;
        }

        if (campusField.trim().equals("")) {
            sectionCampusField.setError("Campus cannot be empty!");
            wasValid = false;
        }

        if (locationField.trim().equals("")) {
            sectionLocationField.setError("Location cannot be empty!");
            wasValid = false;
        }

        if (!validateDateString(startDateField)) {
            sectionStartDateField.setError("Invalid start date format!");
            wasValid = false;
        }

        if (!validateDateString(endDateField)) {
            sectionEndDateField.setError("Invalid end date format!");
            wasValid = false;
        }

        boolean containsTime = false;

        for (int i = 0; i < startFields.length; i++) {
            boolean startFieldEmpty = startFields[i].getText().toString().trim().equals("");
            boolean endFieldEmpty = endFields[i].getText().toString().trim().equals("");

            if (!startFieldEmpty && !endFieldEmpty) {
                if (!validateTimeString(startFields[i].getText().toString())) {
                    startFields[i].setError("Invalid time format!");
                    wasValid = false;
                }

                if (!validateTimeString(endFields[i].getText().toString())) {
                    endFields[i].setError("Invalid time format!");
                    wasValid = false;
                }

                containsTime = true;
            } else if (!startFieldEmpty) {
                endFields[i].setError("You must fill in both fields for a day!");
            } else if (!endFieldEmpty) {
                startFields[i].setError("You must fill in both fields for a day!");
            }
        }

        if (!containsTime) {
            Alerts.warning(source, "You must fill in at least one time slot!");
            wasValid = false;
        }

        return wasValid;
    }

    private static boolean validateDateString(String dateString) {
        return validateSplitIntegerString(dateString, "/", 1, 12, 1, 31);
    }

    private static boolean validateTimeString(String timeString) {
        return validateSplitIntegerString(timeString, ":", 0, 23, 0, 59);
    }

    private static boolean validateSplitIntegerString(String target, String delim, int firstMin, int firstMax, int secondMin, int secondMax) {
        int delimIndex = target.indexOf(delim);
        int firstNum;
        int secondNum;

        if (delimIndex == -1) {
            return false;
        } else {
            String[] splitString = target.split(delim);
            if (splitString.length != 2) {
                return false;
            }
            try {
                firstNum = Integer.parseInt(splitString[0]);
                secondNum = Integer.parseInt(splitString[1]);

                if ((firstNum < firstMin || firstNum > firstMax) || (secondNum < secondMin || secondNum > secondMax)) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }
}
