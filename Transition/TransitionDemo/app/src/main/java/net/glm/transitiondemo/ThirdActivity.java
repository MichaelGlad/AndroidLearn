package net.glm.transitiondemo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;

public class ThirdActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MY_LOGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        if (Build.VERSION.SDK_INT >= 21) {

            Slide slide = new Slide();
            slide.setDuration(2000);
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            slide.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setReturnTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((Build.VERSION.SDK_INT >= 21) && keyCode == KeyEvent.KEYCODE_BACK) {

            this.supportFinishAfterTransition();
            Log.d(LOG_TAG, " BackKey in Third Activity pressed");

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
