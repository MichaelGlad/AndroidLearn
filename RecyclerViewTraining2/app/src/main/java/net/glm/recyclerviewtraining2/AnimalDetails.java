package net.glm.recyclerviewtraining2;

import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalDetails extends AppCompatActivity {
    ImageView imageView;
    TextView txtName,txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);

        imageView = (ImageView)findViewById(R.id.datails_image);
        txtName = (TextView) findViewById(R.id.txt_details_name);
        txtEmail = (TextView) findViewById(R.id.txt_details_mail);

        imageView.setImageResource(getIntent().getIntExtra(AnimalsRecyclerAdapter.IMAGE_ID,00));
        txtName.setText(getIntent().getStringExtra(AnimalsRecyclerAdapter.NAME));
        txtEmail.setText(getIntent().getStringExtra(AnimalsRecyclerAdapter.MAIL));

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setEnterTransition(new Fade().setDuration(3000));
        }
    }
}
