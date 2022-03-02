package com.mkao.newsgo;

import android.app.Application;

public class App extends Application {
    private NewsService newsService;
    @Override
    public void onCreate(){
        super.onCreate();
        newsService= new NewsService();

    }

    public NewsService getNewsService() {
        return newsService;
    }
}
