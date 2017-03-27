package net.glm.recyclerviewtraining2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        txtName.setText("Animal Name : "+getIntent().getStringExtra(AnimalsRecyclerAdapter.NAME));
        txtEmail.setText("Animal Email : "+getIntent().getStringExtra(AnimalsRecyclerAdapter.MAIL));
    }
}
