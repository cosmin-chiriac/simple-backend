package com.simple.simplebackend.dao;

import com.simple.simplebackend.model.Article;
import com.simple.simplebackend.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleDAO {
    @Autowired
    private ArticleRepo articleRepo;

    public Article saveArticle(Article article) {
        return  articleRepo.save(article);
    }

    public Iterable<Article> getAllArticles() {
        return articleRepo.findAll();
    }
}
