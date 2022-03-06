package com.mkao.newsgo.configuration;

public enum Sort {
    RELEVANCY,
    POPULARITY,
    PUBLISHED_AT;

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
