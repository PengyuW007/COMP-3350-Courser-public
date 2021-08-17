package comp3350.courser.tests.acceptance;

//from: https://stackoverflow.com/questions/28476507/using-espresso-to-click-view-inside-recyclerview-item

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

public class ReclyclerClickOnView {

    public static ViewAction ReclyclerClickOnView(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view from recyclerview with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}
