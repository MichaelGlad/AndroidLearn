package net.glm.transitiondemo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (Build.VERSION.SDK_INT >= 21){
            TransitionInflater transitionInflater = TransitionInflater.from(this);
            Transition returnTransition = transitionInflater.inflateTransition(R.transition.transition_b);

            Slide slide = new Slide();
            slide.setDuration(3000);
            getWindow().setReturnTransition(returnTransition);
            getWindow().setEnterTransition(slide);
        }
    }
}
