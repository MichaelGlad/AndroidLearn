package net.glm.bindservice98s;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    Button btnStart,btnStop,btnBind,btnUnbind;
    Button btnUp,btnDown,btnGetRandomNumber;
    TextView txtRandomNumber;

    boolean isServiceBound = false;
    ServiceConnection serviceConnection;
    Intent intentForService;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnBind = (Button) findViewById(R.id.btnBind);
        btnUnbind = (Button) findViewById(R.id.btnUnbind);
        btnUp = (Button) findViewById(R.id.btnUp);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnGetRandomNumber = (Button) findViewById(R.id.btnGetNumber);
        txtRandomNumber = (TextView) findViewById(R.id.txtRandomNumber);


        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnBind.setOnClickListener(this);
        btnUnbind.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnGetRandomNumber.setOnClickListener(this);
        Log.d(LOG_TAG,"Main Service Thread is - " + Thread.currentThread().getId() + " With Name - " + Thread.currentThread().getName());

        intentForService = new Intent(this,MyService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btnStart):
                startService(intentForService);
                    break;
            case (R.id.btnStop):
                stopService(intentForService);
                break;
            case (R.id.btnBind):
                myBindService();
                break;
            case (R.id.btnUnbind):
                myUnbindService();
                break;
            case (R.id.btnUp):
                if(isServiceBound && (myService != null)){
                    myService.upInterval(100);
                }
                break;
            case (R.id.btnDown):
                if(isServiceBound && (myService != null)){
                    myService.downInterval(100);
                }
                break;
            case (R.id.btnGetNumber):
                setRandomNumberToTxt();
                break;

        }
    }

    private void myBindService(){
        if (serviceConnection == null){
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    MyService.MyBinder myBinder = (MyService.MyBinder) service;
                    myService = myBinder.getService();
                    isServiceBound = true;

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceBound = false;

                }
            };

        }
        bindService(intentForService,serviceConnection,BIND_AUTO_CREATE);
    }

    private void myUnbindService(){
        if(isServiceBound){
            unbindService(serviceConnection);
            isServiceBound = false;
        }

    }

    private void setRandomNumberToTxt(){
        if(isServiceBound){
            txtRandomNumber.setText(" Random number is - " + myService.getRandomNumber()+ " with Interval - " + myService.getInterval());
        }else {
            txtRandomNumber.setText("Service not Bound");
        }
    }
}
