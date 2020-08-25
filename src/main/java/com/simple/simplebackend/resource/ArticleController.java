package com.simple.simplebackend.resource;

import com.simple.simplebackend.dto.ArticleDTO;
import com.simple.simplebackend.model.Article;
import com.simple.simplebackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping(path = "/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @PostMapping(path = "/create")
    public @ResponseBody
    String createArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.createArticle(articleDTO);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    String updateArticle(@RequestParam("id") Integer id, @RequestBody ArticleDTO articleDTO) {
        return articleService.updateArticle(id, articleDTO);
    }

    @GetMapping(path = "/getall")

    public @ResponseBody
    Iterable<Article> getAllArticles(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return articleService.getAllArticles(pageable);
    }

    @GetMapping(path = "/getbyid/{id}")
    public @ResponseBody
    Article getArticleById(@PathVariable("id") @NotNull int id) {
        return articleService.getArticleById(id);
    }

    @GetMapping(path = "/getallbyuserid/{userId}")
    public @ResponseBody
    Iterable<Article> getAllArticlesByUserId(@PathVariable("userId") @NotNull int userId) {
        return articleService.getAllArticlesByUserId(userId);
    }
}
