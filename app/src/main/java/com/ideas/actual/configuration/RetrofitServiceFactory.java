package com.ideas.actual.configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitServiceFactory {
    public static final String API_BASE_URL = "http://164.68.101.162:8084/api";
    /*public static final String API_BASE_URL = "http://10.0.2.2:8084/api";*/
   /* public static final String API_BASE_URL = "http://192.168.0.14:8084/api";*/



    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL + "/")
                                                    .addConverterFactory(GsonConverterFactory.create());

    public static <T> T createService(final Class<T> clazz) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(clazz);
    }

    public static <S> S createService(Class<S> clazz, final String authToken){
        httpClient.addInterceptor(new AuthenticationInterceptor(authToken));
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(clazz);
    }
}
