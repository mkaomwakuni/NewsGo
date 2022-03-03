package com.mkao.newsgo;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsService {
    public static final String KEY = "f36d74acc142444f96809b7b9395716b";



    NewsApi nnewsApi;

    //Retrofit acts as a bridge between the api and
    // the okHttp
    public NewsService(){
        Retrofit retrofit = createRetrofit();
        nnewsApi = retrofit.create(NewsApi.class);
    }

    public NewsApi getNnewsApi() { return nnewsApi;}

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient createHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                final Request original = chain.request();
                final HttpUrl originalHttpUrl = original.url();
                final HttpUrl url = originalHttpUrl.newBuilder()
                        //.addQueryParameter(api_key,KEY)
                .build();
                final Request.Builder requestBuilder= original.newBuilder()
                        .url(url);
                final Request request = requestBuilder.build();
                return chain.proceed(request);

            }
        });
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        return httpClient.build();
    }


}
