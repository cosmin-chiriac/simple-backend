package com.simple.simplebackend.dao;

import com.simple.simplebackend.dto.ArticleDTO;
import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.exceptions.ArticleNotFoundException;
import com.simple.simplebackend.mail.MailHandler;
import com.simple.simplebackend.model.Article;
import com.simple.simplebackend.model.User;
import com.simple.simplebackend.repo.ArticleRepo;
import com.simple.simplebackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ArticleDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    MailHandler mailHandler;
    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private UserDAO userDAO;

    public Iterable<Article> getAllArticles(Pageable pageable) {
        return articleRepo.findAll(pageable);
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

    public Iterable<Article> getAllArticlesByUserId(Integer userId) {
        return articleRepo.findAllByUserId(userId);
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
        Article savedArticle = articleRepo.save(article);
        LOGGER.info("Article created with id: " + savedArticle.getId());
        sendEmailToCategorySubscribers(savedArticle);
        return savedArticle;

    }

    @Async
    private void sendEmailToCategorySubscribers(Article article) {
        Iterable<String> listOfSubscribersEmails = userDAO.getSubscribersEmailList(article.getCategory());
        listOfSubscribersEmails.forEach(email -> mailHandler.sendEmail(email, article.getCategory() + " NEW UPDATES!!! ", "A new article named: \"+ article.getTitle()+ \" was created in your subscribed category. "));
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
        LOGGER.info("Article with id: " + article.getId() + " was updated.");
        return article;
    }

}
