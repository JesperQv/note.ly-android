package com.jesperqvarfordt.taskhero.domain.api;

import com.jesperqvarfordt.taskhero.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private TaskHeroApi apiClient;

    public Retrofit() {
        apiClient = createApiClient();
    }

    private TaskHeroApi createApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(TaskHeroApi.class);
    }

    public TaskHeroApi getClient() {
        return apiClient;
    }

}
