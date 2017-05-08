package net.glm.service95s;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;
    final int TASK4_CODE = 4;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_PENDING_INTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    TextView tvTask1, tvTask2, tvTask3,tvTask4;
    Button btnBroadcastReceiver, btnPendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTask1 = (TextView) findViewById(R.id.tv_task1);
        tvTask2 = (TextView) findViewById(R.id.tv_task2);
        tvTask3 = (TextView) findViewById(R.id.tv_task3);
        tvTask4 = (TextView) findViewById(R.id.tv_task4);

        btnBroadcastReceiver = (Button) findViewById(R.id.btn_broadcast_receiver);
        btnPendingIntent = (Button) findViewById(R.id.btn_pending_intent);
        btnBroadcastReceiver.setOnClickListener(this);
        btnPendingIntent.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        PendingIntent pendingIntent;
        Intent intentInOnClick;
        BroadcastReceiver broadcastReceiver;

        if (v.getId() == R.id.btn_pending_intent) {

            //Start Task1 with PendingIntent
            intentInOnClick = new Intent(this, PendingIntentService.class);
            pendingIntent = createPendingResult(TASK1_CODE, intentInOnClick, 0);
            intentInOnClick.putExtra(PARAM_TIME, 14)
                    .putExtra(PARAM_PENDING_INTENT, pendingIntent);
            startService(intentInOnClick);

            //Start Task2 with PendingIntent
            intentInOnClick = new Intent(this, PendingIntentService.class);
            pendingIntent = createPendingResult(TASK2_CODE, intentInOnClick, 0);
            intentInOnClick.putExtra(PARAM_TIME, 3)
                    .putExtra(PARAM_PENDING_INTENT, pendingIntent);
            startService(intentInOnClick);

            //Start Task3 with PendingIntent
            intentInOnClick = new Intent(this, PendingIntentService.class);
            pendingIntent = createPendingResult(TASK3_CODE, intentInOnClick, 0);
            intentInOnClick.putExtra(PARAM_TIME, 6)
                    .putExtra(PARAM_PENDING_INTENT, pendingIntent);
            startService(intentInOnClick);




        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String resultString = "";

        Log.d(LOG_TAG," Request code - " + requestCode + " Result code - " + resultCode);

        if(resultCode == STATUS_START){
            resultString = "start";
        }

        if(resultCode == STATUS_FINISH){
            resultString = "finish, result - " + data.getIntExtra(PARAM_RESULT,0);
        }

        switch (requestCode){
            case TASK1_CODE:
                tvTask1.setText("Task 1 "+resultString);
                break;
            case TASK2_CODE:
                tvTask2.setText("Task 2 "+resultString);
                break;
            case TASK3_CODE:
                tvTask3.setText("Task 3 "+resultString);
                break;
            case TASK4_CODE:
                tvTask4.setText("Task 4 "+resultString);
                break;
        }

        if(resultCode == STATUS_FINISH && requestCode == TASK3_CODE){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent4StartService = new Intent(this, PendingIntentService.class);
            PendingIntent pendingIntent4StartService = createPendingResult(TASK4_CODE, intent4StartService, 0);
            intent4StartService.putExtra(PARAM_TIME, 7)
                    .putExtra(PARAM_PENDING_INTENT, pendingIntent4StartService);
            startService(intent4StartService);

        }
    }
}
