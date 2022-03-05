package com.mkao.newsgo;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**unified liof news for all fragments*/
public class NewsList {
    public static RecyclerView.Adapter extends RecyclerView.Adapter<NewsHolder>{
    private final List<NewsDTO> mList = new ArrayList<>();
    }
}
