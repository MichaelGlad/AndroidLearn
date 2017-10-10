package net.glm.retrofittest2.service;

import net.glm.retrofittest2.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Michael on 24/09/2017.
 */

public interface UserClient {
    @POST("user")
    Call<User> createAccount (@Body User user);
}
