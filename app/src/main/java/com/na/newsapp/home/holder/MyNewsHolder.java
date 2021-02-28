package com.na.newsapp.home.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.na.newsapp.R;
import com.na.newsapp.data.model.Article;
import com.na.newsapp.home.listener.PostClickListener;

public class MyNewsHolder extends RecyclerView.ViewHolder{
    ImageView news_image;
    TextView news_title;
    TextView news_description;
    public MyNewsHolder(@NonNull View itemView) {
        super(itemView);

        news_image = itemView.findViewById(R.id.iv_image);
        news_title = itemView.findViewById(R.id.tv_news_title);
        news_description = itemView.findViewById(R.id.tv_news_description);
    }

    public void bind(Article article, PostClickListener postClickListener) {
        news_title.setText(article.getTitle());
        news_description.setText(article.getDescription());
        Glide.with(itemView.getContext())
                .load(article.getUrlToImage())
                .into(news_image);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postClickListener.postClicked(getAdapterPosition());
            }
        });
    }
}
