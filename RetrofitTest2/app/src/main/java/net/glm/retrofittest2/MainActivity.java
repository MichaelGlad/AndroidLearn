package net.glm.retrofittest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import net.glm.retrofittest2.service.UserClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etFullName,etEmail,etAge;
    CheckBox chbFuel,chbFlatTire;
    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAge = (EditText) findViewById(R.id.etAge);

        chbFlatTire = (CheckBox) findViewById(R.id.checkboxFlatTire);
        chbFuel = (CheckBox) findViewById(R.id.checkboxFuel);

        btnAccept = (Button) findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        User user = new User (
                etFullName.getText().toString(),
                etEmail.getText().toString(),
                Integer.parseInt(etAge.getText().toString())
        );
        if (chbFlatTire.isChecked()) user.setRepairFlatTire(true);
        else user.setRepairFlatTire(false);
        if (chbFuel.isChecked()) user.setBringFuel(true);
        else user.setBringFuel(false);


        sendNetworkRequest( user);

    }

    private void sendNetworkRequest(User user) {
        //create Retrofit instance
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/RetrofitServlet")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //get client & call object for request

        UserClient client = retrofit.create(UserClient.class);
    }
}