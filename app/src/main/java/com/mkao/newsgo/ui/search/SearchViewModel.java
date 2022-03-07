package com.mkao.newsgo.ui.search;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mkao.newsgo.api.NewsRepository;
import com.mkao.newsgo.api.models.NewsDTO;
import com.mkao.newsgo.api.models.Wrapper;
import com.mkao.newsgo.configuration.Config;

import java.util.List;

public class SearchViewModel extends ViewModel {
    private final String language;
    private String q;

    public SearchViewModel(){
        language = Config.getLang();
    }
    public MutableLiveData<Wrapper<List<NewsDTO>>> getList(){
        return NewsRepository.getInstance().getListBasedOnQuery(language,q);

    }


    public void setQuery(String q) {
        this.q =q;
    }
}


