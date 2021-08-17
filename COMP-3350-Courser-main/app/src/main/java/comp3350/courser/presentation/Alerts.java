package comp3350.courser.presentation;

import android.app.Activity;
import android.app.AlertDialog;

public class Alerts {
    public static void warning(Activity source, String message) {
        AlertDialog alert = new AlertDialog.Builder(source).create();

        alert.setTitle("Warning");
        alert.setMessage(message);

        alert.show();
    }
}
