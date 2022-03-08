package com.mkao.newsgo.ui.trending;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mkao.newsgo.api.NewsRepository;
import com.mkao.newsgo.api.models.NewsDTO;
import com.mkao.newsgo.api.models.Wrapper;
import com.mkao.newsgo.configuration.Config;
import com.mkao.newsgo.configuration.Sort;

import java.util.List;

public class TrendingViewModel extends ViewModel {
    private MutableLiveData<Wrapper<List<NewsDTO>>>mList;
    private String country;
    private String sortBy;

    public TrendingViewModel() {
        mList = new MutableLiveData<>();
        country = Config.geCountry();
        sortBy = Config.getSort(Sort.POPULARITY);
    }

    public MutableLiveData<Wrapper<List<NewsDTO>>> getList() {
        return NewsRepository.getInstance().getListBasedOnCountry(country,sortBy);
    }
}
