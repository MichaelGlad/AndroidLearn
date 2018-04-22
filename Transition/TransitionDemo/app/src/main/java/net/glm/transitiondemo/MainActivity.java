package net.glm.transitiondemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "MY_LOGS";

    private Button btnFirst, btnSecond, btnThird, btnFourth;
    private ViewGroup rootView;

    private int viewStateTransition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirst = findViewById(R.id.btn_first);
        btnSecond = findViewById(R.id.btn_second);
        btnThird = findViewById(R.id.btn_third);
        btnFourth = findViewById(R.id.btn_fourth);
        rootView = findViewById(R.id.cl_main);

        rootView.setOnClickListener(this);
        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
        btnThird.setOnClickListener(this);
        btnFourth.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 21){
            TransitionInflater transitionInflater = TransitionInflater.from(this);
            Transition exitTransition = transitionInflater.inflateTransition(R.transition.transition_a);
            getWindow().setExitTransition(exitTransition);
        }
    }

    private void toggleVisablity (int visability, View... views){

        Log.d(LOG_TAG,"In toggleVisability - " + visability);
        if (views != null){
            for (View currentView : views){
                currentView.setVisibility(visability);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.cl_main) {

            switch (viewStateTransition) {
                case 0:
                    Log.d(LOG_TAG," ZERO " + viewStateTransition);
                    if(btnFirst.getVisibility() == View.INVISIBLE){
                        toggleVisablity(View.VISIBLE,btnFirst,btnSecond,btnThird,btnFourth);
                    }
                    break;
                case 1:
                    Log.d(LOG_TAG," First button return " + viewStateTransition);
                    viewStateTransition = 0;
                    TransitionManager.beginDelayedTransition(rootView, new Fade().setDuration(1000));
                    toggleVisablity(View.VISIBLE,btnFirst,btnSecond,btnThird,btnFourth);
                    break;

                case 2:
                    Log.d(LOG_TAG," Second button return " + viewStateTransition);
                    viewStateTransition = 0;
                    TransitionManager.beginDelayedTransition(rootView, new Slide(Gravity.TOP).setDuration(1000));
                    toggleVisablity(View.VISIBLE,btnFirst,btnSecond,btnThird,btnFourth);
                    break;
                case 3:
                    Log.d(LOG_TAG," Third button return " + viewStateTransition);

                    break;
                case 4:
                    Log.d(LOG_TAG," Fourth button return " + viewStateTransition);
                    break;
                default:
                    Log.d(LOG_TAG," Deffault pressed " + viewStateTransition);
                    break;

            }

        }

        if (viewId == R.id.btn_first) {
            if (btnFirst.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(rootView, new Fade().setDuration(1000));
                toggleVisablity(View.INVISIBLE,btnFirst,btnSecond,btnThird,btnFourth);
                viewStateTransition = 1;
            }
        }

        if (viewId == R.id.btn_second) {
            if (btnSecond.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(rootView, new Slide(Gravity.LEFT).setDuration(1000));
                toggleVisablity(View.INVISIBLE,btnFirst,btnSecond,btnThird,btnFourth);
                viewStateTransition = 2;
            }
        }

        if (viewId == R.id.btn_third) {
            Intent intent = new Intent(this,ThirdActivity.class);
            String transitionName = getString(R.string.transition_view);

            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this,btnThird,transitionName);
            startActivity(intent,transitionActivityOptions.toBundle());

        }

        if (viewId == R.id.btn_fourth) {

            Intent intent = new Intent(this,SecondActivity.class);
            String transitionName = getString(R.string.transition_fourth_button);
            ActivityOptionsCompat mOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,btnFourth,transitionName);
            startActivity(intent,mOptionsCompat.toBundle());



        }


    }
}
