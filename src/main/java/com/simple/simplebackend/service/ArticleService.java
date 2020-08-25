package com.simple.simplebackend.service;

import com.simple.simplebackend.dao.ArticleDAO;
import com.simple.simplebackend.dto.ArticleDTO;
import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {


    @Autowired
    private ArticleDAO articleDAO;


    public String createArticle(ArticleDTO articleDTO) {
        Article savedArticle = handleSaveArticle(OperationTypeEnum.CREATE, articleDTO);
        return "Article created with Id " + savedArticle.getId();
    }

    public String updateArticle(Integer id, ArticleDTO articleDTO) {
        Article savedArticle = handleSaveArticle(OperationTypeEnum.UPDATE, articleDTO);
        return "Article updated with Id " + savedArticle.getId();
    }

    public Iterable<Article> getAllArticles(Pageable pageable) {
        return articleDAO.getAllArticles(pageable);
    }

    public Article getArticleById(Integer id) {
        return articleDAO.getArticleById(id);
    }
    public Iterable<Article> getAllArticlesByUserId(Integer userId) {
        return articleDAO.getAllArticlesByUserId(userId);
    }


    private Article handleSaveArticle(OperationTypeEnum operation, ArticleDTO article) {
        //business logic

        return articleDAO.saveArticle(operation, article);

    }


}
