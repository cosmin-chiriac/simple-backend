package com.simple.simplebackend.resource;

import com.simple.simplebackend.model.Article;
import com.simple.simplebackend.repo.UserRepo;
import com.simple.simplebackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(path = "/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserRepo userRepo;

    @PostMapping(path = "/create")
    public @ResponseBody
    String createArticle(@RequestBody Article article) {
       /* Optional<User> userOpt = userRepo.findById(article.getAuthorId().getId());
        User user = userOpt.get();
        article.setAuthorId(user);
        user.getArticles().add(article);
        userRepo.save(user);*/

        return articleService.createArticle(article);
    }

    @GetMapping(path = "/getall")
    public @ResponseBody
    Iterable<Article> getAllArticles() {
        return articleService.getAllArticles();
    }
}
