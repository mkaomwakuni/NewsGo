package com.mkao.newsgo.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourceList {
    @SerializedName("source")
    private List<SourceDTO> mSourceDTOList;

    public List<SourceDTO> getmSourceDTOList() {
        return mSourceDTOList;
    }

    public void setmSourceDTOList(List<SourceDTO> mSourceDTOList) {
        this.mSourceDTOList = mSourceDTOList;
    }
}
