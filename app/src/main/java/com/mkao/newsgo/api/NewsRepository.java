package com.mkao.newsgo.api;

import androidx.lifecycle.MutableLiveData;

import com.mkao.newsgo.api.models.NewsDTO;
import com.mkao.newsgo.api.models.NewsList;
import com.mkao.newsgo.api.models.Wrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static NewsRepository NnewsRepository;
    private static   NewsApi NnewsApi;
    private static NewsService NnewsService;


    private final MutableLiveData<Wrapper<List<NewsDTO>>> mHomelist = new MutableLiveData<>();
    private final  MutableLiveData<Wrapper<List<NewsDTO>>> mTrendList = new MutableLiveData<>();
    private final MutableLiveData<Wrapper<List<NewsDTO>>> mSearchList = new MutableLiveData<>();

    public static NewsRepository getInstance(){
        if (NnewsRepository==null) NnewsRepository = new NewsRepository();
        return NnewsRepository;
    }
    private NewsRepository(){
        NnewsService = new NewsService();
        NnewsApi = NnewsService.getNnewsApi();
    }
    public MutableLiveData<Wrapper<List<NewsDTO>>> getListBasedOnLanguage(String language,String country){
        Call<NewsList> listofNews = NnewsApi.getBasedOnLanguage(language,country,NnewsService.KEY);
        listofNews.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Wrapper<List<NewsDTO>> wrapper = new Wrapper<>();
                if (response.body()!=null){
                    wrapper.setData(response.body().getmNewsDTOList());
                }
                mHomelist.setValue(wrapper);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Wrapper<List<NewsDTO>> wrapper = new Wrapper<>();
                wrapper.setError(t);
                mHomelist.postValue(wrapper);

            }
        });
        return mHomelist;
    }
    public MutableLiveData<Wrapper<List<NewsDTO>>> getBasedOnCategory(String language, String country,String category ){
        Call<NewsList> listofNews = NnewsApi.getBasedOnCategory(language,country,category,NnewsService.KEY);
        listofNews.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Wrapper<List<NewsDTO>> wrapper = new Wrapper<>();
                if (response.body()!=null){
                    wrapper.setData(response.body().getmNewsDTOList());
                }
                mHomelist.setValue(wrapper);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {

                Wrapper<List<NewsDTO>>wrapper=new Wrapper<>();
                wrapper.setError(t);
                mHomelist.postValue(wrapper);

            }
        });
        return mHomelist;
    }
    public MutableLiveData<Wrapper<List<NewsDTO>>> getListBasedOnCountry(String sort,String country){
        Call<NewsList> listofNews = NnewsApi.getTrendingArticles(country,sort,NnewsService.KEY);
        listofNews.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Wrapper<List<NewsDTO>> wrapper = new Wrapper<>();
                wrapper.setData(response.body().getmNewsDTOList());
                mTrendList.setValue(wrapper);
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Wrapper<List<NewsDTO>> wrapper = new Wrapper<>();
                wrapper.setError(t);
                mTrendList.postValue(wrapper);

            }
        });
        return mTrendList;
    }
    public  MutableLiveData<Wrapper<List<NewsDTO>>> getListBasedOnQuery(String language,String query ){
     Call<NewsList>   listofNews = NnewsApi.getBySearch(language,query,NnewsService.KEY);
     listofNews.enqueue(new Callback<NewsList>() {
         @Override
         public void onResponse(Call<NewsList> call, Response<NewsList> response) {
             Wrapper<List<NewsDTO>> wrapper = new Wrapper<>();
             wrapper.setData(response.body().getmNewsDTOList());
             mSearchList.setValue(wrapper);
         }

         @Override
         public void onFailure(Call<NewsList> call, Throwable t) {
             Wrapper<List<NewsDTO>>wrapper = new Wrapper<>();
             wrapper.setError(t);
             mSearchList.postValue(wrapper);

         }
     });
     return mSearchList;
    }
}

