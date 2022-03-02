package com.mkao.newsgo;

import okhttp3.OkHttpClient;
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
        return null;
    }


}
