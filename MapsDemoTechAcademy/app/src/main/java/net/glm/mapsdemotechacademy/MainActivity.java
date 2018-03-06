package net.glm.mapsdemotechacademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMapsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMapsActivity = findViewById(R.id.btn_map_activity);
        btnMapsActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_map_activity){
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
        }

    }
}
