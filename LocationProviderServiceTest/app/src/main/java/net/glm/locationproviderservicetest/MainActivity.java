package net.glm.locationproviderservicetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String BROADCAST_ACTION = "receiveLocationFromService";
    static final String LATITUDE = "Latitude";
    static final String LONGITUDE = "Longitude";
    static final String MY_LOG = "MyLog";

    private static final Integer PERMISSION_LOCATION_REQUEST_CODE = 102;
    private boolean permissionGranted = false;


    TextView tvLatitude, tvLongitude;
    Button btnStartService, btnStopServive;

    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLatitude = (TextView) findViewById(R.id.tv_latitude);
        tvLongitude = (TextView) findViewById(R.id.tv_longitude);
        tvLatitude.setText(getString(R.string.latitude) + ": Service not run");
        tvLongitude.setText(getString(R.string.longitude) + ": Service not run");

        btnStartService = (Button) findViewById(R.id.btn_start_service);
        btnStopServive = (Button) findViewById(R.id.btn_stop_service);
        btnStartService.setOnClickListener(this);
        btnStopServive.setOnClickListener(this);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Double latitude = intent.getDoubleExtra(LATITUDE, 250);
                Double longitude = intent.getDoubleExtra(LONGITUDE, 250);
                if ((latitude == 250) || (longitude == 250)) {
                    Log.d(MY_LOG, " The data from Location Service is wrong");
                } else {
                    tvLatitude.setText(getString(R.string.latitude) + ": " + latitude);
                    tvLongitude.setText(getString(R.string.longitude) + ": " + longitude);
                    Log.d(MY_LOG, " The Latitude: " + latitude + " and Longitude: " + longitude + " count is - " + count++);
                }
            }
        };

        intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_start_service:

                if (!permissionGranted) {
                    requestApplicationPermissions();
                }
                if (permissionGranted) {
                    startService(new Intent(MainActivity.this, LocationProviderService.class));
                }
                break;
            case R.id.btn_stop_service:
                stopService(new Intent(MainActivity.this, LocationProviderService.class));
                break;
        }

    }

    private void requestApplicationPermissions() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_LOCATION_REQUEST_CODE);
            } else permissionGranted = true;
        } else permissionGranted = true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permissionGranted = true;
            } else {
                permissionGranted = false;
                Toast.makeText(this, " This App request location Permission to be granted ", Toast.LENGTH_SHORT).show();
                tvLatitude.setText(getString(R.string.latitude) + ":  Permission not granted");
                tvLongitude.setText(getString(R.string.latitude) + ":  Permission not granted");

            }
        }
    }
}
