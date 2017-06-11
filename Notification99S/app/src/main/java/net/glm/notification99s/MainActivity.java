package net.glm.notification99s;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String FILE_NAME = "file name";

    Button btnStart,btnStop;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        tvResult = (TextView) findViewById(R.id.tvResult);

        String fileName = getIntent().getStringExtra(FILE_NAME);
        if(!TextUtils.isEmpty(fileName))
            tvResult.setText(fileName);



    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this,MyService.class);
        if (v.getId() == R.id.btnStart){
            startService(intent);
        }
        if (v.getId() == R.id.btnStop){
            stopService(intent);

        }
    }
}
