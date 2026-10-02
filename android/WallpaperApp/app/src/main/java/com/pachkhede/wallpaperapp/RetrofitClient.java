package com.pachkhede.wallpaperapp;

import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://nexwall.kodnextech.com/";
    private static final String[] API_KEYS = {
            "128|7j1rsiyNbzwLfCSF2fPsU5cLsoCrcujMX6SAxOqSd20ecb0d",
            "134|bpcYE6iWTehUYBX25APDaFNaE52fiMLL71JWkaYy4751b5fb",
            "129|YTW2JbNRkNG2bKAxUPyUk9kBxFessWnqZnio6djPdb45d831"
    };




    private static Retrofit retrofit;

    public static NexWallApi getApi() {
        Random random = new Random();

        String apiKey = API_KEYS[random.nextInt(API_KEYS.length)];

        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain->{
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization","Bearer " + apiKey)
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