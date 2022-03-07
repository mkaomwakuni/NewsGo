package com.mkao.newsgo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mkao.newsgo.R;
import com.mkao.newsgo.databinding.FragmentHomeBinding;
import com.mkao.newsgo.ui.ListNews;

import java.util.Arrays;
import java.util.List;

import dev.chrisbanes.insetter.ViewState;


public class HomeFragment extends Fragment {
    private HomeViewModel NhomeViewModel;
    private FragmentHomeBinding Nbinding;
    private SwipeRefreshLayout Nswipe;

    RecyclerView NNewsList;
    ListNews.Adapter NNewsAdapter;

    RecyclerView NcategoryList;
    Adapter Ncategory;

    private  String category;

    @Override
    public View onCreate(@Nullable LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        NhomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        Nbinding = FragmentHomeBinding.inflate(inflater,container,false);
        Nswipe= Nbinding.swipeContainer;

        return Nbinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NNewsList= Nbinding.homeList;
        NcategoryList= Nbinding.categoryList;
        NcategoryList.setNestedScrollingEnabled(false);

        List<String> categories = Arrays.asList(getResources().getStringArray(R.array.categories));
        Ncategory= new Adapter(data -> {


        }
    }
}
