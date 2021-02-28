package com.na.newsapp.home.viewmodel;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.na.newsapp.data.database.NewsDao;
import com.na.newsapp.data.database.NewsDatabase;
import com.na.newsapp.data.model.Article;
import com.na.newsapp.data.model.NewsResponse;
import com.na.newsapp.home.ui.MainActivity;
import com.na.newsapp.network.NewsServices;
import com.na.newsapp.network.RetrofitFactory;
import com.na.newsapp.util.NetworkUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    NewsServices newsServices;
    List<Article> articleList;
    public MutableLiveData<List<Article>> listMutableLiveData = new MutableLiveData();
    NewsDao newsDao;



    public void getNewsData(Context context){
        if (newsServices == null) {
            newsServices = RetrofitFactory.createRetrofitInstance().create(NewsServices.class);
        }
        createDatabaseInstance(context);
       createCall(context);
    }

    private void createDatabaseInstance(Context context) {
        newsDao = NewsDatabase.createDatabaseInstance(context).newsDao();
    }

    private void createCall(Context context) {
        if(NetworkUtil.isNetworkConnected(context)){
            newsServices.getAllNews().enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    if(response.isSuccessful()){
                        if(response.body()!=null){
                            listMutableLiveData.postValue(response.body().getArticles());
                            addData(response.body().getArticles());
                        }
                    }
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {

                }
            });
        }
        else{
            getPreviousNews();
        }
    }

    private void getPreviousNews() {
        class GetData extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                listMutableLiveData.postValue(newsDao.getAllNewsData());
                return null;
            }
        }
        GetData getData = new GetData();
        getData.execute();
    }

    private void addData(List<Article> articles) {
        if(articles!=null && !articles.isEmpty()){
            for (int i = 0; i<articles.size(); i++){
                addDataInBg(articles.get(i));
            }
        }
    }

    private void addDataInBg(Article article){
        class AsyncDataSaver extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                newsDao.addArticle(article);
                return null;
            }
        }
        AsyncDataSaver asyncDataSaver = new AsyncDataSaver();
        asyncDataSaver.execute();
    }

}
