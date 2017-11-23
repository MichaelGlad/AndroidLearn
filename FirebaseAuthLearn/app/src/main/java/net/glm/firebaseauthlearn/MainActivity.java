package net.glm.firebaseauthlearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEmailAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmailAuth = (Button) findViewById(R.id.btn_email_auth);
        btnEmailAuth.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btn_email_auth){
            Intent mIntent = new Intent(this,EmailAuthActivity.class);
            startActivity(mIntent);
        }



    }
}
