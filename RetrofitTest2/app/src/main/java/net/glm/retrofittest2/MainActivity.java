package net.glm.retrofittest2;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import net.glm.retrofittest2.service.UserClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etFullName, etEmail, etAge;
    CheckBox chbFuel, chbFlatTire;
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
        User user = new User(
                etFullName.getText().toString(),
                etEmail.getText().toString(),
                Integer.parseInt(etAge.getText().toString())
        );
        if (chbFlatTire.isChecked()) user.setRepairFlatTire(true);
        else user.setRepairFlatTire(false);
        if (chbFuel.isChecked()) user.setBringFuel(true);
        else user.setBringFuel(false);


        sendNetworkRequest(user);

    }

    private void sendNetworkRequest(User user) {

        // create OkHttp client
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();

        // create of httpLogging Interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //4 levels of Interceptor BODY it"s full
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Use Iterceptor only in Debug MODE !!!!!!
        if (BuildConfig.DEBUG){
            okhttpClientBuilder.addInterceptor(loggingInterceptor);
        }

        //create Retrofit instance
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClientBuilder.build());

        Retrofit retrofit = builder.build();

        //get client & call object for request
        Log.d("MY_LOG", user.toString());

        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.createAccount(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                if (user != null) {
                    Log.d("MY_LOG_from Retrofit", user.toStringID());
                } else {
                    Log.d("MY_LOG_from Retrofit", "User is NULL");
                    Log.d("MY_LOG_from Retrofit", response.toString());
                }

                 Toast.makeText(MainActivity.this, "User ID - " + response.body().getId(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();


            }
        });

    }
}