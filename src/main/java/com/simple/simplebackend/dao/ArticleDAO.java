package com.simple.simplebackend.dao;

import com.simple.simplebackend.dto.ArticleDTO;
import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.exceptions.ArticleNotFoundException;
import com.simple.simplebackend.model.Article;
import com.simple.simplebackend.model.User;
import com.simple.simplebackend.repo.ArticleRepo;
import com.simple.simplebackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ArticleDAO {
    @Autowired
    private ArticleRepo articleRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public Iterable<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    public Article saveArticle(OperationTypeEnum operation, ArticleDTO articleDTO) {
        if (OperationTypeEnum.CREATE.equals(operation)) {
            return createArticle(articleDTO);
        }
        return updateArticle(articleDTO);
    }

    public Article getArticleById(Integer id) {
        return articleRepo.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    private Article createArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setCategory(articleDTO.getCategory());
        article.setTitle(articleDTO.getTitle());
        if (!StringUtils.isEmpty(articleDTO.getBody())) {
            article.setBody(articleDTO.getBody());
        }
        User userAuthor = new User();
        userAuthor.setId(articleDTO.getUser().getId());
        article.setUser(userAuthor);
        LOGGER.info("Article created with id: " + article.getId());
        return articleRepo.save(article);

    }

    private Article updateArticle(ArticleDTO articleDTO) {
        Article article = getArticleById(articleDTO.getId());
        if (!StringUtils.isEmpty(articleDTO.getCategory())) {
            article.setCategory(articleDTO.getCategory());
        }
        if (!StringUtils.isEmpty(articleDTO.getTitle())) {
            article.setTitle(articleDTO.getTitle());
        }
        if (!StringUtils.isEmpty(articleDTO.getBody())) {
            article.setBody(articleDTO.getBody());
        }
        LOGGER.info("Article with id: " + article.getId()+ " was updated.");
        return article;
    }

    public Iterable<Article> getAllArticlesByUserId(Integer userId) {
        return articleRepo.findAllByUserId(userId);
    }
}
