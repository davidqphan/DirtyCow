package com.dphan.dirtycow;

import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by dphan on 12/12/17.
 */

public class ServiceGenerator {

    private static Retrofit.Builder builder = new Retrofit.Builder();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }
}