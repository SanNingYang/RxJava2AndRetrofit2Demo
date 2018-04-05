package ysn.com.rxjava2andretrofit2demo.http;


import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

public class RetrofitFactory {

    private static final String BASE_URL = "http://api.laifudao.com/open/";
    private static final int TIME_OUT = 30;

    private static RetrofitService retrofitService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(buildOkHttpClient())
            .build()
            .create(RetrofitService.class);

    public static RetrofitService getInstance() {
        return retrofitService;
    }

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                // 添加通用的Header
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        // 替换为自己的token
//                        builder.addHeader("token", "123");
                        return chain.proceed(builder.build());
                    }
                })
                /*
                这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
                出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
                 */
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("test", "message: " + message);

                    }
                }).setLevel(HttpLoggingInterceptor.Level.BASIC))
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
//                 此处可以添加Gson 自定义TypeAdapter
//                .registerTypeAdapter(Joke.class, new JokeTypeAdapter())
                .create();
    }
}
