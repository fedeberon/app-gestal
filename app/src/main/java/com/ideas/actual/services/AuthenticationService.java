package com.ideas.actual.services;

import com.ideas.actual.model.Authentication;
import com.ideas.actual.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface AuthenticationService {

    @POST("authenticate")
    Call<Authentication> getAuthenticate(@Body User user);

    @GET("user/me")
    Call<User> me();

}
