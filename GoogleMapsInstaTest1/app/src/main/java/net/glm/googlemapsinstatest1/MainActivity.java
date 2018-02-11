package net.glm.googlemapsinstatest1;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MapsTest1_MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 901;


    private Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isServicesAvailable()){

        }
    }

    private void init(){
        btnMap = findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean isServicesAvailable(){
        Log.d(LOG_TAG," isServicesAvailable:  Checking Google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(available == ConnectionResult.SUCCESS){
            Log.d(LOG_TAG," isServicesAvailable: Google Services ia working");
            return true;
        }else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(LOG_TAG," isServicesAvailable: On error accured but User can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            Log.d(LOG_TAG," isServicesAvailable: On UNFIXED error accured ");
            Toast.makeText(this,"You can't make mao request",Toast.LENGTH_SHORT);

        }
        return false;
    }
}
