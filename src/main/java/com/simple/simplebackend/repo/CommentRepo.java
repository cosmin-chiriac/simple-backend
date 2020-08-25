package com.simple.simplebackend.repo;

import com.simple.simplebackend.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends PagingAndSortingRepository<Comment, Integer> {

    Iterable<Comment> findAllByUserId(int userId);

    Page<Comment> findAllByArticleId(int articleId, Pageable pageable);
}
