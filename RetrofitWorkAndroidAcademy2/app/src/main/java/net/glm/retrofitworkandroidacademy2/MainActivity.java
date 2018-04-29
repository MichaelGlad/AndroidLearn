package net.glm.retrofitworkandroidacademy2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.glm.retrofitworkandroidacademy2.Model.ImageSearchResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://pixabay.com/";

    EditText etSearchField;
    TextView tvNumberOfHits;
    RecyclerView rvHits;

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearchField = findViewById(R.id.et_search_field);
        tvNumberOfHits = findViewById(R.id.tv_number_of_hits);
        rvHits = findViewById(R.id.rv_hits);

        layoutManager = new LinearLayoutManager(this);
        rvHits.setLayoutManager(layoutManager);
    }

    public void searchButtonOnClick(View view) {
        String inputForSearch;
        inputForSearch = etSearchField.getText().toString().trim().toLowerCase();
        inputForSearch = inputForSearch.replaceAll("\\s","+");
        if (inputForSearch == null || inputForSearch.length() < 3) {
            Toast.makeText(this, "Please enter string for search with minimum 3 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        PixabayRetrofitClient pixabayRetrofitClient = retrofit.create(PixabayRetrofitClient.class);


        Call<ImageSearchResult> call = pixabayRetrofitClient.getPicturesForRequest(getResources().getString(R.string.key),inputForSearch,"photo");
//        Call<ImageSearchResult> call = pixabayRetrofitClient.getPicturesForRequestStatic();

        call.enqueue(new Callback<ImageSearchResult>() {
            @Override
            public void onResponse(Call<ImageSearchResult> call, Response<ImageSearchResult> response) {
                ImageSearchResult result = response.body();

                if (result.getHits() != null) {
                    ImageAdapter adapter = new ImageAdapter(result.getHits());
                    rvHits.setAdapter(adapter);
                    tvNumberOfHits.setText(result.getTotalHits().toString());
                }


            }

            @Override
            public void onFailure(Call<ImageSearchResult> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sorry can't connect to server", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
