package com.mkao.newsgo.ui.trending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mkao.newsgo.databinding.FragmentTrendingBinding;
import com.mkao.newsgo.ui.ListNews;

public class TrendingViewModel extends Fragment {
    private TrendingViewModel trendingViewModel;
    private FragmentTrendingBinding mbind;

    private RecyclerView recyclerView;
    private ListNews.Adapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        trendingViewModel = new ViewModelProvider(TrendingViewModel.class);

        mbind = FragmentTrendingBinding.inflate(inflater,container,false);
        recyclerView= mbind.trendingList;
        swipeRefreshLayout= mbind.getRoot();

        return mbind.getRoot();
    }
}
