package com.mkao.newsgo.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourceList {
    @SerializedName("source")
    private List<SourceDTO> mSourceDTOList;

    public List<SourceDTO> getSourceDTOList() {
        return mSourceDTOList;
    }

    public void setSourceDTOList(List<SourceDTO> mSourceDTOList) {
        this.mSourceDTOList = mSourceDTOList;
    }
}
