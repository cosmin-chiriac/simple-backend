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


    /**
     * Create article string.
     *
     * @param articleDTO the article dto
     *
     */
    public String createArticle(ArticleDTO articleDTO) {
        Article savedArticle = handleSaveArticle(OperationTypeEnum.CREATE, articleDTO);
        return "Article created with Id " + savedArticle.getId();
    }

    /**
     * Update article string.
     *
     * @param id         the id
     * @param articleDTO the article dto
     *
     */
    public String updateArticle(Integer id, ArticleDTO articleDTO) {
        articleDTO.setId(id);
        Article savedArticle = handleSaveArticle(OperationTypeEnum.UPDATE, articleDTO);
        return "Article updated with Id " + savedArticle.getId();
    }

    /**
     * Gets all articles.
     *
     * @param pageable the pageable
     *
     */
    public Iterable<Article> getAllArticles(Pageable pageable) {
        return articleDAO.getAllArticles(pageable);
    }

    /**
     * Gets article by id.
     *
     * @param id the id
     * @return the article by id
     */
    public Article getArticleById(Integer id) {
        return articleDAO.getArticleById(id);
    }

    /**
     * Gets all articles by user id.
     *
     * @param userId the user id
     * @return the all articles by user id
     */
    public Iterable<Article> getAllArticlesByUserId(Integer userId) {
        return articleDAO.getAllArticlesByUserId(userId);
    }


    private Article handleSaveArticle(OperationTypeEnum operation, ArticleDTO article) {
        //business logic

        return articleDAO.saveArticle(operation, article);

    }


}
