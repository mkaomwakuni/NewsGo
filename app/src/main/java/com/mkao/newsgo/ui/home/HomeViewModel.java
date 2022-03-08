package com.mkao.newsgo.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mkao.newsgo.api.NewsRepository;
import com.mkao.newsgo.api.models.NewsDTO;
import com.mkao.newsgo.api.models.Wrapper;
import com.mkao.newsgo.configuration.Config;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private final NewsRepository Nrepository = NewsRepository.getInstance();
    private final String lang;
    private final String country;

    public  HomeViewModel(){
        lang = Config.getLang();
        country=Config.geCountry();
    }
    public MutableLiveData<Wrapper<List<NewsDTO>>> getList(){
        return Nrepository.getListBasedOnLanguage(lang,country);
    }
    public MutableLiveData<Wrapper<List<NewsDTO>>> getList(String category){
        return Nrepository.getBasedOnCategory(lang,country,category);
    }
}
