package com.simple.simplebackend.service;

import com.simple.simplebackend.dao.CommentDAO;
import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    /**
     * Create comment string.
     *
     * @param comment the comment
     */
    public String createComment(Comment comment) {
        //business logic
        Comment savedComment = handleSaveComment(OperationTypeEnum.CREATE, comment);
        return "Comment created with id: " + savedComment.getId();
    }

    /**
     * Update comment string.
     *
     * @param comment the comment
     */
    public String updateComment(Comment comment) {
        //business logic
        Comment savedComment = handleSaveComment(OperationTypeEnum.UPDATE, comment);
        return "Comment updated.";
    }

    /**
     * Gets all comments for article.
     *
     * @param articleId the article id
     * @param pageable  the pageable
     * @return the all comments for article
     */
    public Iterable<Comment> getAllCommentsForArticle(int articleId, Pageable pageable) {
        //business logic
        return commentDAO.getAllCommentsForArticle(articleId, pageable);
    }

    /**
     * Gets all comments for user.
     *
     * @param articleId the article id
     * @return the all comments for user
     */
    public Iterable<Comment> getAllCommentsForUser(int articleId) {
        //business logic
        return commentDAO.getAllCommentsForUser(articleId);
    }

    private Comment handleSaveComment(OperationTypeEnum operationType, Comment comment) {
        return commentDAO.saveComment(operationType, comment);
    }
}
