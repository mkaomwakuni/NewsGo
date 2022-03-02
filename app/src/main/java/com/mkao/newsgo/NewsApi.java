package com.mkao.newsgo;

import kotlin.reflect.KCallable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<NewsList> getBasedOnLanguage(@Query("language") String lang,
                                      @Query("country")String country,
                                      @Query("apiKey") String key);
}
