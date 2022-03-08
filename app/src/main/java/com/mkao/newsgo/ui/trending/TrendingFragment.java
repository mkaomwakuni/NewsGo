package com.mkao.newsgo.ui.trending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mkao.newsgo.R;
import com.mkao.newsgo.databinding.FragmentTrendingBinding;
import com.mkao.newsgo.ui.ListNews;

public class TrendingFragment extends Fragment {
    private TrendingViewModel trendingViewModel;
    private FragmentTrendingBinding binding;

    private RecyclerView MrecyclerView;
    private ListNews.Adapter MdAPTER;
    private SwipeRefreshLayout Mswipe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       trendingViewModel = new ViewModelProvider(this).get(TrendingViewModel.class);
        binding = FragmentTrendingBinding.inflate(inflater,container,false);
        MrecyclerView = binding.trendingList;
        Mswipe= binding.getRoot();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       Mswipe.setOnRefreshListener(this::refresh);
       Mswipe.setColorSchemeColors(binding.getRoot().getResources().getColor(R.color.purple_500),binding.getRoot().getResources().getColor(R.color.purple_200));
        setupList();
        refresh();

    }



    private void refresh() {
        trendingViewModel.getList().observe(this,listWrapper ->{
            if (listWrapper.getError()!=null){
                Toast.makeText(getContext(), R.string.error_web ,Toast.LENGTH_SHORT).show();

            }else {
                MdAPTER.setList(listWrapper.getData());
            }
        });
    }
    private void setupList() {
        if (MdAPTER==null){
            MdAPTER= new ListNews.Adapter(getContext());
            MrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            MrecyclerView.setAdapter(MdAPTER);
        }else {
            MdAPTER.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MrecyclerView.setAdapter(null);
        MdAPTER =null;
        MrecyclerView = null;
        binding= null;
    }
}
