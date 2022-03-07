package com.mkao.newsgo.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkao.newsgo.R;
import com.mkao.newsgo.databinding.FragmentSearchBinding;
import com.mkao.newsgo.ui.ListNews;

public class SearchFragment extends Fragment {
    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    private RecyclerView recyclerView;
    private ListNews.Adapter mAdapter;
    private SearchView mSearchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding= FragmentSearchBinding.inflate(inflater,container,false);

        recyclerView=binding.SearchList;
        mSearchView=binding.searchview;

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setupList();
        mSearchView.setIconified(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView.setVisibility(View.VISIBLE);
                fetchData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerView.setVisibility(View.INVISIBLE);
                return false;
            }
        });
    }



    private void setupList() {
        if (mAdapter==null){
            mAdapter= new ListNews.Adapter(getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
    private void fetchData(String q) {
        searchViewModel.setQuery(q);
        searchViewModel.getList().observe(this,listWrapper ->{
            if (listWrapper.getError()!=null){
                Toast.makeText(getContext(), R.string.error_web,Toast.LENGTH_SHORT).show();
            }else {
                mAdapter.setList(listWrapper.getData());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
        mAdapter=null;
        recyclerView= null;
        binding= null;
    }
}
