package com.shezzer.service;

import com.shezzer.pojo.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    boolean addArticle(Article article);
    boolean updateArticle(Article article);
    boolean deleteArticle(int ARTICLE_ID);
    Article findArticleById(int ARTICLE_ID);
    List<Map> listArticle();
}
