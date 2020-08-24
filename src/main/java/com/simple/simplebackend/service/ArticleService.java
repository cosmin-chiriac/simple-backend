package com.simple.simplebackend.service;

import com.simple.simplebackend.dao.ArticleDAO;
import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {


    @Autowired
    private ArticleDAO articleDAO;


    public String createArticle(Article article) {
        Article savedArticle = handleSaveArticle(OperationTypeEnum.CREATE, article);
        return "Article created with Id " + savedArticle.getId();
    }
    public Iterable<Article> getAllArticles() {
        return articleDAO.getAllArticles();
    }






    private Article handleSaveArticle(OperationTypeEnum operation, Article article) {
        //business logic
        if (OperationTypeEnum.CREATE.equals(operation)) {
        //business logic for creation
            return articleDAO.saveArticle(article);
        }
        //business logic for update
        return articleDAO.saveArticle(article);
    }


}
