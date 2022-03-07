package com.mkao.newsgo.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkao.newsgo.Internet_news;
import com.mkao.newsgo.R;
import com.mkao.newsgo.api.models.NewsDTO;
import com.mkao.newsgo.databinding.ItemNewsBinding;
import com.squareup.picasso.Picasso;

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
            ItemNewsBinding itemBinding = ItemNewsBinding.inflate(layoutInflater,parent,false);
            NewsHolder newsHolder = new NewsHolder(itemBinding,new ClickListener(){
                @Override
                public void onClick(int position){
                    String url = mList.get(position).getUrl();
                    Intent intent = Internet_news.getIntent(mContext,url);
                    mContext.startActivity(intent);
                }
                @Override
                public void onShare (int position){
                    String url = mList.get(position).getUrl();
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,url);
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent,null);
                    mContext.startActivity(shareIntent);
                }
            });
            return newsHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

            holder.bind(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

    }
    public static class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ItemNewsBinding binding;
        ClickListener NclickListener;


        public NewsHolder(ItemNewsBinding binding, ClickListener click) {
            super(binding.getRoot());
            this.binding = binding;
            this.NclickListener = click;

            binding.getRoot().setOnClickListener(this);
            binding.share.setOnClickListener(this);
        }

        public void bind(NewsDTO news){
            binding.heading.setText(news.getTitle());
            binding.text.setText(news.getContent());
            binding.publisher.setText(news.getAuthor());
            Picasso.get().load(news.getUrlToImage()).fit().centerCrop()
                    .placeholder(R.drawable.news_placeholder)
                    .into(binding.image);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            if (view.getId()==binding.getRoot().getId()){
                NclickListener.onClick(this.getLayoutPosition());
            }
            if (view.getId()==binding.share.getId()){
                NclickListener.onShare(this.getLayoutPosition());
            }
        }
    }
    /**Webview and Sharesheet on Click*/
    public interface ClickListener{
        void onClick(int position);
        void onShare(int position);
    }
}
