package com.na.newsapp.home.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.na.newsapp.R;
import com.na.newsapp.data.model.Article;
import com.na.newsapp.detail.DetailActivity;
import com.na.newsapp.home.adapter.NewsAdapter;
import com.na.newsapp.home.listener.PostClickListener;
import com.na.newsapp.home.viewmodel.HomeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PostClickListener {
    List<Article> listOfArticles;
    RecyclerView recyclerView;
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initView();
        getNews();
    }

    private void initViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    private void getNews() {
         homeViewModel.getNewsData(this);
         homeViewModel.listMutableLiveData.observe(this, new Observer<List<Article>>() {
             @Override
             public void onChanged(List<Article> articles) {
                 listOfArticles  = articles;
                 recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                 recyclerView.setAdapter(new NewsAdapter(listOfArticles, MainActivity.this ));
             }
         });

      /*  retrofit = RetrofitFactory.createRetrofitInstance();
        newsServices = retrofit.create(NewsServices.class);

        newsServices.getAllNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        listOfArticles = response.body().getArticles();
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(new NewsAdapter(response.body().getArticles(), MainActivity.this ));
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                Log.d("ERROR", t.getMessage());

            }
        });*/
    }

    private void initView() {
        recyclerView = findViewById(R.id.rec_view);
    }

    @Override
    public void postClicked(int pos) {

        if(listOfArticles!=null && !listOfArticles.isEmpty()){
          Article article =  listOfArticles.get(pos);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("ARTICLE_DATA", article);
            startActivity(intent);
          /*  for(int i=0; i<=listOfArticles.size(); i++){
                if(listOfArticles.get(i).getTitle().equals(title)){
                  Article article = listOfArticles.get(i);

                }
            }*/
        }
    }
}