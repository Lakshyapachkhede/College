package com.pachkhede.wallpaperapp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://nexwall.kodnextech.com/";
    private static final String API_KEY = "128|7j1rsiyNbzwLfCSF2fPsU5cLsoCrcujMX6SAxOqSd20ecb0d";


    private static Retrofit retrofit;

    public static NexWallApi getApi() {

        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain->{
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization","Bearer " + API_KEY)
                        .addHeader("Accept","application/json")
                        .build();

                return chain.proceed(request);
            }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(NexWallApi.class);
    }
}