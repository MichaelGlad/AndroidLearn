package net.glm.activityrecognition;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        ResultCallback<Status> ,
        View.OnClickListener{

    private final String LOG_TAG = "ActivityRecognitionApp";
    private final int MY_PERMISSION_LOCATION = 102;
    private Boolean permissionIsGranted = false;

    private TextView tvActivities;
    private Button btnRequestUpdate;
    private Button btnRemoveUpdate;
    private GoogleApiClient activitiesGoogleApiClient;
    private LocationRequest mLocationRequest;

    protected ActivityDetectionBroadcastReceiver mBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvActivities = findViewById(R.id.tv_activities);
        btnRequestUpdate = findViewById(R.id.btn_request_activity_update);
        btnRemoveUpdate = findViewById(R.id.btn_remove_activity_update);
        btnRequestUpdate.setOnClickListener(this);
        btnRemoveUpdate.setOnClickListener(this);
        btnRemoveUpdate.setEnabled(false);

        mBroadcastReceiver = new ActivityDetectionBroadcastReceiver();


        activitiesGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_request_activity_update){
            requestActivityUpdatesButtonHandler();
        }
        if (v.getId() == R.id.btn_remove_activity_update){
            removeActivityUpdateButtonHandler();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        activitiesGoogleApiClient.connect();

    }

    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,
                new IntentFilter(Constants.BROADCAST_ACTION));

        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);


    }

    @Override
    protected void onStop() {
        activitiesGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(LOG_TAG, " Connected to GoogleApiClient");


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(LOG_TAG, "Connection suspended");
        activitiesGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(LOG_TAG, " Connection faild: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    public void requestActivityUpdatesButtonHandler() {
        if (!activitiesGoogleApiClient.isConnected()) {
            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                activitiesGoogleApiClient,
                Constants.DETECTION_INTERVAL_IN_MILLISECONDS,
                getActivityDetectionPendingIntent()).setResultCallback(this);
        btnRequestUpdate.setEnabled(false);
        btnRemoveUpdate.setEnabled(true);
    }

    public void removeActivityUpdateButtonHandler() {
        if (!activitiesGoogleApiClient.isConnected()) {
            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(
                activitiesGoogleApiClient,
                getActivityDetectionPendingIntent()).setResultCallback(this);
        btnRemoveUpdate.setEnabled(false);
        btnRequestUpdate.setEnabled(true);

    }


    @Override
    public void onResult(@NonNull Status status) {
        if (status.isSuccess()) {
            Log.d(LOG_TAG, "Successfully added activity detection.");

        } else {
            Log.d(LOG_TAG, "Error adding or removing activity detection: " + status.getStatusMessage());
        }

    }

    boolean checkPermission() {
        if (permissionIsGranted) {
            return permissionIsGranted;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_LOCATION);
            }
        } else permissionIsGranted = true;

        return permissionIsGranted;

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSION_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permissionIsGranted = true;
            } else {
                permissionIsGranted = false;
                Toast.makeText(this, " This App request location Permission to be granted ", Toast.LENGTH_SHORT).show();


            }
        }
//
    }


    public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver {
        protected static final String TAG = "receiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<DetectedActivity> updatedActivities =
                    intent.getParcelableArrayListExtra(Constants.ACTIVITY_EXTRA);
            String strStatus = "";
            for (DetectedActivity thisActivity : updatedActivities) {
                strStatus += getActivityString(thisActivity.getType()) + thisActivity.getConfidence() + "%\n";
            }

            tvActivities.setText(strStatus);

        }


    }

    public String getActivityString(int detectesActivityType) {
        Resources resources = this.getResources();
        switch (detectesActivityType) {
            case DetectedActivity.IN_VEHICLE:
                return resources.getString(R.string.in_vehicle);
            case DetectedActivity.ON_BICYCLE:
                return resources.getString(R.string.on_bicycle);
            case DetectedActivity.ON_FOOT:
                return resources.getString(R.string.on_foot);
            case DetectedActivity.RUNNING:
                return resources.getString(R.string.running);
            case DetectedActivity.STILL:
                return resources.getString(R.string.still);
            case DetectedActivity.TILTING:
                return resources.getString(R.string.tilting);
            case DetectedActivity.UNKNOWN:
                return resources.getString(R.string.unknown);
            case DetectedActivity.WALKING:
                return resources.getString(R.string.walking);

            default:
                return resources.getString(R.string.unidentifiable_activity);
        }
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(this, DetectedActivitiesIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
