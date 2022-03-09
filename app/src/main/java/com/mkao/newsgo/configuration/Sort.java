package com.mkao.newsgo.configuration;

import androidx.annotation.NonNull;

public enum Sort {
    RELEVANCY,
    POPULARITY,
    PUBLISHED_AT;

    @NonNull
    public String toString(){
        switch (this){
            case RELEVANCY:
                return "relevancy";
            case POPULARITY:
                return "popularity";
            case PUBLISHED_AT:
                return "publishedAt";
        }
        return super.toString();
    }
}
