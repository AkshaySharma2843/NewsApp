package com.na.newsapp.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.na.newsapp.R;
import com.na.newsapp.data.model.Article;

public class DetailActivity extends AppCompatActivity {

    Article article;
    ImageView imageView;
    TextView title;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getData();
        initView();
        bindData();
    }

    private void bindData() {
        title.setText(article.getTitle());
        description.setText(article.getDescription());
        Glide.with(this)
                .load(article.getUrlToImage())
                .into(imageView);
    }

    private void initView() {
        imageView = findViewById(R.id.iv_news);
        title = findViewById(R.id.tv_detail_title);
        description = findViewById(R.id.tv_detail_description);
    }

    private void getData() {
        Intent intent = getIntent();
        article = (Article) intent.getSerializableExtra("ARTICLE_DATA");

    }
}