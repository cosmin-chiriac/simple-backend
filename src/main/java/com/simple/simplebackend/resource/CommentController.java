package com.simple.simplebackend.resource;

import com.simple.simplebackend.model.Comment;
import com.simple.simplebackend.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentRepo commentRepo;

    @PostMapping
    @RequestMapping("/create")
    public @ResponseBody String createComment (@RequestBody Comment comment) {
        commentRepo.save(comment);
        return "Comment saved.";
    }
}
