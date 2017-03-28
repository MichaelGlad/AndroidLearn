package net.glm.toolbartraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idOfItem = item.getItemId();
        
        switch (idOfItem){
            case (R.id.action_settings):
                Toast.makeText(this, "You press SETTINGS", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.action_share):
                Toast.makeText(this, "You press SHARE", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.action_delete):
                Toast.makeText(this, "You press DELETE", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
