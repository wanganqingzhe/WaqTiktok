package com.kf.waqtiktok.utils;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static HttpUtils httpUtils;
    private static Retrofit retrofit;

    private HttpUtils() {

    }

    public static HttpUtils getInstance() {


        if (httpUtils == null) {

            synchronized (HttpUtils.class) {

                if (httpUtils == null) {
                    httpUtils = new HttpUtils();

                }

            }

        }
        return httpUtils;

    }

    public Retrofit getRetrofit() {

        if (retrofit == null) {


            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {

                            Request request = chain.request().newBuilder().addHeader("token", SPUtils.getInstance().getString("token"))
                                    .build();

                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .followSslRedirects(true)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .callTimeout(100, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://10.161.9.80:7012/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();


        }


        return retrofit;

    }


}
