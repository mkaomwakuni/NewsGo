package com.mkao.newsgo.api.models;

import com.google.gson.annotations.SerializedName;
import com.mkao.newsgo.api.models.NewsDTO;

import java.util.List;

/**unified liof news for all fragments*/
public class NewsList {
    @SerializedName("articles")
    private List<NewsDTO> mNewsDTOList;

    public List<NewsDTO> getmNewsDTOList(){
        return mNewsDTOList;
    }
    public void setmNewsDTOList(List<NewsDTO> newsDTOList){
        mNewsDTOList= newsDTOList;
    }

}
