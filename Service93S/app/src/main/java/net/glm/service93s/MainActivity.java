package net.glm.service93s;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    Button startServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = (Button) findViewById(R.id.start_button);

        startServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startService(new Intent(this,InsideService.class).putExtra("time",10));
        startService(new Intent(this,InsideService.class).putExtra("time",2));
        startService(new Intent(this,InsideService.class).putExtra("time",4));

    }
}
