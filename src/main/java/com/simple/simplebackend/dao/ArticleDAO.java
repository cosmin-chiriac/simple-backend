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
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Component
public class ArticleDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    MailHandler mailHandler;
    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private UserDAO userDAO;

    /**
     * Gets all articles.
     *
     * @param pageable the pageable
     * @return all articles
     */
    public Iterable<Article> getAllArticles(Pageable pageable) {
        return articleRepo.findAll(pageable);
    }

    /**
     * Save article article.
     *
     * @param operation  the operation
     * @param articleDTO the article dto
     * @return the article
     */
    public Article saveArticle(OperationTypeEnum operation, ArticleDTO articleDTO) {
        if (OperationTypeEnum.CREATE.equals(operation)) {
            return createArticle(articleDTO);
        }
        return updateArticle(articleDTO);
    }

    /**
     * Gets article by id.
     *
     * @param id the id
     * @return the article by id
     */
    public Article getArticleById(Integer id) {
        return articleRepo.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    /**
     * Gets all articles by user id.
     *
     * @param userId the user id
     * @return the all articles by user id
     */
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
        long start = System.currentTimeMillis();
        CompletableFuture completable = sendEmailToCategorySubscribers(savedArticle);
        /*while (!completable.isDone()) {
            try {
                completable.get();
            } catch (ExecutionException | InterruptedException e) {
            }
        }*/
        LOGGER.info("Elapsed time: " + (System.currentTimeMillis() - start));
        return savedArticle;
    }

    @Async
    private CompletableFuture sendEmailToCategorySubscribers(Article article) {
        Iterable<String> listOfSubscribersEmails = userDAO.getSubscribersEmailList(article.getCategory());
        return (new AsyncResult<String>(sendEmailsToSubscribersList(listOfSubscribersEmails, article)).completable());
    }

    private String sendEmailsToSubscribersList(Iterable<String> listOfSubscribersEmails, Article article) {
        listOfSubscribersEmails.forEach(email -> mailHandler.sendEmail(email, article.getCategory() + " NEW UPDATES!!! ", "A new article named: \"+ article.getTitle()+ \" was created in your subscribed category. "));
        return "Emails sent!!!";
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
