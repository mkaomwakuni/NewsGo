package com.mkao.newsgo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkao.newsgo.api.models.NewsDTO;
import com.mkao.newsgo.databinding.ItemNewsBinding;

import java.util.ArrayList;
import java.util.List;

/**unified liof news for all fragments*/
public class ListNews{
    public static class  Adapter extends RecyclerView.Adapter<NewsHolder>{
        private final List<NewsDTO> mList = new ArrayList<>();
        private final Context mContext;

        //constructor
        public Adapter(Context mContext) {
            this.mContext = mContext;
        }
        public  void setList(List<NewsDTO> list){
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        //creates a new view holder when there are no existing
        // view holders which the RecyclerView can reuse
        public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Instantiating a  XML file into its corresponding View objects.
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            ItemNewsBinding itemNewsBinding = ItemNewsBinding.inflate(layoutInflater,parent,false);
            NewsHolder newsHolder = new NewsHolder(itemBinding,new ClickListener(){

            })

        }

        @Override
        public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        private
    }
}
