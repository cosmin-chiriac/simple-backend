package com.simple.simplebackend.repo;

import com.simple.simplebackend.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends CrudRepository<Article, Integer> {

    @Query("select a from Article a where a.user = ?1 ")
    Iterable<Article> findAllByUserId(int userId);
}
