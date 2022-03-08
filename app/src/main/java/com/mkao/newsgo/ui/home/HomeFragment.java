package com.mkao.newsgo.ui.home;

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
import com.mkao.newsgo.databinding.FragmentHomeBinding;
import com.mkao.newsgo.databinding.ItemCategoryBinding;
import com.mkao.newsgo.ui.ListNews;

import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {
    private HomeViewModel NhomeViewModel;
    private FragmentHomeBinding Nbinding;
    private SwipeRefreshLayout Nswipe;


    RecyclerView NNewsList;
    ListNews.Adapter NNewsAdapter;

    RecyclerView NcategoryList;
    Adapter Ncategory;

    private String category;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

        Ncategory = new Adapter(data ->{
            category = data;
            refresh();
            NNewsAdapter.notifyDataSetChanged();
        },categories);
        Nswipe.setOnRefreshListener(this::refresh);
        Nswipe.setColorSchemeColors(Nbinding.getRoot().getResources().getColor(R.color.purple_700),
                Nbinding.getRoot().getResources().getColor(R.color.purple_200));

        setRecyclerView();
        refresh();
    }

    private void setRecyclerView() {
        if (NNewsAdapter==null){
            NNewsAdapter= new ListNews.Adapter(getContext());
            NNewsList.setAdapter(NNewsAdapter);
            NNewsList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        }
    }


    private void refresh() {
        if (category==null){
            NhomeViewModel.getList().observe(this,listWrapper ->{
                if (listWrapper.getError()!=null){
                    Toast.makeText(getContext(),R.string.error_web, Toast.LENGTH_SHORT).show();
                }else {
                    NNewsAdapter.setList(listWrapper.getData());
                }
                Nswipe.setRefreshing(false);
            });
        }

    }
    /**ADAPTER FOR LIST THAT SHOWS CATEGORIES*/
    public static class Adapter extends RecyclerView.Adapter<CategoryHolder>{
        private static final List<String> LIST = (Arrays.asList("business","entertainment"
                ,"general","health","science","sport","technology"));
        private final List<String>listtoshow;
        private String category;
        private final CategoryHolder.DataTransfer dataTransfer;

        public Adapter(List<String> listtoshow, CategoryHolder.DataTransfer dataTransfer) {
            this.listtoshow = listtoshow;
            this.dataTransfer = dataTransfer;
        }

        @NonNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            @NonNull ItemCategoryBinding binding = ItemCategoryBinding.inflate(layoutInflater,parent,false);
            CategoryHolder holder = new CategoryHolder(binding,position ->{
                category = LIST.get(position);
                dataTransfer.transferData(category);
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
            holder.bind(listtoshow.get(position));
        }

        @Override
        public int getItemCount() {
            return listtoshow.size();
        }
    }
    public static class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
      CategoryClick Nclick;
      ItemCategoryBinding Nbinding;
      public CategoryHolder(ItemCategoryBinding binding,CategoryClick click){
          super(binding.getRoot());
          Nbinding= binding;
          Nclick=click;

          Nbinding.getRoot().setOnClickListener(this);
      }

        public void bind(String category) {
          Nbinding.setCategory(category);
          Nbinding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            Nclick.onClick(this.getLayoutPosition());
        }
        /*Get chosen category onclick*/
        public interface CategoryClick{
            void onClick(int position);
        }
        /*Transfer chosen category from Adapter to Fragment*/
        public interface DataTransfer{
            void transferData(String data);
        }
    }

}
