package com.na.newsapp.data.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.na.newsapp.data.model.Article;
import com.na.newsapp.util.TableConstants;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void addArticle(Article article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllArticles(List<Article> articleList);

    @Query("SELECT * FROM " + TableConstants.ARTICLE_TABLE)
     List<Article> getAllNewsData();
}
