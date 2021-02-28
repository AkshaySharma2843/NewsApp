package com.na.newsapp.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.na.newsapp.data.model.Article;
import com.na.newsapp.home.holder.MyNewsHolder;
import com.na.newsapp.R;
import com.na.newsapp.home.listener.PostClickListener;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<MyNewsHolder> {
    private List<Article> articles;
    PostClickListener postClickListener;

    public NewsAdapter(List<Article> articles, PostClickListener postClickListener) {
        this.articles = articles;
        this.postClickListener = postClickListener;
    }


    @NonNull
    @Override
    public MyNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyNewsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsHolder holder, int position) {
       Article article = articles.get(position);
       holder.bind(article,postClickListener);


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
