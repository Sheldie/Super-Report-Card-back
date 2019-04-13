package com.shezzer.service.impl;

import com.shezzer.mapper.ArticleMapper;
import com.shezzer.pojo.Article;
import com.shezzer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public boolean addArticle(Article article) {
        articleMapper.addArticle(article);
        return true;
    }

    @Override
    public boolean updateArticle(Article article) {
        articleMapper.updateArticle(article);
        return true;
    }

    @Override
    public boolean deleteArticle(int ARTICLE_ID) {
        articleMapper.deleteArticle(ARTICLE_ID);
        return true;
    }

    @Override
    public Article findArticleById(int ARTICLE_ID) {
        return articleMapper.findArticleById(ARTICLE_ID);
    }

    @Override
    public List<Map> listArticle() {
        return articleMapper.listArticle();
    }
}
