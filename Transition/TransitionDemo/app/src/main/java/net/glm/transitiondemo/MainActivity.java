package net.glm.transitiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFirst, btnSecond, btnThird, btnFourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirst = findViewById(R.id.btn_first);
        btnSecond = findViewById(R.id.btn_second);
        btnThird = findViewById(R.id.btn_third);
        btnFourth = findViewById(R.id.btn_fourth);

        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
        btnThird.setOnClickListener(this);
        btnFourth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.btn_first){

        }

        if (viewId == R.id.btn_second){

        }
        if (viewId == R.id.btn_third){

        }
        if (viewId == R.id.btn_fourth){

        }


    }
}
