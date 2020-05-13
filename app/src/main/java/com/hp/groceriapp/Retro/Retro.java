package com.hp.groceriapp.Retro;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {
    // create a method which returns our api interface class
    public Apis getApi()
    {
        //handle time outs
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();
        //build retrofit object
        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://srishti-systems.info/projects/groceryShop/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //connect api class with this builder

        Apis apis=retrofit.create(Apis.class);
        return apis;
    }
}
