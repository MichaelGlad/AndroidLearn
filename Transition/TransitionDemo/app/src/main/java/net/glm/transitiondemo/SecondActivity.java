package net.glm.transitiondemo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;

public class SecondActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MY_LOGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (Build.VERSION.SDK_INT >= 21){
            TransitionInflater transitionInflater = TransitionInflater.from(this);
            Transition returnTransition = transitionInflater.inflateTransition(R.transition.transition_b);

            Slide slide = new Slide();
            slide.setDuration(2000);
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            slide.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setReturnTransition(returnTransition);
            getWindow().setEnterTransition(slide);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((Build.VERSION.SDK_INT >= 21) && keyCode == KeyEvent.KEYCODE_BACK) {

            this.supportFinishAfterTransition();
            Log.d(LOG_TAG, " BackKey in Second Activity pressed");

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
