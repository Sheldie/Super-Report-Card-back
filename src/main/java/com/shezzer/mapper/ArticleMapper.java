package com.shezzer.mapper;

import com.shezzer.pojo.Article;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int ARTICLE_ID);
    Article findArticleById(int ARTICLE_ID);
    List<Map> listArticle();
}
