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


    /**
     * Create article string.
     *
     * @param articleDTO the article dto
     */
    @PostMapping(path = "/create")
    public @ResponseBody
    String createArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.createArticle(articleDTO);
    }

    /**
     * Update article string.
     *
     * @param id         the id
     * @param articleDTO the article dto
     */
    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    String updateArticle(@RequestParam("id") Integer id, @RequestBody ArticleDTO articleDTO) {
        return articleService.updateArticle(id, articleDTO);
    }

    /**
     * Gets all articles.
     *
     * @param pageable the pageable
     */
    @GetMapping(path = "/getall")
    public @ResponseBody
    Iterable<Article> getAllArticles(@PageableDefault(size = 5) Pageable pageable) {
        return articleService.getAllArticles(pageable);
    }

    /**
     * Gets article by id.
     *
     * @param id the id
     */
    @GetMapping(path = "/getbyid/{id}")
    public @ResponseBody
    Article getArticleById(@PathVariable("id") @NotNull int id) {
        return articleService.getArticleById(id);
    }

    /**
     * Gets all articles by user id.
     *
     * @param userId the user id
     */
    @GetMapping(path = "/getallbyuserid/{userId}")
    public @ResponseBody
    Iterable<Article> getAllArticlesByUserId(@PathVariable("userId") @NotNull int userId) {
        return articleService.getAllArticlesByUserId(userId);
    }
}
