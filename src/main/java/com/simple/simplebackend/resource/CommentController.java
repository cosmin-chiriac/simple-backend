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


    @PostMapping
    @RequestMapping("/create")
    public @ResponseBody
    String createComment(@RequestBody @Valid Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody
    String updateComment(@PathVariable("id") @NotNull int id, @RequestBody @Valid Comment comment) {
        comment.setId(id);
        return commentService.updateComment(comment);
    }

    @GetMapping("allbyarticleId/{articleId}")
    public @ResponseBody
    Iterable<Comment> getAllCommentsForArticle(@PathVariable("articleId") @NotNull int articleId, @PageableDefault(page = 0, size = 20) @SortDefault(sort = "rating", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.getAllCommentsForArticle(articleId, pageable);
    }

    @GetMapping("allbyuserId/{userId}")
    public @ResponseBody
    Iterable<Comment> getAllCommentsForArticle(@PathVariable("userId") @NotNull int userId) {
        return commentService.getAllCommentsForUser(userId);
    }
}
