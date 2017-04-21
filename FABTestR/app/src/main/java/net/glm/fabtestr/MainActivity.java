package net.glm.fabtestr;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public FloatingActionButton mainFab, secondFab, thirdFab;
    Animation animationFabOpen, animationFabClose, animationRotateClockwise, animationRotateAntiClockwise;
    Boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFab = (FloatingActionButton) findViewById(R.id.main_fab);
        secondFab = (FloatingActionButton) findViewById(R.id.second_fab);
        mainFab.setOnClickListener(this);

        animationFabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        animationFabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);
        animationRotateAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise);
        animationRotateClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_fab) {
            if (isOpen){
                animationRotateAntiClockwise.setDuration(1000);
               // animationFabClose.setDuration(1000);
                mainFab.setAnimation(animationRotateAntiClockwise);
                secondFab.startAnimation(animationFabClose);
                secondFab.setClickable(false);
                isOpen = false;

            }else {
                animationRotateClockwise.setDuration(1000);
                //animationFabOpen.setDuration(1000);
                mainFab.setAnimation(animationRotateClockwise);
                secondFab.startAnimation(animationFabOpen);
                secondFab.setClickable(true);
                isOpen=true;


            }


        }

    }
}
