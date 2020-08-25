package com.simple.simplebackend.dao;

import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.model.Comment;
import com.simple.simplebackend.repo.CommentRepo;
import com.simple.simplebackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CommentDAO {
@Autowired
    CommentRepo commentRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public Comment saveComment(OperationTypeEnum operationType, Comment comment) {
        if(OperationTypeEnum.CREATE.equals(operationType)){
            //data logic for create
            Comment savedComment = commentRepo.save(comment);
            LOGGER.info("Comment created with id: " + savedComment.getId());
         return savedComment;
        } else
        //data logic for update
        {
            Comment savedComment = commentRepo.save(comment);
            LOGGER.info("Comment with id: " + savedComment.getId() + " was updated.");
            return savedComment;
        }
    }

    public Iterable<Comment> getAllCommentsForArticle(int articleId, Pageable pageable) {
        return commentRepo.findAllByArticleId(articleId,pageable);
    }

    public Iterable<Comment> getAllCommentsForUser(int userId) {
        return commentRepo.findAllByUserId(userId);
    }
}
