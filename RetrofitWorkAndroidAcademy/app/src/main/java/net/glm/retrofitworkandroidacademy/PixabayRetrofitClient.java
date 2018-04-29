package net.glm.retrofitworkandroidacademy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayRetrofitClient {

    @GET("/api/")
    Call<ImageSearchResult> getPicturesForRequest(@Query("key") String key,
                                                  @Query("q") String searchQuery,
                                                  @Query("image_type") String imageType);

    @GET("https://pixabay.com/api/?key=8821516-e701348a212fb9a667d12762c&q=yellow+flowers&image_type=photo&pretty=true")
    Call<ImageSearchResult> getPicturesForRequestStatic();
}
