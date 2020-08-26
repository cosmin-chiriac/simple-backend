package com.simple.simplebackend.resource;

import com.simple.simplebackend.model.Comment;
import com.simple.simplebackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@Validated
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    /**
     * Create comment string.
     *
     * @param comment the comment
     */
    @PostMapping
    @RequestMapping("/create")
    public @ResponseBody
    String createComment(@RequestBody @Valid Comment comment) {
        return commentService.createComment(comment);
    }

    /**
     * Update comment string.
     *
     * @param id      the id
     * @param comment the comment
     */
    @PutMapping("/update/{id}")
    public @ResponseBody
    String updateComment(@PathVariable("id") @NotNull int id, @RequestBody @Valid Comment comment) {
        comment.setId(id);
        return commentService.updateComment(comment);
    }

    /**
     * Gets all comments for article.
     *
     * @param articleId the article id
     * @param pageable  the pageable
     */
    @GetMapping("allbyarticleId/{articleId}")
    public @ResponseBody
    Iterable<Comment> getAllCommentsForArticle(@PathVariable("articleId") @NotNull int articleId, @PageableDefault(page = 0, size = 20) @SortDefault(sort = "rating", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.getAllCommentsForArticle(articleId, pageable);
    }

    /**
     * Gets all comments for article.
     *
     * @param userId the user id
     */
    @GetMapping("allbyuserId/{userId}")
    public @ResponseBody
    Iterable<Comment> getAllCommentsForArticle(@PathVariable("userId") @NotNull int userId) {
        return commentService.getAllCommentsForUser(userId);
    }
}
